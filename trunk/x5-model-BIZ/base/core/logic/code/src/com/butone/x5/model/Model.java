package com.butone.x5.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 模型文件
 * interceptor.config.m,db.config.m,mapping.m,action.action.m,action.procedure.m<BR>
 * ontology.m,process.procedure.m,process.process.m
 * listener.m.listener.m,listener.m.procedure.m,
 */
@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
public class Model extends MultiLanguage {
	@XmlElement(name = "include")
	List<String> includeList = new ArrayList<String>();
	@XmlElement(name = "use")
	List<String> useList = new ArrayList<String>();

	public Model() {

	}

	public List<String> getIncludeList() {
		return includeList;
	}

	public void setIncludeList(List<String> includeList) {
		this.includeList = includeList;
	}

	public List<String> getUseList() {
		return useList;
	}

	public void setUseList(List<String> useList) {
		this.useList = useList;
	}

	public boolean containsUse(String url) {
		for (String use : this.useList) {
			if (use.equals(url))
				return true;
		}
		return false;
	}

	public boolean hasUse(String model) {
		return this.useList.contains(model);
	}

	public boolean hasInclude(String model) {
		return this.includeList.contains(model);
	}
}
