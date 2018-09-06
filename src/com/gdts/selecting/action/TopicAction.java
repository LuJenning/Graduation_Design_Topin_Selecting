package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.pagination.Result;
import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.ClassInfo;
import com.gdts.selecting.entity.InstituteInfo;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.TopicInfo;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
import com.gdts.selecting.service.IClassService;
import com.gdts.selecting.service.IInstetuteService;
import com.gdts.selecting.service.ITopicService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Controller
 * 
 * @date 2018-5-23
 * @author 陆建宁
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class TopicAction extends BaseAction implements Preparable, ModelDriven {
	protected static final String LIST_JSP = "/WEB-INF/page/Topic/Topic_list.jsp";
	protected static final String ADD_JSP = "/WEB-INF/page/Topic/Topic_add.jsp";
	protected static final String EDIT_JSP = "/WEB-INF/page/Topic/Topic_edit.jsp";
	protected static final String IDEALLIST_JSP = "/WEB-INF/page/Ideal/Ideal_list.jsp";
	protected static final String TOPICSHOW_JSP = "/WEB-INF/page/Topic/Topic_show.jsp";
	protected static final String IDEALTOPIC_JSP="/WEB-INF/page/Topic/TeacherIdealTopic_list.jsp";

	private InstituteInfo instituteInfo;
	private TopicInfo topic;
	private SysUser sysUser;
	private List<TopicInfo> allTopicsList;
	private Result<TeacherSeeSelecting> pageResult;
	private Result<TopicInfo> pageResultT;
	private List<InstituteInfo> instituteList;// 学院
	private List<InstituteInfo> majorList;// 专业
	@Autowired
	private ITopicService topicService;
	@Autowired
	private IInstetuteService instituteService;
	@Autowired
	private IClassService classService;
	private ServletContext context;

	public void setServletContext(ServletContext arg0) {
		context = arg0;
	}

	/**
	 * 打开分页,显示分页页面
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String openList() throws Exception {
		SysUser currentUser = (SysUser) super.getSession().getAttribute("SysUser");
		if (currentUser.getUserType() == 3) {
			// System.out.println("用户类型:"+currentUser.getUserType());
			ClassInfo classInFo = classService.findClassByClassId(currentUser.getClassId());
			System.out.println("classInFo——+——："+classInFo);
			topic.setInstituteId(classInFo.getInstituteId());
			// System.out.println("学院id:"+currentUser.getInstituteId());
			pageResult = topicService.findForTeacherSeeSelecting(topic, getPage(), getRow());
			setForwardView(IDEALLIST_JSP);
		}
		if (currentUser.getUserType() == 2) {
			topic.setUserId(currentUser.getUserId());
			pageResult = topicService.findForTeacherSeeSelecting(topic, getPage(), getRow());
			setForwardView(LIST_JSP);
		}
		System.out.println("newPageResult:"+pageResult);
		return SUCCESS;
	}

	/**
	 * 打开发布课题页面
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String openAdd() throws Exception {
		instituteList = instituteService.findInstituteList();
		allTopicsList = topicService.findAll();
		setForwardView(ADD_JSP);
		return SUCCESS;
	}

	/**
	 * 发布课题
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception {
		if (null == request) {
			request = super.getRequest();
		}
		String maxTopicId = String.valueOf(topicService.findMaxTopicId());
		if ("[null]".equals(maxTopicId)) {
			topic.setTopicId("1000");
		} else {
			String numberString = maxTopicId.substring(1, maxTopicId.length() - 1);
			int id = Integer.valueOf(numberString) + 1;
			String topicId = String.valueOf(id);
			topic.setTopicId(topicId);

		}
		topic.setInstituteId(instituteInfo.getInstituteId());// 设置专业id
		Timestamp issueTime = new Timestamp(System.currentTimeMillis());
		topic.setIssueDate(issueTime);// 设置发布时间
		SysUser currentTutor = (SysUser) super.getSession().getAttribute("SysUser");
		topic.setUserId(currentTutor.getUserId());// 设置发布人的id
		topic.setIntNelen(0);
		topicService.addTopic(topic);
		return openAdd();
	}

	/**
	 * 打开修改页面（将替换为：openEditByAjax）
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String openEdit() throws Exception {
		String id = getParameters("id");
		instituteList = instituteService.findInstituteList();
		topic = topicService.findByPriId(Integer.parseInt(id));
		System.out.println("topic:" + topic);
		instituteInfo = instituteService.findByInstituteId(topic.getInstituteId());// 根据学院专业id，查询专业信息
		majorList = instituteService.findMajorsListByPid(instituteInfo.getInstitutePid());
		/* System.out.println("majorList:"+majorList); */
		if (topic != null) {
			setForwardView(EDIT_JSP);
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @Description: Ajax修改课题页面
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月7日
	 */
	@SuppressWarnings("unchecked")
	public String openEditByAjax() throws Exception {
		Map<String, Object> resoultMap = new HashMap();//json输出结果集
		String id = getParameters("id");
		instituteList = instituteService.findInstituteList();//学院列表
		resoultMap.put("institute", instituteList);
		topic = topicService.findByPriId(Integer.parseInt(id));//放回当前修改的课题
		resoultMap.put("topicInfo", topic);
		instituteInfo = instituteService.findByInstituteId(topic.getInstituteId());// 根据学院专业id，查询专业信息
		resoultMap.put("instituteInfo", instituteInfo);
		for(int i=0;i<instituteList.size();i++){
			System.out.println(instituteList.get(i).getInstituteId()+"---"+instituteInfo.getInstitutePid());
			if(instituteList.get(i).getInstituteId().equals(instituteInfo.getInstitutePid())){
				resoultMap.put("curentInstituteName", instituteList.get(i).getInstituteName());
				resoultMap.put("curentInstituteId", instituteList.get(i).getInstituteId());
				break;
			};
		}
		System.out.println("resoultMap:"+resoultMap);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(resoultMap));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 
	 * @Description: 保存课题修改
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月7日
	 */
	public String doEditSaveByAjax(){
		System.out.println("doEditSaveByAjax-topic:"+topic);
		boolean flag = topicService.saveTopicInfoByAjax(topic);//true:修改成功，false:修改失败
		System.out.println("flag:"+flag);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(flag));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 修改课题信息(将替换为：doEditSaveByAjax)
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception {
		Timestamp issueTime = new Timestamp(System.currentTimeMillis());
		topic.setIssueDate(issueTime);// 设置修改后的最新更新时间
		topicService.updateTopic(topic);
		topic.setTopicTitle(null);
		topic.setUserId(null);
		return openList();

	}

	/**
	 * 删除课题
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String doDelete() throws Exception {
		TopicInfo delTopicInfo = topicService.findByPriId(topic.getId());
		topicService.deleteTopic(delTopicInfo);
		return openList();
	}
	/**
	 * 学生查看课题详情页面
	 * @author 陆建宁 
	 * @return
	 * @throws Exception
	 */
	public String openShow() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		System.out.println("id是多少:"+id);
		topic=topicService.findByPriId(Integer.parseInt(id));
		System.out.println("topic又是多少:"+topic);
		setForwardView(TOPICSHOW_JSP);
		return SUCCESS;
		
	}

	/**
	 * 返回查看课题页面
	 * 
	 * @author 陆建宁
	 * @return
	 * @throws Exception
	 */
	public String backIdealList() throws Exception {
		return openList();
	}
	/**
	 * 教师查看志愿情况（打开已发布课题信息）
	 * @author 秦松
	 * @return
	 * @throws Exception
	 */
	public String openIdealTopicList() throws Exception{
		sysUser=(SysUser) ActionContext.getContext().getSession().get("SysUser");//session中取出教师信息
		pageResultT=topicService.openIdealTopicList(sysUser,getPage(),getRow());
		System.out.println("pageResultT++++l:"+pageResultT);
		forwardView=IDEALTOPIC_JSP;
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return topic;
	}

	@Override
	public void prepare() throws Exception {
		if (topic == null) {
			topic = new TopicInfo();
		}
		if (instituteInfo == null) {
			instituteInfo = new InstituteInfo();
		}

	}

	public Result<TopicInfo> getPageResultT() {
		return pageResultT;
	}

	public void setPageResultT(Result<TopicInfo> pageResultT) {
		this.pageResultT = pageResultT;
	}

	public IInstetuteService getInstituteService() {
		return instituteService;
	}

	public void setInstituteService(IInstetuteService instituteService) {
		this.instituteService = instituteService;
	}

	public List<TopicInfo> getAllTopicsList() {
		return allTopicsList;
	}

	public void setAllTopicsList(List<TopicInfo> allTopicsList) {
		this.allTopicsList = allTopicsList;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public TopicInfo getTopic() {
		return topic;
	}

	public void setTopic(TopicInfo topic) {
		this.topic = topic;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public Result<TeacherSeeSelecting> getPageResult() {
		return pageResult;
	}

	public void setPageResult(Result<TeacherSeeSelecting> pageResult) {
		this.pageResult = pageResult;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
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

	public InstituteInfo getInstituteInfo() {
		return instituteInfo;
	}

	public void setInstituteInfo(InstituteInfo instituteInfo) {
		this.instituteInfo = instituteInfo;
	}

	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

}
