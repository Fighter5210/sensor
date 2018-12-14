package com.dy.sensor.common.support;

import java.io.File;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * XML解析通用工具类
 * @author SunHailong
 */
public class XmlParser {
	
	Element rootElement = null; // XML的顶级节点

	/**
	 * 创建实例
	 * 生成rootElement
	 * @param xmlFile XML文件信息
	 * @param isPath 是否路径
	 */
	public XmlParser(String xmlFile, boolean isPath) {
		if(isPath) { // 如果是XML文件路径，则通过文件方式生成Element
			setRootElementByPath(xmlFile);
		} else { // 如果是XML字符串，则直接将XML内容读入并生成Element
			setRootElementByXml(xmlFile);
		}
	}

	/**
	 * 创建实例
	 * @param filePath
	 */
	public XmlParser(String filePath) {
		setRootElementByPath(filePath);
	}
	
	/**
	 * 通过文件方式生成Element
	 * @param filePath
	 */
	private void setRootElementByPath(String filePath) {
		String m_FilePath = this.getCheckStr(filePath);
		try {
			File file = new File(m_FilePath);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(file);
			rootElement = doc.getRootElement();
		} catch (Exception e) {
			System.out.println("filePath=" + filePath);
			e.printStackTrace();
		}
	}

	/**
	 * 直接将XML内容读入并生成Element
	 * @param fileXml
	 */
	private void setRootElementByXml(String fileXml) {
		StringReader read = new StringReader(this.getCheckStr(fileXml));
		InputSource source = new InputSource(read);
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(source);
			rootElement = doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取指定标签的属性值
	 * @param catalog
	 * @param atrName
	 * @return
	 */
	public String getAttributeValue(String catalog, String atrName) {
		Element element = rootElement;
		String tags[] = null;
		int len = 0;
		if (!"".equals(this.getCheckStr(catalog))) {
			tags = catalog.split("#");
			len = tags.length;
		}
		try {
			for (int i = 0; i < len; i++) {
				element = element.getChild(tags[i]);
				if (element == null) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String text = "";
		if (element != null) {
			text = element.getAttributeValue(atrName);
		}
		return text;
	}

	/**
	 * 获取制定标签的值
	 * @param catalog
	 * @return
	 */
	public String getValue(String catalog) {
		Element element = rootElement;
		String tags[] = null;
		int len = 0;
		if (!"".equals(this.getCheckStr(catalog))) {
			tags = catalog.split("#");
			len = tags.length;
		}
		try {
			for (int i = 0; i < len; i++) {
				element = element.getChild(tags[i]);
				if (element == null) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String text = "";
		if (element != null) {
			text = element.getText();
		}
		return text;
	}

	/**
	 * 获取指定标签的Element集合
	 * @param catalog
	 * @return
	 */
	public List<Element> getElement(String catalog) {
		return getElement(null, catalog);
	}

	/**
	 * 获取指定目录下指定标签的集合
	 * @param catalog
	 * @return
	 */
	public List<Element> getElement(Element p_element, String catalog) {
		List list = null;
		Element element = rootElement;
		if (p_element == null) {
			element = rootElement;
		} else {
			element = p_element;
		}
		try {
			list = element.getChildren(catalog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定标签下的Element集合
	 * @param catalog
	 * @return
	 */
	public List<Element> getChildren(String catalog) {
		return getChildren(null, catalog);
	}

	/**
	 * 获取指定目录下指定标签下的集合
	 * @param catalog
	 * @return
	 */
	public List<Element> getChildren(Element p_element, String catalog) {
		Element element = rootElement;
		if (p_element == null) {
			element = rootElement;
		} else {
			element = p_element;
		}
		String tags[] = null;
		int len = 0;
		if (!"".equals(this.getCheckStr(catalog))) {
			tags = catalog.split("#");
			len = tags.length;
		}
		try {
			for (int i = 0; i < len; i++) {
				element = element.getChild(tags[i]);
				if (element == null) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List list = null;
		if (element != null) {
			list = element.getChildren();
		}
		return list;
	}
	
	private String getCheckStr(String str) {
		if(str == null) {
			str = "";
		}
		return str;
	}
}
