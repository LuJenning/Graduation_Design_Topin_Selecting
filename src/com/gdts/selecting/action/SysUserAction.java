package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.gdts.core.util.JsonEntityMapping;
import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.ClassInfo;
import com.gdts.selecting.entity.InstituteInfo;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.service.IClassService;
import com.gdts.selecting.service.IInstetuteService;
import com.gdts.selecting.service.ISysUserService;
import com.gdts.selecting.service.ITutorService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
public class SysUserAction extends BaseAction implements Preparable, ModelDriven{
	protected static final String STUDENT_ADD ="/WEB-INF/page/studentPage/Student_add.jsp";
	protected static final String STUDENT_SHOW ="/WEB-INF/page/studentPage/Student_show.jsp";
	protected static final String TEACHER_SHOW ="/WEB-INF/page/teacherPage/teacher_show.jsp";
	protected static final String STUDENT_EDIT="/WEB-INF/page/studentPage/Student_edit.jsp";
	protected static final String TEACHER_EDIT="/WEB-INF/page/teacherPage/teacher_edit.jsp";
	private InstituteInfo instituteInfo; //学院，专业买模型
	private ClassInfo classInfo; //班级
	private SysUser sysUser; //用户
	private String userBirthday;
	@Autowired
	private IInstetuteService instituteService; // 注入
	@Autowired
	private IClassService classService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ITutorService tutorService;
	private List<InstituteInfo> instituteList; //学院列表
	private List<InstituteInfo> majorList; //专业列表
	private List<ClassInfo> classList; //班级列表
	private Integer instituteId;
	
	
	/**
	 * 打开学生注册页面
	 * @return
	 * @author 秦松
	 * @throws Exception
	 */
	public String openRegister() throws Exception{
		instituteList=instituteService.findInstituteList();
		forwardView=STUDENT_ADD;
		return SUCCESS;
	}
	/**
	 * AJAX异步判断用户账号是否存在
	 * @author 秦松
	 * @return
	 * @throws Exception
	 */
	public String findByUserId() throws Exception{
		SysUser sysUserId = sysUserService.findByUserId(sysUser.getUserId());
		System.out.println("++有值吗:"+sysUser.getUserId());
		//获得response对象，顶页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		//判断
		if(sysUserId!=null){
			response.getWriter().println("<font color='red'>账号已经存在</font>");
			
		}else{
			response.getWriter().println("<font color='green'>账号可以使用</font>");
		}
		return NONE;
	}
	
	/**
	 * 学生注册
	 * @return
	 * @author 秦松
	 */
	public String studentAdd() throws Exception{
		//sysUser.setUserBirthday(Timestamp.valueOf(DateUtils.formatTime(new Date())));
		//Date date=new Date();
		this.setUserBirthday(this.getUserBirthday()+" 00:00:00");
		System.out.println("bir:"+this.getUserBirthday());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("学生注册："+df.parse(this.getUserBirthday()));
		sysUser.setUserBirthday(df.parse(this.getUserBirthday()));
		sysUserService.addStudent(sysUser);
		//tutorService.saveTeacherByAjax(sysUser);//这个方法是修改信息的：学生和教师基本信息都用这个方法保存
		
		this.getRequest().setAttribute("msg", "<script>alert('学生添加成功！');</script>");
		instituteList=instituteService.findInstituteList();
		forwardView=STUDENT_ADD;
		return SUCCESS;
	}
	/**
	 * 学生查看个人信息
	 * @return
	 * @author 秦松
	 * @throws Exception
	 */
	public String studentShow() throws Exception{
		
		sysUser=(SysUser)ActionContext.getContext().getSession().get("SysUser");
		System.out.println("ss "+sysUser.getUserName());
		if(sysUser.getUserType() == 1){
			
		}else{
			instituteList=instituteService.findInstituteList();
			instituteInfo=instituteService.findByInstituteId(sysUser.getInstituteId());
			System.out.println("instituteInfo值:"+instituteInfo);
			majorList=instituteService.findMajorsListByPid(instituteInfo.getInstitutePid());
			classList=classService.findClassListById(sysUser.getInstituteId());
		};
		System.out.println("session:"+sysUser);
		if(sysUser.getUserType()==3)
			forwardView=STUDENT_SHOW;
			else
				forwardView=TEACHER_SHOW;
			return SUCCESS;
		
	}
	/**
	 * 打开学生个人信息修改页面
	 * @return
	 * @throws Exception
	 */
	public String openStudentEdit() throws Exception{
		/*String id=getParameters("id");
		sysUser=sysUserService.findById(Integer.parseInt(id));*/
		sysUser=(SysUser)ActionContext.getContext().getSession().get("SysUser");
		instituteList=instituteService.findInstituteList();
		instituteInfo=instituteService.findByInstituteId(sysUser.getInstituteId());
		majorList=instituteService.findMajorsListByPid(instituteInfo.getInstitutePid());
		classList=classService.findClassListById(sysUser.getInstituteId());
		if(sysUser.getUserType()==3)
		forwardView=STUDENT_EDIT;
		else
			forwardView=TEACHER_EDIT;
		return SUCCESS;
	}
	/**
	 * 学生修改个人信息
	 * @return
	 * @author 秦松
	 * @throws Exception
	 */
	public String studentEdit() throws Exception{
		
		this.setUserBirthday(this.getUserBirthday()+" 00:00:00");
		System.out.println("bir:"+this.getUserBirthday());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("学生修改："+df.parse(this.getUserBirthday()));
		sysUser.setUserBirthday(df.parse(this.getUserBirthday()));
		sysUser.setUserType(3);
	    sysUserService.updateStudent(sysUser);
		this.getSession().setAttribute("SysUser", sysUser);
		this.getRequest().setAttribute("msg", "<script>alert('学生信息修改成功！');</script>");
		//forwardView=STUDENT_SHOW;
		return studentShow();
	}
	/**
	 * 教师修改个人信息
	 * @return
	 * @author 秦松
	 * @throws Exception
	 */
	public String teacherEdit() throws Exception{
		
		this.setUserBirthday(this.getUserBirthday()+" 00:00:00");
		System.out.println("bir:"+this.getUserBirthday());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("学生修改："+df.parse(this.getUserBirthday()));
		sysUser.setUserBirthday(df.parse(this.getUserBirthday()));
		sysUser.setUserType(2);
	    sysUserService.updateStudent(sysUser);
		this.getSession().setAttribute("SysUser", sysUser);
		this.getRequest().setAttribute("msg", "<script>alert('学生信息修改成功！');</script>");
		//forwardView=STUDENT_SHOW;
		return studentShow();
	}
	
	/**
	 * 删除用户
	 * @return
	 * @author liuchunfu
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delStudent() throws Exception{
		SysUser sessionSysUser = (SysUser) this.getSession().getAttribute("SysUser");
		PrintWriter out = this.getPrintWriter();
		JsonEntityMapping jsonEntity = null;
		Gson gson = null;
		if(null != sessionSysUser && 1 == sessionSysUser.getUserType()){
			SysUser delUser = sysUserService.getSysUserById(sysUser.getId());
			sysUserService.delStudent(delUser);
			jsonEntity = new JsonEntityMapping();
			jsonEntity.setT(delUser);
			jsonEntity.setMsg("用户删除成功！");
			gson = new Gson();
			out.print(gson.toJson(jsonEntity));
			out.flush();
			out.close();
		}
		return null;
	}
	
	
	public InstituteInfo getInstituteInfo() {
		return instituteInfo;
	}


	public void setInstituteInfo(InstituteInfo instituteInfo) {
		this.instituteInfo = instituteInfo;
	}


	public ClassInfo getClassInfo() {
		return classInfo;
	}


	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}


	public SysUser getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}


	public List<InstituteInfo> getInstituteList() {
		return instituteList;
	}


	public void setInstituteList(List<InstituteInfo> instituteList) {
		this.instituteList = instituteList;
	}


	public List<InstituteInfo> getMajorList() {
		return majorList;
	}


	public void setMajorList(List<InstituteInfo> majorList) {
		this.majorList = majorList;
	}


	public List<ClassInfo> getClassList() {
		return classList;
	}


	public void setClassList(List<ClassInfo> classList) {
		this.classList = classList;
	}

	public String getUserBirthday() {
		return userBirthday;
	}
	
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return sysUser;
	}

	@Override
	public void prepare() throws Exception {
		if(null == instituteInfo)
			instituteInfo = new InstituteInfo();
		
	}
	
	public Integer getInstituteId() {
		return instituteId;
	}
	
	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}

}
