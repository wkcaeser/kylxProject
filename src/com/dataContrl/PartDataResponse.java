package com.dataContrl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PartDataResponse extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String part;
	ArrayList<DataLog> dataLogs;

	public PartDataResponse(){
		dataLogs = new ArrayList<>();
	}
	
	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	private int getGCID(int ID) throws SQLException{
		Connection connection = (Connection) ConnectDatabase.getConnect();
		int id = 0;
		String sql = "select GC_ID from GC_INF where KH_ID=?;";
		PreparedStatement preparedStatement =  connection.prepareStatement(sql);
		preparedStatement.setInt(1, ID);
		ResultSet rSet = preparedStatement.executeQuery();
		if(rSet.next()){
			id = rSet.getInt("GC_ID");
		}
		rSet.close();
		preparedStatement.close();
		connection.close();
		return id;
	}
	
	private String getSGZ(int ID) throws SQLException{
		Connection connection = (Connection) ConnectDatabase.getConnect();
		String name = "";
		String sql = "select XM from SGZ_INF where ID=?;";
		PreparedStatement preparedStatement =  connection.prepareStatement(sql);
		preparedStatement.setInt(1, ID);
		ResultSet rSet = preparedStatement.executeQuery();
		if(rSet.next()){
			name = rSet.getString("XM");
		}
		rSet.close();
		preparedStatement.close();
		connection.close();
		return name;
	}
	
	private boolean getData(int ID){
		try {
			int gc_id = getGCID(ID);
			if(gc_id == 0){
				return false;
			}
			
			Connection connection = (Connection) ConnectDatabase.getConnect();
			String sql = "select * from LOG_INF where LOG_LX=? and LOG_GC_ID=? order by LOG_ID desc;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, part);
			preparedStatement.setInt(2, gc_id);
			ResultSet rSet = preparedStatement.executeQuery();
			
			while(rSet.next()){
				DataLog dataLog = new DataLog();
				dataLog.setLOG_BZ(rSet.getString("LOG_BZ"));
				dataLog.setLOG_CLCS(rSet.getString("LOG_CLCS"));
				dataLog.setLOG_CLJG(rSet.getString("LOG_CLJG"));
				dataLog.setLOG_CLSYSL(rSet.getString("LOG_CLSYSL"));
				dataLog.setLOG_CLXH(rSet.getString("LOG_CLXH"));
				dataLog.setLOG_FY(rSet.getString("LOG_FY"));
				dataLog.setLOG_JD(rSet.getString("LOG_JD"));
				dataLog.setLOG_LX(rSet.getString("LOG_LX"));
				dataLog.setLOG_RQ(rSet.getString("LOG_RQ"));
				dataLog.setLOG_SGZ(getSGZ(rSet.getInt("LOG_SGZ_ID")));
				dataLog.setLOG_PIC(rSet.getString("LOG_PIC"));
				dataLogs.add(dataLog);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		//获取用户ID并获取相应数据
		int ID_view = (int) ActionContext.getContext().getSession().get("ID_view");
		getData(ID_view);
		
		//构造json
		JSONObject data = new JSONObject();
		data.put("part", part);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String power = (String)session.get("power");
		data.put("power", power);
		
		JSONArray jsonArray = new JSONArray();
		for(DataLog value : dataLogs){
			jsonArray.add(value);
		}
		
		data.put("data", jsonArray);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(data.toString());

		return null;
	}
	
}

