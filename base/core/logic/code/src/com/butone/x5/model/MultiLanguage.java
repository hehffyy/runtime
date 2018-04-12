package com.butone.x5.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 多国语言列表
 * 
 * @author Administrator
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MultiLanguage {
	@XmlElement(name = "label")
	List<Label> labels = new ArrayList<Label>();

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabel(List<Label> labels) {
		this.labels = labels;
	}

	public void setLabel(String language, String text) {
		Label l = findLabel(language);
		if (l == null) {
			this.getLabels().add(new Label(language, text));
		} else {
			l.setText(text);
		}
	}

	public void setLabel(String text) {
		this.setLabel(Label.DEFAULT_LANGUAGE, text);
	}

	public String getLabel(String language) {
		Label l = findLabel(language);
		if (l != null)
			return l.getText();
		return null;
	}

	public String getLabel() {
		return getLabel(Label.DEFAULT_LANGUAGE);
	}

	public Label findLabel(String language) {
		for (int i = 0; i < labels.size(); i++) {
			Label l = labels.get(i);
			if (language.equals(l.getLanguage())) {
				return l;
			}
		}
		return null;
	}
}
