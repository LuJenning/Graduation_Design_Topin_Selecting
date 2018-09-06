package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.pagination.Result;
import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.InstituteInfo;
import com.gdts.selecting.service.IInstetuteService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings({ "serial", "rawtypes" })
public class InstituteAction extends BaseAction implements Preparable,ModelDriven{
	protected static final String INSTITUTE_ADD ="/WEB-INF/page/Institute_add.jsp";
	protected static final String MAJOR_ADD ="/WEB-INF/page/Major_add.jsp";
	protected static final String INSTITUTE_LIST="/WEB-INF/page/Institute_list.jsp";
	
	@Autowired
	private IInstetuteService instituteService;
	private InstituteInfo instituteInfo; //学院模型
	private List<InstituteInfo> instituteList; //学院信息列表集合
	private List<InstituteInfo> majorList;	//专业列表
	private Result<InstituteInfo> pageResult; //分页列表
	//private Integer page;
	//private Integer row;
	
	
	/*public void setPage(Integer page) {
		this.page = page;
	}
	public int getPage() {
		if(page==null||page==0){
			page=1;
		}
		return page;
	}
	
	public void setRow(Integer row){
		this.row = row;
	}
	public int getRow() {
		if(row==null||row==0){
			row=6;
		}
		return page;
	}*/
	/**
	 * 添加学院信息以及专业信息，共用此方法
	 * @return
	 * @author 秦松
	 * @throws Exception
	 */
	public String instituteAdd() throws Exception{
		
		instituteService.instituteAdd(instituteInfo);
		System.out.println("instituteInfo:"+instituteInfo);
		System.out.println("name:"+instituteInfo.getInstituteName());
		//instituteInfo.setInstituteName(null);
		forwardView=INSTITUTE_ADD;
		return SUCCESS;
		
	}
	/**
	 * 打开添加专业页面，下拉框获取学院列表
	 * @return
	 */
	public String openMajorAdd(){
		instituteList = instituteService.findInstituteList();//获取学院信息
		forwardView=MAJOR_ADD;
		return SUCCESS;
	}
	/**
	 * 根据学院ID查询专业列表
	 * @return
	 * @author 秦松
	 * @throws Exception
	 */
	public String findMajorsListById()throws Exception{
		System.out.println(instituteInfo.getInstituteId());
		majorList = instituteService.findMajorsListByPid(instituteInfo.getInstituteId());
		PrintWriter pw = getPrintWriter();
		try {
			Gson g = new Gson();
			String json = g.toJson(majorList);
			pw.print(json);
			pw.flush();
		} finally {
			pw.close();
		}
		return null;
	}
	/**
	 * 这个是添加学院页面链接，测试用，勿删！
	 * @return
	 */
	public String jump(){
		forwardView=INSTITUTE_ADD;
		return SUCCESS;
	}
	/**
	 * 分页查询学院列表
	 * @author 秦松
	 * @return
	 * @throws Exception
	 */
	public String institutePageList() throws Exception{
		/*instituteInfo.setInstitutePid(0);*/
		pageResult=instituteService.institutePageList(instituteInfo,getPage(),getRow()); //getPage=第几页，getRow=一页显示多少条记录数
		System.out.println("pageResult:"+pageResult);
		forwardView=INSTITUTE_LIST;
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Description: 通过ajax返回待修改的学院信息
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public String getInstituteForAjax(){
		System.out.println("getInstituteForAjax--->instituteInfo:"+instituteInfo);
		InstituteInfo newInstituteInfo = instituteService.findByInstituteId(instituteInfo.getInstituteId());
		System.out.println("newInstituteInfo:"+newInstituteInfo);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(newInstituteInfo));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 
	 * @Description: 异步更新学院或专业信息
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public String doEditInstituteSaveByAjax(){
		System.out.println("doEditInstituteSaveByAjax--->instituteInfo:"+instituteInfo);
		boolean flag = instituteService.instituteUpdate(instituteInfo);
		System.out.println("doEditInstituteSaveByAjax-->:"+flag);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(flag));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 
	 * @Description: 通过ajax删除学院或专业
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public String doInstituteDeleteByAjax(){
		System.out.println("doInstituteDeleteByAjax--->instituteInfo:"+instituteInfo);
		boolean flag = instituteService.deleteInstituteInfo(instituteInfo);
		System.out.println("doInstituteDeleteByAjax-->:"+flag);
		PrintWriter out = this.getPrintWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(flag));
		out.flush();
		out.close();
		return null;
	}
	
	//setterand getter 方法全部写在最下方
	
	
	public InstituteInfo getInstituteInfo() {
		return instituteInfo;
	}
	public Result<InstituteInfo> getPageResult() {
		return pageResult;
	}
	public void setPageResult(Result<InstituteInfo> pageResult) {
		this.pageResult = pageResult;
	}
	public List<InstituteInfo> getMajorList() {
		return majorList;
	}
	public void setMajorList(List<InstituteInfo> majorList) {
		this.majorList = majorList;
	}
	public List<InstituteInfo> getInstituteList() {
		return instituteList;
	}
	public void setInstituteList(List<InstituteInfo> instituteList) {
		this.instituteList = instituteList;
	}
	public void setInstituteInfo(InstituteInfo instituteInfo) {
		this.instituteInfo = instituteInfo;
	}
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return instituteInfo;
	}

	@Override
	public void prepare() throws Exception {
		if(null == instituteInfo){
			instituteInfo = new InstituteInfo();
		}
		
	}

}
