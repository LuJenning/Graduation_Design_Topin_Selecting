package com.gdts.core.web.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gdts.core.systemcontext.SystemContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ BaseAction.java 2015-7-26 下午04:20:18
 */
public class BaseAction extends ActionSupport {	

	private static final long serialVersionUID = -5391490758289533136L;
	protected Integer page=0;//1
	protected Integer row=0;//100
	protected String action;
	
	protected String forwardView;//自定义的跳转页面
	protected HttpServletRequest request;
	protected HttpServletResponse response;//获取response
	protected PrintWriter printWriter;//获取输出
	
    public HttpServletResponse getResponse() {
    	if(null == response){
    		response = ServletActionContext.getResponse();
            response.setContentType(SystemContext.ACTION_CONTENT_TYPE);
            response.setHeader("Cache-Control","no-cache"); 
    	}
		return response;
	}

	public PrintWriter getPrintWriter() {
    	if(null == printWriter){
    		try {
    			printWriter = getResponse().getWriter();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
		return printWriter;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPage() {
		if(page==null||page==0){
			page=1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public int getRow() {
		if(row==null||row==0){
		   row=10;
		}
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	private String[] delids;


	public String[] getDelids() {
		return delids;
	}

	public void setDelids(String[] delids) {
		this.delids = delids;
	}
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	protected String getContextPath() {
		return getRequest().getContextPath();
	}
	/**
	 * 
	 * @param paramName
	 * @return
	 */
	protected String getParameters(String paramName) {
		HttpServletRequest req =ServletActionContext.getRequest();
		return req.getParameter(paramName);
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public String getRootPath(){
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac
				.get(ServletActionContext.SERVLET_CONTEXT);
		String rootURL = sc.getRealPath("/");
		return rootURL;
	}

	public String getForwardView() {
		return forwardView;
	}

	public void setForwardView(String forwardView) {
		this.forwardView = forwardView;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
