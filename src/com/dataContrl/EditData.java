package com.dataContrl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class EditData extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Type;
	private String ZH;
	private String MM;
	private String XM;
	private String XB;
	private String YX;
	private String DH;
	private String LX;
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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
	public void setDH(String dD) {
		DH = dD;
	}
	public String getLX() {
		return LX;
	}
	public void setLX(String lX) {
		LX = lX;
	}
	private void setKH() throws SQLException{
		Connection connection = (Connection) ConnectDatabase.getConnect();
		String sql = "update KH_INF set MM=?, XM=?, XB=?, YX=?, DH=? where ZH=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, MM);
		preparedStatement.setString(2, XM);
		preparedStatement.setString(3, XB);
		preparedStatement.setString(4, YX);
		preparedStatement.setString(5, DH);
		preparedStatement.setString(6, ZH);
		preparedStatement.executeUpdate();
	}
	
	private void setGLZ() throws SQLException{
		Connection connection = (Connection) ConnectDatabase.getConnect();
		String sql = "update GLZ_INF set MM=?, XM=?, XB=?, YX=?, DH=? where ZH=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, MM);
		preparedStatement.setString(2, XM);
		preparedStatement.setString(3, XB);
		preparedStatement.setString(4, YX);
		preparedStatement.setString(5, DH);
		preparedStatement.setString(6, ZH);
		preparedStatement.executeUpdate();
	}
	
	private void setSGZ() throws SQLException{
		Connection connection = (Connection) ConnectDatabase.getConnect();
		String sql = "update SGZ_INF set MM=?, XM=?, XB=?, YX=?, DH=?, LX=? where ZH=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, MM);
		preparedStatement.setString(2, XM);
		preparedStatement.setString(3, XB);
		preparedStatement.setString(4, YX);
		preparedStatement.setString(5, DH);
		preparedStatement.setString(6, LX);
		preparedStatement.setString(7, ZH);
		preparedStatement.executeUpdate();
	}
	
	public String reponseFunc(){
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if("".equals(ZH) || "".equals(MM) ||"".equals(XM) ||"".equals(XB) ||"".equals(YX) ||"".equals(DH)){
				return ERROR;
			}
			if("KH".equals(Type)){
				setKH();
			}
			else if("GLZ".equals(Type)){
				setGLZ();
			}
			else if("SGZ".equals(Type)) {
				setSGZ();
			}
			else {
				return ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		ActionContext.getContext().getSession().put("editDataLog", "修改成功!");
		return SUCCESS;
	}

}
