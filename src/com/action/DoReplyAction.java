package com.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.data.TextMseeageData;
import com.dataBase.NewLog;
import com.dataBase.WXKHBind;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MyUtilClass;

public class DoReplyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
		httpServletRequest.setCharacterEncoding("UTF-8");
		
		Map<String, String>map = MyUtilClass.xmlToMap(httpServletRequest);
		String ToUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		String MsgType = map.get("MsgType");
		String Content = map.get("Content");
		
//		System.out.println(FromUserName);
		
		String message = "";
		String contentStr = "";

		if("newlog".equals(Content)){
			contentStr = NewLog.GetLog(FromUserName);
			contentStr += "详情请点击：http://www.wkcaeser.com/kylxProject \n";
		}
		else{
			String[] doString = Content.split(" ");
			if("bind".equals(doString[0])){
				contentStr = WXKHBind.bindWX(doString[1], doString[2], FromUserName);
				if("true".equals(contentStr)){
					contentStr = "绑定成功！";
				}
				else if("false".equals(contentStr)){
					contentStr = "绑定失败！";
				}
			}
			else{
				contentStr = "查询请输入：newlog \n绑定账号请输入： bind 用户名 密码 \n";
			}
		}
			
		TextMseeageData textMseeageData = new TextMseeageData();
		textMseeageData.setFromUserName(ToUserName);
		textMseeageData.setToUserName(FromUserName);
		textMseeageData.setMsgType("text");
		textMseeageData.setCreateTime(new Date().getTime());
		textMseeageData.setContent(contentStr);
		message = MyUtilClass.textMessageDataToXml(textMseeageData);
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.getWriter().write(message);
		
		return null;
	}
	
	
}
