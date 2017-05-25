package com.dataContrl;

import net.sf.json.JSONObject;

public class DataLog {
	private String LOG_LX;
	private String LOG_SGZ;
	private String LOG_FY;
	private String LOG_JD;
	private String LOG_CLXH;
	private String LOG_CLCS;
	private String LOG_CLJG;
	private String LOG_CLSYSL;
	private String LOG_RQ;
	private String LOG_BZ;
	private String LOG_PIC;
	public DataLog(){
		LOG_LX = "";
		LOG_SGZ = "";
		LOG_FY = "";
		LOG_JD = "";
		LOG_CLXH = "";
		LOG_CLCS = "";
		LOG_CLJG = "";
		LOG_CLSYSL = "";
		LOG_RQ = "";
		LOG_BZ = "";
		LOG_PIC = "";
	}
	public JSONObject toJson(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("LOG_LX", LOG_LX);
		jsonObject.put("LOG_SGZ", LOG_SGZ);
		jsonObject.put("LOG_FY", LOG_FY);
		jsonObject.put("LOG_JD", LOG_JD);
		jsonObject.put("LOG_CLXH", LOG_CLXH);
		jsonObject.put("LOG_CLCS", LOG_CLCS);
		jsonObject.put("LOG_CLJG", LOG_CLJG);
		jsonObject.put("LOG_CLSYSL", LOG_CLSYSL);
		jsonObject.put("LOG_RQ", LOG_RQ);
		jsonObject.put("LOG_BZ", LOG_BZ);
		jsonObject.put("LOG_PIC", LOG_PIC);
		return jsonObject;
	}
	
	public String getLOG_PIC() {
		return LOG_PIC;
	}
	public void setLOG_PIC(String lOG_PIC) {
		LOG_PIC = lOG_PIC;
	}
	public String getLOG_LX() {
		return LOG_LX;
	}
	public void setLOG_LX(String lOG_LX) {
		LOG_LX = lOG_LX;
	}
	public String getLOG_SGZ() {
		return LOG_SGZ;
	}
	public void setLOG_SGZ(String lOG_SGZ) {
		LOG_SGZ = lOG_SGZ;
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
	public String getLOG_RQ() {
		return LOG_RQ;
	}
	public void setLOG_RQ(String lOG_RQ) {
		LOG_RQ = lOG_RQ;
	}
	public String getLOG_BZ() {
		return LOG_BZ;
	}
	public void setLOG_BZ(String lOG_BZ) {
		LOG_BZ = lOG_BZ;
	}
}
