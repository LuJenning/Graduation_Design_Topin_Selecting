package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.LoginInfoUser;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.service.ILoginService;
import com.gdts.selecting.util.ResourceUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings({ "serial", "rawtypes" })
public class LoginAction extends BaseAction implements Preparable, ModelDriven {
	protected static final String LOGIN_JSP = "/login.jsp";
	protected static final String MANAGERINDEX_JSP = "/WEB-INF/page/ManagerIndex.jsp";
	protected static final String STUDENT_JSP = "/WEB-INF/page/StudentIndex.jsp";
	protected static final String TUTOR_JSP = "/WEB-INF/page/TutorIndex.jsp";
	protected static final String ADMIN_INDEX_JSP ="/WEB-INF/page/adminPage/adminIndex.jsp";
	protected static final String STUDENT_INDEX_JSP ="/WEB-INF/page/studentPage/studentIndex.jsp";
	protected static final String TEACHER_INDEX_JSP ="/WEB-INF/page/teacherPage/teacherIndex.jsp";
	
	@Autowired
	private ILoginService loginService;
	private SysUser sysUser;
	private String userId;
	private String userPassword;
	private String userType;
	private String error;
	private String flagString;
	private Map<String, String> allResource;
	
	/**
	 * 
	 * @Description: 打开登陆请求
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月25日
	 */
	public String openLogin(){
		ResourceUtil resourceUtil = new ResourceUtil();
		resourceUtil.loadForZh();//默认调用中文
		System.out.println("web--->openLogin");
		Set<String> keysetZ = ResourceUtil.getKeys();
		Iterator<String> keyIterZ = (keysetZ!=null)?keysetZ.iterator():null;
		//循环获key 获取value
		while(keyIterZ!=null && keyIterZ.hasNext()){
			String key = keyIterZ.next();
			String value = ResourceUtil.text(key);
			allResource.put(key, value);
		}
		if(null != flagString && !"".equals(flagString) && "en".equals(flagString)){//调用英文
			resourceUtil = new ResourceUtil();
			resourceUtil.loadForEn();
			Set<String> keysetE = ResourceUtil.getKeys();
			Iterator<String> keyIterE = (keysetE!=null)?keysetE.iterator():null;
			//循环获key 获取value
			while(keyIterE !=null && keyIterE.hasNext()){
				String key = keyIterE.next();
				String value = ResourceUtil.text(key);
				allResource.put(key, value);
			}
		};
		System.out.println("allResource:"+allResource);
		this.forwardView = "/WEB-INF/login.jsp";
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Description: 登录验证
	 * @param @return   
	 * @return String  
	 * @throws ParseException 
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月21日
	 */
	public String doLogin(){
		System.out.println("接收到："+sysUser.getUserId()+"--"+sysUser.getUserPassword());
		SysUser loginSysUser = loginService.toLogin(sysUser);
		System.out.println(loginSysUser);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		if(null != loginSysUser){
			if(loginSysUser.getUserPassword().equals(sysUser.getUserPassword())){
				System.out.println("login == > true");
				this.getSession().setAttribute("SysUser", loginSysUser);
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat dateFm = new SimpleDateFormat("EEEE"); 
					LoginInfoUser loginInfoUser = new LoginInfoUser();
					Date nowDate = new Date();
					String date = df.format(nowDate);
					loginInfoUser.setUserId(loginSysUser.getUserId());
					loginInfoUser.setUserType(loginSysUser.getUserType());
					loginInfoUser.setLoginDate(df.parse(date));
					loginInfoUser.setWeekDay(dateFm.format(nowDate));
					loginService.saveLoginInfo(loginInfoUser);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("doLogin---SysUser："+this.getSession().getAttribute("SysUser"));
				out.print(gson.toJson(true));				
			}else{
				System.out.println("login == > flase");
				out.print(gson.toJson(false));
			}
		}else{
			System.out.println("login == > error");
			out.print(gson.toJson("error"));
		}
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 打开登录成功后的页面
	 * @author lujianning,刘春福
	 * @return
	 * @throws Exception
	 * @date 2018-5-20
	 */
	public String userLogin() throws Exception {
		SysUser allUser = (SysUser) this.getSession().getAttribute("SysUser");
		System.out.println("userLogin---SysUser："+allUser);
		if(null != allUser){
			switch (allUser.getUserType()) {
				case 1:
					setForwardView(ADMIN_INDEX_JSP);
					break;
				case 2:
					setForwardView(TEACHER_INDEX_JSP);
					break;
				case 3:
					setForwardView(STUDENT_INDEX_JSP);
					break;
				default:
					setForwardView(LOGIN_JSP);
					break;
			}
		}else{
			setForwardView(LOGIN_JSP);
		};
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Description: 用户退出
	 * @return String  
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月24日
	 */
	public String logout(){
		this.getSession().invalidate();
		//setForwardView("/WEB-INF/login.jsp");
		return openLogin();
	}
	
	public String tempRegister(){
		
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare() throws Exception {
		if (null == sysUser) {
			sysUser = new SysUser();
		}
		if(null == allResource){
			allResource = new HashMap<String, String>();
		}

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getFlagString() {
		return flagString;
	}

	public void setFlagString(String flagString) {
		this.flagString = flagString;
	}

	public Map<String, String> getAllResource() {
		return allResource;
	}

	public void setAllResource(Map<String, String> allResource) {
		this.allResource = allResource;
	}

}
