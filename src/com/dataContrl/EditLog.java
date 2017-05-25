package com.dataContrl;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ConnectDatabase;

import net.sf.json.JSONObject;

public class EditLog extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int LOG_GC_ID;
	private String LOG_LX;
	private String LOG_SGZ_ZH;
	private String LOG_FY;
	private String LOG_JD;
	private String LOG_CLXH;
	private String LOG_CLCS;
	private String LOG_CLJG;
	private String LOG_CLSYSL;
	private String LOG_BZ;
	
	
	private File pic; //file控件名
	private String picContentType;//图片类型
	private String picFileName; //文件名
	
	 
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	
	public EditLog(){
		LOG_LX = "";
		LOG_SGZ_ZH = "";
		LOG_FY = "";
		LOG_JD = "";
		LOG_CLXH = "";
		LOG_CLCS = "";
		LOG_CLJG = "";
		LOG_CLSYSL = "";
		LOG_BZ = "";
		picContentType = "";
		picFileName = "";
	}
	public int getLOG_GC_ID() {
		return LOG_GC_ID;
	}
	public void setLOG_GC_ID(int lOG_GC_ID) {
		LOG_GC_ID = lOG_GC_ID;
	}
	public String getLOG_LX() {
		return LOG_LX;
	}
	public void setLOG_LX(String lOG_LX) {
		LOG_LX = lOG_LX;
	}
	public String getLOG_SGZ_ZH() {
		return LOG_SGZ_ZH;
	}
	public void setLOG_SGZ_ZH(String lOG_SGZ_ZH) {
		LOG_SGZ_ZH = lOG_SGZ_ZH;
	}
	public String getLOG_FY() {
		return LOG_FY;
	}
	public void setLOG_FY(String lOG_FY) {
		LOG_FY = lOG_FY;
	}
	public String getLOG_JD() {
		return LOG_JD;
	}
	public void setLOG_JD(String lOG_JD) {
		LOG_JD = lOG_JD;
	}
	public String getLOG_CLXH() {
		return LOG_CLXH;
	}
	public void setLOG_CLXH(String lOG_CLXH) {
		LOG_CLXH = lOG_CLXH;
	}
	public String getLOG_CLCS() {
		return LOG_CLCS;
	}
	public void setLOG_CLCS(String lOG_CLCS) {
		LOG_CLCS = lOG_CLCS;
	}
	public String getLOG_CLJG() {
		return LOG_CLJG;
	}
	public void setLOG_CLJG(String lOG_CLJG) {
		LOG_CLJG = lOG_CLJG;
	}
	public String getLOG_CLSYSL() {
		return LOG_CLSYSL;
	}
	public void setLOG_CLSYSL(String lOG_CLSYSL) {
		LOG_CLSYSL = lOG_CLSYSL;
	}
	public String getLOG_BZ() {
		return LOG_BZ;
	}
	public void setLOG_BZ(String lOG_BZ) {
		LOG_BZ = lOG_BZ;
	}
	
	private int getSGZ_ID() throws SQLException{
		Connection connection = (Connection) ConnectDatabase.getConnect();
		int id = 0;
		String sql = "select ID from SGZ_INF where ZH=?;";
		PreparedStatement preparedStatement =  connection.prepareStatement(sql);
		preparedStatement.setString(1, LOG_SGZ_ZH);
		ResultSet rSet = preparedStatement.executeQuery();
		if(rSet.next()){
			id = rSet.getInt("ID");
		}
		rSet.close();
		preparedStatement.close();
		connection.close();
		return id;
	}
	
	private String getRQ(){
		String date = "";
 
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.format(d).toString();
		return date;
	}
	
	private String savePic(){
		String root = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/upload");
		
		String names[] = picFileName.split("\\.");
	    String newName="";
	    if(names.length>=1){
	    	Calendar cal = Calendar.getInstance();
	    	int y=cal.get(Calendar.YEAR);      
	        int m=cal.get(Calendar.MONTH);      
	        int d=cal.get(Calendar.DATE);      
	        int h=cal.get(Calendar.HOUR_OF_DAY);      
	        int mi=cal.get(Calendar.MINUTE);      
	        int s=cal.get(Calendar.SECOND);  
	        newName= "" + y + m + d + h + mi + s + "." + names[names.length-1];
	    }
		String url = "upload/" + newName;
		
		System.out.println(root+newName);
        try {
            FileUtils.copyFile(pic, new File(root,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return url;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
		Connection connection = (Connection)ConnectDatabase.getConnect();
		String sql = "insert into LOG_INF(LOG_GC_ID, "
				+ "LOG_LX, "
				+ "LOG_SGZ_ID, "
				+ "LOG_FY, "
				+ "LOG_JD, "
				+ "LOG_CLXH, "
				+ "LOG_CLCS, "
				+ "LOG_CLJG, "
				+ "LOG_CLSYSL, "
				+ "LOG_RQ, "
				+ "LOG_BZ, "
				+ "LOG_PIC"
				+ ")"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, LOG_GC_ID);
		preparedStatement.setString(2, LOG_LX);
		preparedStatement.setInt(3, getSGZ_ID());
		preparedStatement.setString(4, LOG_FY);
		preparedStatement.setString(5, LOG_JD);
		preparedStatement.setString(6, LOG_CLXH);
		preparedStatement.setString(7, LOG_CLCS);
		preparedStatement.setString(8, LOG_CLJG);
		preparedStatement.setString(9, LOG_CLSYSL);
		preparedStatement.setString(10, getRQ());
		preparedStatement.setString(11, LOG_BZ);
		if(pic != null){
			preparedStatement.setString(12, savePic());
		}
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(jsonObject.toString());
		}catch(Exception e){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().write(jsonObject.toString());
		}
		return null;
	}
}
