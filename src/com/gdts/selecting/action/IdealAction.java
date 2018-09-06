package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.pagination.Result;
import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.Ideal;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.TopicInfo;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
import com.gdts.selecting.service.IIdealService;
import com.gdts.selecting.service.ITopicService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * Controller层
 * @author 陆建宁
 * @date 2018 5-31
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class IdealAction extends BaseAction implements Preparable, ModelDriven {
	protected static final String LIST_JSP ="/WEB-INF/page/Ideal/Ideal_list.jsp";
	protected static final String STULIST_JSP ="/WEB-INF/page/Ideal/stuIdeal_list.jsp";
	protected static final String TEACHERIDEAL_JSP="/WEB-INF/page/Ideal/TeacherLookIdeal_list.jsp"; //教师查看学生志愿填选情况页面
	protected static final String DEFAULT_INFORM_FOR_TEACHER_JSP="/WEB-INF/page/Ideal/teacherIdeal_info.jsp"; //通知教师，学生选课情况
	protected static final String DEFAULT_INFORM_FOR_STUDENT_JSP="/WEB-INF/page/Ideal/stuIdeal_info.jsp"; //通知学生，教师审核志愿情况
	protected static final String ENROLL_JSP="/WEB-INF/page/Ideal/enroll_list.jsp"; //录取名单页面
	
	
	@Autowired
	private ITopicService topicService;
	@Autowired
	private IIdealService idealService;
	private Ideal ideal;
	private SysUser sysUser;
	private TopicInfo topic;
	private TeacherSeeSelecting seeSelecting;
	private Result<TopicInfo> pageResult;
	private List<TeacherSeeSelecting> stuIdealList;
	private Result<TeacherSeeSelecting> teachLookIdealList;
	private String type;//志愿类型：first-->第一志愿,second-->第二志愿
	private int id;//课题表的id,有填报志愿ajax传递
	
	public String openList(){
		return action;
		/*SysUser currentStudent = (SysUser) super.getSession().getAttribute("SysUser");
		topic.setInstituteId(currentStudent.getInstituteId());
		System.out.println(currentStudent.getInstituteId());
		pageResult = idealService.find(topic, getPage(), getRow());
		System.out.println(pageResult);
		setForwardView(LIST_JSP);
		return SUCCESS;*/
	}
	
	/**
	 * 
	 * @Description: 志愿信息通知
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	public String defaultInform(){
		SysUser allUser = (SysUser) ActionContext.getContext().getSession().get("SysUser");
		System.out.println("userLogin---SysUser："+allUser);
		if(null != allUser){
			int num = allUser.getUserType();
			switch (num) {
				case 2:
					System.out.println("type:"+num);
					teachLookIdealList = idealService.queryIdealForTeacherInfo(allUser.getUserId(), this.getPage(), this.getRow());
					this.setForwardView(DEFAULT_INFORM_FOR_TEACHER_JSP);//根据用户类型分发，通知教师
					break;
				case 3:
					System.out.println("type:"+num);
					stuIdealList = idealService.queryIdealByStuId(allUser.getUserId());
					System.out.println("stuIdealList:"+stuIdealList);
					this.setForwardView(DEFAULT_INFORM_FOR_STUDENT_JSP);//根据用户类型分发,通知学生
					break;
			}
		};
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Description: 通过ajax填报志愿
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月11日
	 */
	public String saveIdealByAjax(){
		System.out.println("this.getType():"+this.getType());
		System.out.println("topic:"+topic.getId());
		sysUser=(SysUser)ActionContext.getContext().getSession().get("SysUser");
		seeSelecting = topicService.queryTeacherSeeSelectingForStudent(topic);
		if(null != seeSelecting){
			//ideal.setIdealId(idealId);
			ideal.setIdealType(this.getType());//志愿类型：first-->第一志愿,second-->第二志愿
			ideal.setTopicId(seeSelecting.getTopicId());
			ideal.setUserId(sysUser.getUserId());
			Timestamp issueTime = new Timestamp(System.currentTimeMillis());
			ideal.setSelectDate(issueTime);
			ideal.setInstituteId(seeSelecting.getInstituteId());
			ideal.setIsAccept(3);//默认为3待录取
		};
		Integer num = idealService.addIdeal(ideal);
		System.out.println("saveIdealByAjax-->num:"+num);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		if(null !=num && 0<num) {
			topic.setId(seeSelecting.getTopicInfoId());
			topic.setIntNelen(seeSelecting.getIntNelen()+1);
			System.out.println("topc"+topic);
			topicService.updateTopicForStudentChecked(topic);
			out.print(gson.toJson(true));
			System.out.println("saveIdealByAjax-->"+gson.toJson(true));
		}else{
			out.print(gson.toJson(false));
			System.out.println("saveIdealByAjax-->"+gson.toJson(false));
		};
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 
	 * @Description: 学生报考志愿时，先判断是否是已选的志愿
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	public String seacherIsSelectIdeal(){
		SysUser allUser = (SysUser) ActionContext.getContext().getSession().get("SysUser");
		System.out.println("userLogin---SysUser："+allUser);
		ideal.setUserId(allUser.getUserId());
		System.out.println("seacherIsSelectIdeal-->ideal:"+ideal);
		List<Ideal> list = idealService.seacherIsSelectIdeal(ideal);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("data", list);
		boolean flag = false;
		if(null != list && 0<list.size()){
			flag = true;
		}
		resMap.put("falg", flag);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(resMap));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 学生查看已选志愿列表
	 * @author 秦松
	 * @return
	 * @throws Exception
	 */
	public String IdealByStudent() throws Exception{
		sysUser=(SysUser) ActionContext.getContext().getSession().get("SysUser");//从session中取出sysUser对象
		stuIdealList=idealService.queryIdealByStuId(sysUser.getUserId()); //已选志愿列表（只有两条志愿）
		System.out.println("ideal++++:"+stuIdealList);
		forwardView=STULIST_JSP;
		return SUCCESS;
	}
	/**
	 * 教师查看志愿选择情况
	 * @author 秦松
	 * @return
	 * @throws Exception
	 */
	public String IdealByTeacher() throws Exception{
		String topicId=getParameters("topicId"); //传统接收参数法，哈哈哈哈哈哈哈哈
		teachLookIdealList=idealService.IdealByTeacher(topicId,getPage(),getRow());
		System.out.println("teachLookIdealList++_+_+_+:"+teachLookIdealList);
		forwardView=TEACHERIDEAL_JSP;
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Description: 教师审核学生志愿：---》同意志愿
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public String agreeIdeal(){
		ideal.setIsAccept(1);
		boolean flag = idealService.agreeIdeal(ideal);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(flag));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 
	 * @Description: 教师审核学生志愿：---》退档志愿
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public String ctrlIdeal(){
		ideal.setIsAccept(2);
		boolean flag = idealService.agreeIdeal(ideal);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(flag));
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 
	 * @Description: 教师查看录取名单（enroll就是录取的意思）
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author Tony
	 * @date 2018年6月18日
	 */
	public String enrollList() throws Exception{
		sysUser=(SysUser) ActionContext.getContext().getSession().get("SysUser");
		teachLookIdealList=idealService.enrollList(sysUser.getUserId(),getPage(),getRow());//录取名单列表
		System.out.println("teachLookIdealList:"+teachLookIdealList);
		forwardView=ENROLL_JSP;
		return SUCCESS;
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return ideal;
	}

	@Override
	public void prepare() throws Exception {
		if(ideal == null || sysUser ==null || topic == null){
			ideal = new Ideal();
			sysUser = new SysUser();
			topic = new TopicInfo();
		}
		if(null == seeSelecting){
			seeSelecting = new TeacherSeeSelecting();
		};
		
	}
	public IIdealService getIdealService() {
		return idealService;
	}

	public void setIdealService(IIdealService idealService) {
		this.idealService = idealService;
	}


	

	public Ideal getIdeal() {
		return ideal;
	}

	public void setIdeal(Ideal ideal) {
		this.ideal = ideal;
	}

	public TopicInfo getTopic() {
		return topic;
	}

	public void setTopic(TopicInfo topic) {
		this.topic = topic;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}


	public Result<TopicInfo> getPageResult() {
		return pageResult;
	}
	public void setPageResult(Result<TopicInfo> pageResult) {
		this.pageResult = pageResult;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public TeacherSeeSelecting getSeeSelecting() {
		return seeSelecting;
	}

	public void setSeeSelecting(TeacherSeeSelecting seeSelecting) {
		this.seeSelecting = seeSelecting;
	}

	public List<TeacherSeeSelecting> getStuIdealList() {
		return stuIdealList;
	}

	public void setStuIdealList(List<TeacherSeeSelecting> stuIdealList) {
		this.stuIdealList = stuIdealList;
	}

	public Result<TeacherSeeSelecting> getTeachLookIdealList() {
		return teachLookIdealList;
	}

	public void setTeachLookIdealList(Result<TeacherSeeSelecting> teachLookIdealList) {
		this.teachLookIdealList = teachLookIdealList;
	}



	
	
}
