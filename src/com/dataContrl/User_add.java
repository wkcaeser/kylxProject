package com.dataContrl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class User_add extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ZH;
	private String MM;
	private String XM;
	private String XB;
	private String YX;
	private String DH;
	private String FZLX;
	public String getFZLX() {
		return FZLX;
	}
	public void setFZLX(String fZLX) {
		FZLX = fZLX;
	}
	public String getZH() {
		return ZH;
	}
	public void setZH(String zH) {
		ZH = zH;
	}
	public String getMM() {
		return MM;
	}
	public void setMM(String mM) {
		MM = mM;
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
	
	public String addKH() {
		try {
			if(ZH==null || MM==null || XM==null || XB==null || YX==null || DH==null){
				ActionContext.getContext().getSession().put("Log_add", "添加客户失败！");
				return ERROR;
			}
			if("".equals(ZH) || "".equals(MM) || "".equals(XM) || "".equals(XB) || "".equals(YX) || "".equals(DH)){
				ActionContext.getContext().getSession().put("Log_add", "添加客户失败！");
				return ERROR;
			}
			Connection connection = (Connection)ConnectDatabase.getConnect();
			String sql = "insert into KH_INF(ZH, MM, XM, XB, YX, DH) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ZH);
			preparedStatement.setString(2, MM);
			preparedStatement.setString(3, XM);
			preparedStatement.setString(4, XB);
			preparedStatement.setString(5, YX);
			preparedStatement.setString(6, DH);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActionContext.getContext().getSession().put("Log_add", "添加客户失败！");
			return ERROR;
		}
		ActionContext.getContext().getSession().put("Log_add", "添加客户成功！");
		return SUCCESS;
	}
	
	public String addGLZ() {
		try {
			if(ZH==null || MM==null || XM==null || XB==null || YX==null || DH==null){
				ActionContext.getContext().getSession().put("Log_add", "添加客户失败！");
				return ERROR;
			}
			if("".equals(ZH) || "".equals(MM) || "".equals(XM) || "".equals(XB) || "".equals(YX) || "".equals(DH)){
				ActionContext.getContext().getSession().put("Log_add", "添加客户失败！");
				return ERROR;
			}
			Connection connection = (Connection)ConnectDatabase.getConnect();
			String sql = "insert into GLZ_INF(ZH, MM, XM, XB, YX, DH) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ZH);
			preparedStatement.setString(2, MM);
			preparedStatement.setString(3, XM);
			preparedStatement.setString(4, XB);
			preparedStatement.setString(5, YX);
			preparedStatement.setString(6, DH);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActionContext.getContext().getSession().put("Log_add", "添加管理者失败！");
			return ERROR;
		}
		ActionContext.getContext().getSession().put("Log_add", "添加管理者成功！");
		return SUCCESS;
	}
	
	public String addSGZ() {
		try {
			if(ZH==null || MM==null || XM==null || XB==null || YX==null || DH==null || FZLX==null){
				ActionContext.getContext().getSession().put("Log_add", "添加施工者失败！");
				return ERROR;
			}
			if("".equals(ZH) || "".equals(MM) || "".equals(XM) || "".equals(XB) || "".equals(YX) || "".equals(DH) || "".equals(FZLX)){
				ActionContext.getContext().getSession().put("Log_add", "添加施工者失败！");
				return ERROR;
			}
			Connection connection = (Connection)ConnectDatabase.getConnect();
			String sql = "insert into SGZ_INF(ZH, MM, XM, XB, YX, DH, FZLX) values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ZH);
			preparedStatement.setString(2, MM);
			preparedStatement.setString(3, XM);
			preparedStatement.setString(4, XB);
			preparedStatement.setString(5, YX);
			preparedStatement.setString(6, DH);
			preparedStatement.setString(7, FZLX);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActionContext.getContext().getSession().put("Log_add", "添加施工者失败！");
			return ERROR;
		}
		ActionContext.getContext().getSession().put("Log_add", "添加施工者成功！");
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(ZH + " " + MM + " " + XM + " " + XB + " " + YX + " " + DH);
		return super.execute();
	}

}
