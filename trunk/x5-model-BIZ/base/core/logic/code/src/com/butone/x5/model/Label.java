package com.butone.x5.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

/**
 * 标签模型,用于多国语言描述
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Label {
	public static String DEFAULT_LANGUAGE = "zh_CN";
	@XmlAttribute
	String language = "zh_CN";

	@XmlValue
	String text;

	public Label() {

	}

	public Label(String text) {
		this.text = text;
	}


	public Label(String lang, String text) {
		this.language = lang;
		this.text = text;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
