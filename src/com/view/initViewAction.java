package com.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class initViewAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int ID;
	String type; //用户类型
	String XM; //姓名
	String XB; //性别
	String YX; //邮箱
	String DH; //电话
	
	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public String getXB() {
		return XB;
	}

	public void setXB(String xB) {
		XB = xB;
	}

	public String getYX() {
		return YX;
	}

	public void setYX(String yX) {
		YX = yX;
	}

	public String getDH() {
		return DH;
	}

	public void setDH(String dH) {
		DH = dH;
	}


	private Boolean initUserData(){
		try {
			Connection connection = (Connection)ConnectDatabase.getConnect();
			String sql = "select * from " + type + "_INF where ID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ID);
			ResultSet rSet = preparedStatement.executeQuery();
			rSet.next();
			setXM(rSet.getString("XM"));
			setXB(rSet.getString("XB"));
			setYX(rSet.getString("YX"));
			setDH(rSet.getString("DH"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	private void setSession(Map<String, Object> session) {
		session.put("ID", ID);
		session.put("userType", type);
		session.put("XM", XM);
		session.put("XB", XB);
		session.put("YX", YX);
		session.put("DH", DH);
	}
	
	private void setSession_view(Map<String, Object> session) {
		session.put("power", "kh");
		session.put("ID_view", ID);
		session.put("userType_view", type);
		session.put("XM_view", XM);
		session.put("XB_view", XB);
		session.put("YX_view", YX);
		session.put("DH_view", DH);
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ActionContext.getContext().getSession();
		setID((int)session.get("userID"));
		setType((String)session.get("userType"));
		if(ID==0 || type==null){
			return ERROR;
		}
		if (!initUserData()) {
			return ERROR;
		}
		if("KH".equals(type)){
			setSession_view(session);
			return "khView";
		}
		setSession(session);
		return SUCCESS;
	}

}
