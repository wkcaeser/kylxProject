package com.dataContrl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

public class UserAllData extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject responseData;
	
	public UserAllData(){
		responseData = new JSONObject();
	}

	private boolean getKHData(){
		Connection connection;
		try {
			connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select * from KH_INF";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		JSONArray khDataArray = new JSONArray();
		while(rSet.next()){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", rSet.getString("ID"));
			jsonObject.put("zh", rSet.getString("ZH"));
			jsonObject.put("xm", rSet.getString("XM"));
			jsonObject.put("xb", rSet.getString("XB"));
			jsonObject.put("mm", rSet.getString("MM"));
			jsonObject.put("yx", rSet.getString("YX"));
			jsonObject.put("dh", rSet.getString("DH"));
			khDataArray.put(jsonObject);
		}
			
		responseData.put("khData", khDataArray);
		rSet.close();
		preparedStatement.close();
		connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private boolean getGLZData(){
		Connection connection;
		try {
			connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select * from GLZ_INF";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		JSONArray glzDataArray = new JSONArray();
		while(rSet.next()){
			if("sa".equals(rSet.getString("ZH"))){
				continue;
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", rSet.getString("ID"));
			jsonObject.put("zh", rSet.getString("ZH"));
			jsonObject.put("xm", rSet.getString("XM"));
			jsonObject.put("xb", rSet.getString("XB"));
			jsonObject.put("mm", rSet.getString("MM"));
			jsonObject.put("yx", rSet.getString("YX"));
			jsonObject.put("dh", rSet.getString("DH"));
			glzDataArray.put(jsonObject);
		}
			
		responseData.put("glzData", glzDataArray);
		rSet.close();
		preparedStatement.close();
		connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean getSGZData(){
		try {
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select * from SGZ_INF";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		JSONArray sgzDataArray = new JSONArray();
		while(rSet.next()){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", rSet.getString("ID"));
			jsonObject.put("zh", rSet.getString("ZH"));
			jsonObject.put("xm", rSet.getString("XM"));
			jsonObject.put("xb", rSet.getString("XB"));
			jsonObject.put("mm", rSet.getString("MM"));
			jsonObject.put("yx", rSet.getString("YX"));
			jsonObject.put("dh", rSet.getString("DH"));
			jsonObject.put("fzlx", rSet.getString("FZLX"));
			sgzDataArray.put(jsonObject);
		}
		responseData.put("sgzData", sgzDataArray);
		rSet.close();
		preparedStatement.close();
		connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String getName(int id, String type) throws SQLException{
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select XM from " + type + "_INF where ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet rSet = preparedStatement.executeQuery();
		if(rSet.next()){
			String name = rSet.getString("XM");
			return name;
		}
		return null;
	}
	
	private boolean getGCData(){
		try{
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "select * from GC_INF";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		JSONArray gcDataArray = new JSONArray();
		while(rSet.next()){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("gc_id", rSet.getInt("GC_ID"));
			jsonObject.put("glz_id", getName(rSet.getInt("GLZ_ID"), "GLZ"));
			jsonObject.put("kh_id", getName(rSet.getInt("KH_ID"), "KH"));
			jsonObject.put("gc_dd", rSet.getString("gc_dd"));
			gcDataArray.put(jsonObject);
		}
		responseData.put("gcData", gcDataArray);
		rSet.close();
		preparedStatement.close();
		connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(getGCData() && getKHData() && getSGZData() && getGLZData()){
//			System.out.println(responseData.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(responseData.toString());
		}
		
		return null;
	}

}
