package com.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UrlTestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String TOKEN = "wkTest";
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("get request!");
		//排序
		String sortString = sort(TOKEN, timestamp, nonce);
		//加密
		String mySignature = sha1(sortString);
		//校验签名
		 HttpServletResponse response = ServletActionContext.getResponse();  
		if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
			System.out.println("签名校验通过。");
		    //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
		    //response.getWriter().println(echostr);
		    response.getWriter().write(echostr);
		    } else {
			 System.out.println("签名校验失败.");
		}
		return null;
	}
	
	  /**
	       * 排序方法
	       *
	       * @param token
	       * @param timestamp
	       * @param nonce
	       * @return
	       */
  	public String sort(String token, String timestamp, String nonce) {
          String[] strArray = {token, timestamp, nonce};
          Arrays.sort(strArray);
          StringBuilder sb = new StringBuilder();
          for (String str : strArray) {
              sb.append(str);
          }
  
         return sb.toString();
     } 
 
      /**
       * 将字符串进行sha1加密
       *
       * @param str 需要加密的字符串
       * @return 加密后的内容
       */
      public String sha1(String str) {
          try {
              MessageDigest digest = MessageDigest.getInstance("SHA-1");
              digest.update(str.getBytes());
              byte messageDigest[] = digest.digest();
              // Create Hex String
              StringBuffer hexString = new StringBuffer();
              // 字节数组转换为 十六进制 数
              for (int i = 0; i < messageDigest.length; i++) {
                  String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                  if (shaHex.length() < 2) {
                      hexString.append(0);
                  }
                  hexString.append(shaHex);
              }
 				return hexString.toString(); 
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
          }
         return "";
      }
	
}
