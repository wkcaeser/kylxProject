package com.login;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userType;
	private String userName;
	private String userPassword;
	private String login;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	//用户名或密码是否为空检查
	private boolean checkInput() {
		if(userName==null || userPassword==null || 
			userName.trim().equals("") || userPassword.trim().equals(""))
			return false;
		else
			return true;
	}
	
	private void loginLog() {
		String loginLog = "请检查用户名和密码是否正确！";
		ActionContext.getContext().getSession().put("loginLog", loginLog);
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		if(!checkInput()){
			loginLog();
			return ERROR;
		}
		if(CheckLoginData.checkUser(userType, userName, userPassword)){
			if("SGZ".equals(userType)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", 1);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.getWriter().write(jsonObject.toString());
				return null;
			}
			if(userType.equals("GLZ") && "sa".equals(userName)){
				ActionContext.getContext().getSession().put("topPower", 1);
			}
			return SUCCESS;
		}
		else{
			if("SGZ".equals(userType)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", 0);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.getWriter().write(jsonObject.toString());
				return null;
			}
			loginLog();
			return ERROR;
		}
	}	

}
