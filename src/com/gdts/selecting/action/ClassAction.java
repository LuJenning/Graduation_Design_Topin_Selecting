package com.gdts.selecting.action;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdts.core.web.action.BaseAction;
import com.gdts.selecting.entity.ClassInfo;
import com.gdts.selecting.entity.InstituteInfo;
import com.gdts.selecting.service.IClassService;
import com.gdts.selecting.service.IInstetuteService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings({ "serial", "rawtypes" })
public class ClassAction extends BaseAction implements Preparable, ModelDriven{
	protected static final String CLASS_ADD="/WEB-INF/page/ClassRoom_add.jsp";
	private ClassInfo classInfo; //班级信息模型
	private InstituteInfo instituteInfo; //学院，专业信息模型
	private List<InstituteInfo> instituteList; //学院列表
	private List<InstituteInfo> majorList; //专业列表
	private List<ClassInfo> classList;
	@Autowired
	private IInstetuteService instituteService; // 注入
	@Autowired
	private IClassService classService;
	
	
	
	
	/**
	 * 跳转班级信息添加页面
	 * @author 秦松
	 * @return
	 */
	
	public String jump(){
		
		//查询学院信息列表
		instituteList=instituteService.findInstituteList();
		forwardView=CLASS_ADD;
		
		return SUCCESS;		
	}
	
	/**
	 * 添加班级信息
	 * @author 秦松
	 * @return
	 */
	public String classAdd(){
		classInfo.setInstituteId(instituteInfo.getInstituteId()); //添加专业id
		classService.classAdd(classInfo);
		System.out.println("学院id:"+instituteInfo.getInstituteId());
		forwardView=CLASS_ADD;
		return SUCCESS;
	}
	/**
	 * 根据专业id查找班级信息列表
	 * @author 秦松
	 * @return
	 */
	public String findClassListById(){
		System.out.println(classInfo.getInstituteId());
		classList = classService.findClassListById(classInfo.getInstituteId());
		PrintWriter pw = getPrintWriter();
		try {
			Gson g = new Gson();
			String json = g.toJson(classList);
			pw.print(json);
			pw.flush();
		} finally {
			pw.close();
		}
		return null;
	
	}
	
	
	
	
	
	
	public List<ClassInfo> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassInfo> classList) {
		this.classList = classList;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
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

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return classInfo;
	}

	@Override
	public void prepare() throws Exception {
		if(null == classInfo){
			classInfo = new ClassInfo();
		}
		if(null == instituteInfo){
			instituteInfo = new InstituteInfo();
		}
		
	}

}
