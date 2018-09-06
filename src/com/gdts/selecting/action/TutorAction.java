package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.pagination.Result;
import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.ClassInfo;
import com.gdts.selecting.entity.InstituteInfo;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
import com.gdts.selecting.service.IClassService;
import com.gdts.selecting.service.IInstetuteService;
import com.gdts.selecting.service.ISysUserService;
import com.gdts.selecting.service.ITutorService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Controller层
 * 
 * @author 陆建宁
 * @date 2018 5-30
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class TutorAction extends BaseAction implements Preparable, ModelDriven {
	protected static final String ADD_JSP = "/WEB-INF/page/Tutor/Tutor_add.jsp";
	protected static final String LIST_JSP = "/WEB-INF/page/Tutor/Tutor_teacher_list.jsp";
	protected static final String EDIT_JSP = "/WEB-INF/page/Tutor/Tutor_edit.jsp";
	protected static final String SHOW_JSP = "/WEB-INF/page/Tutor/Tutor_show.jsp";
	protected static final String STUDENT_LIST_JSP = "/WEB-INF/page/adminPage/student/student_list.jsp";
	protected static final String STUDENT_PERSONAL_JSP = "/WEB-INF/page/studentPage/StuBeSeenByTeacher.jsp";
	@Autowired
	private ITutorService tutorService;
	@Autowired
	private IClassService iClassService;
	@Autowired
	private IInstetuteService instituteService;
	@Autowired
	private ISysUserService sysUserService;
	private SysUser sysUser;
	private ClassInfo classInfo;
	private Result<SysUser> pageResult;
	private Result<TeacherSeeSelecting> teacherPageResult;
	private String message;
	private InstituteInfo instituteInfo;
	private String likeString;
	private List<InstituteInfo> instituteList; // 学院信息列表集合

	/**
	 * 打开分页(教师列表)
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String openList() throws Exception {
		sysUser.setUserType(2);
		teacherPageResult = tutorService.queryTeacherAllForAdmin(likeString,sysUser, getPage(), getRow());
		System.out.println("openList--->pageResult:"+teacherPageResult);
		setForwardView(LIST_JSP);
		return SUCCESS;
	}
	
	/**
	 * 打开分页(学生列表)
	 * 
	 * @author liuchunfu
	 * @return
	 * @throws Exception
	 */
	public String openListForStudent() throws Exception {
		sysUser.setUserType(3);
		teacherPageResult = tutorService.queryStudentAllForAdmin(likeString,sysUser, getPage(), getRow());
		System.out.println("openListForStudent--->pageResult:"+teacherPageResult);
		setForwardView(STUDENT_LIST_JSP);
		return SUCCESS;
	}

	/**
	 * 打开编辑页面（将替换为：openEditByAjax）
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String openEdit() throws Exception {
		instituteList = instituteService.findInstituteList();
		sysUser = tutorService.findByPriId(sysUser.getId());
		setForwardView(EDIT_JSP);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Description: 通过ajax返回待修改的教师/
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月9日
	 */
	@SuppressWarnings("unchecked")
	public String openEditByAjax(){
		Map<String, Object> resoultMap = new HashMap();
		instituteList = instituteService.findInstituteList();
		System.out.println("instituteList:"+instituteList);
		System.out.println("web-->sysUser.id:"+sysUser.getId());
		sysUser = tutorService.findByPriId(sysUser.getId());
		System.out.println("sysUser:"+sysUser);
		resoultMap.put("instituteList", instituteList);
		resoultMap.put("sysUser", sysUser);
		for(int i=0;i<instituteList.size();i++){
			System.out.println(instituteList.get(i).getInstituteId()+"---"+sysUser.getInstituteId());
			if(instituteList.get(i).getInstituteId().equals(sysUser.getInstituteId())){
				resoultMap.put("curentInstituteName", instituteList.get(i).getInstituteName());
				resoultMap.put("curentInstituteId", instituteList.get(i).getInstituteId());
				break;
			};
		}
		System.out.println("opendEditByAjax--->resoultMap:"+resoultMap);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(resoultMap));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 
	 * @Description: 通过ajax返回待修改的学生
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月9日
	 */
	@SuppressWarnings("unchecked")
	public String openEditForStudentByAjax(){//iClassService
		Map<String, Object> resoultMap = new HashMap();
		instituteList = instituteService.findInstituteList();
		System.out.println("instituteList:"+instituteList);
		System.out.println("web-->sysUser.id:"+sysUser.getId());
		sysUser = tutorService.findByPriId(sysUser.getId());
		System.out.println("sysUser:"+sysUser);
		resoultMap.put("instituteList", instituteList);
		resoultMap.put("sysUser", sysUser);
		ClassInfo classInFo = iClassService.findClassListByClassId(sysUser.getClassId());
		resoultMap.put("classInfo", classInFo);
		InstituteInfo instituteInfo = instituteService.findByInstituteId(classInFo.getInstituteId());
		resoultMap.put("instituteInfo", instituteInfo);
		for(int i=0;i<instituteList.size();i++){
			System.out.println(instituteList.get(i).getInstituteId()+"---"+sysUser.getInstituteId());
			if(instituteList.get(i).getInstituteId().equals(sysUser.getInstituteId())){
				resoultMap.put("curentInstituteName", instituteList.get(i).getInstituteName());
				resoultMap.put("curentInstituteId", instituteList.get(i).getInstituteId());
				break;
			};
		}
		System.out.println("opendEditByAjax--->resoultMap:"+resoultMap);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(resoultMap));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 
	 * @Description: 保存教师/学生修改信息
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月7日
	 */
	public String doEditSaveByAjax(){
		System.out.println("doEditSaveByAjax-sysUser:"+sysUser);
		boolean flag = tutorService.saveTeacherByAjax(sysUser);//true:修改成功，false:修改失败
		System.out.println("flag:"+flag);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(flag));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 打开添加教师页面
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String openAdd() throws Exception {
		instituteList = instituteService.findInstituteList();
		// System.out.println(instituteList);
		setForwardView(ADD_JSP);
		return SUCCESS;
	}

	public String backAdd() throws Exception {
		setForwardView(ADD_JSP);
		return SUCCESS;
	}

	/**
	 * 删除教师
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		SysUser delTutor = tutorService.findByPriId(sysUser.getId());
		tutorService.deleteTutor(delTutor);
		System.out.println("+_+_+:"+delTutor.getUserType());
		System.out.println("_+_+:"+delTutor.getUserType().equals(2));
		if(delTutor.getUserType().equals(2))
				return openList();
		else
				return openListForStudent();
	}

	/**
	 * 添加教师
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception {
		sysUser.setUserType(2);
		tutorService.add(sysUser);
		// System.out.println(sysUser);
		message = "教师添加成功!";
		setForwardView(ADD_JSP);
		return openAdd();
	}

	public String doQuery() throws Exception {
		try {
			tutorService.findTutorByName(sysUser);
			pageResult = tutorService.find(sysUser, getPage(), getRow());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openList();

	}

	/**
	 * 编辑教师（将替换为：doEditSaveByAjax）
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception {
		instituteList = instituteService.findInstituteList();
		try {
			tutorService.updateTutor(sysUser);
			sysUser.setUserType(null);
			sysUser.setUserName(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openList();

	}

	/**
	 * 打开学生查看教师的详情信息
	 * 
	 * @author 陆建宁
	 * @return
	 */
	public String openShow() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		sysUser = sysUserService.findPropertyByValue("userId", userId);
		if(sysUser.getUserType()==2){
			String TutorInstituteId = String.valueOf(sysUser.getInstituteId());
			instituteInfo = instituteService.findPropertyByValue("instituteId", TutorInstituteId);
			setForwardView(SHOW_JSP);
		}else{
			String stuInstituteId = String.valueOf(sysUser.getInstituteId());
			String classId = String.valueOf(sysUser.getClassId());
			instituteInfo = instituteService.findPropertyByValue("instituteId", stuInstituteId);
			classInfo = iClassService.findPropertyByValue("classId", classId);
			setForwardView(STUDENT_PERSONAL_JSP);
			return SUCCESS;
		}
		
		return SUCCESS;
	}

	public Result<TeacherSeeSelecting> getTeacherPageResult() {
		return teacherPageResult;
	}

	public void setTeacherPageResult(Result<TeacherSeeSelecting> teacherPageResult) {
		this.teacherPageResult = teacherPageResult;
	}

	public String getLikeString() {
		return likeString;
	}

	public void setLikeString(String likeString) {
		this.likeString = likeString;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<InstituteInfo> getInstituteList() {
		return instituteList;
	}

	public void setInstituteList(List<InstituteInfo> instituteList) {
		this.instituteList = instituteList;
	}


	public ITutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(ITutorService tutorService) {
		this.tutorService = tutorService;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public InstituteInfo getInstituteInfo() {
		return instituteInfo;
	}

	public void setInstituteInfo(InstituteInfo instituteInfo) {
		this.instituteInfo = instituteInfo;
	}

	public IInstetuteService getInstituteService() {
		return instituteService;
	}

	public void setInstituteService(IInstetuteService instituteService) {
		this.instituteService = instituteService;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public Result<SysUser> getPageResult() {
		return pageResult;
	}

	public void setPageResult(Result<SysUser> pageResult) {
		this.pageResult = pageResult;
	}

	@Override
	public Object getModel() {
		return sysUser;
	}

	@Override
	public void prepare() throws Exception {
		if (sysUser == null || classInfo ==null) {
			sysUser = new SysUser();
			classInfo = new ClassInfo();
		}
	}

	public IClassService getiClassService() {
		return iClassService;
	}

	public void setiClassService(IClassService iClassService) {
		this.iClassService = iClassService;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

}
