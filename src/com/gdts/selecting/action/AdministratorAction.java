package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.util.ResoultMapping;
import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.service.IAdminManagerService;
import com.gdts.selecting.service.ISysUserService;
import com.gdts.selecting.util.LoginMSGOption;
import com.gdts.selecting.util.UserAnalysis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings({ "serial", "rawtypes" })
public class AdministratorAction extends BaseAction implements Preparable, ModelDriven{

	@SuppressWarnings("unused")
	private static final String SYS_ADMIN_JSP = "WEB-INF/page/adminPage";
	
	private Long totel;
	private Long start;
	private Long end;
	private String tableName;
	private int userType;
	private ResoultMapping<SysUser> limitSysUser;
	private List<SysUser> sysUserList;
	private PrintWriter print;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private IAdminManagerService iAdminManagerService;
	
	/**
	 * countSysUserAll
	 * @Description: 
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月31日
	 */
	public String countSysUserAll(){
		UserAnalysis userAnalysis = iAdminManagerService.countSysUserAll();
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		String json = gson.toJson(userAnalysis);
		out.print(json);
		System.out.println("countSysUserAll--->json:"+json);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * loginMsg
	 * @Description: 管理员统计用户登录活跃量
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月31日
	 */
	public String loginMsg(){
		LoginMSGOption option = iAdminManagerService.loginMSG();
		System.out.println(option);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(option,LoginMSGOption.class));
		return null;
	}
	
	/**
	  * administratorLimit 管理员分页
	  * @param start 分页开始位置
	  * @param end 分页结束位置
	  * @return
	  * @author liuchunfu
	  */
	public String administratorLimit(){
		totel = iSysUserService.getTotel("SysUser",this.getUserType());
		System.out.println("this.getStart():"+this.getStart()+"--this.getEnd():"+this.getEnd()+"---totel:"+totel);
		sysUserList = iSysUserService.administratorLimit(totel, this.getStart(), this.getEnd(),this.getUserType());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		limitSysUser.setResoult(totel, start, "SysUser", sysUserList);
		String json = gson.toJson(limitSysUser.getResoult());
		System.out.println("administratorLimit:"+json);
		print = this.getPrintWriter();
		print.print(json);
		print.flush();
		print.close();
		return null;
	}
	
	/**
	 * 获取表中总记录数
	 * @return
	 * @author liuchunfu
	 */
	public String getMaxTotel(){
		totel = iSysUserService.getTotel(this.getTableName(),this.getUserType());
		Gson gson = new Gson();
		print = this.getPrintWriter();
		print.print(totel);
		print.flush();
		print.close();
		return null;
	}
	
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void prepare() throws Exception {
		if(null == limitSysUser){
			limitSysUser = new ResoultMapping<SysUser>();
		}
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
