package com.util;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.data.TextMseeageData;
import com.thoughtworks.xstream.XStream;


public class MyUtilClass {
	
	/*
	 * xml  to map
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest httpServletRequest) throws IOException, DocumentException{
		Map<String, String>xmlMap = new HashMap<String, String>();
		InputStream inputStream = httpServletRequest.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		
		for(Element element : list){
			xmlMap.put(element.getName(), element.getText());
		}
		inputStream.close();
		return xmlMap;
	}

	public static String textMessageDataToXml(TextMseeageData textMseeageData) {
		XStream xStream = new XStream();
		xStream.alias("xml", textMseeageData.getClass());
		return xStream.toXML(textMseeageData);
	}
}
