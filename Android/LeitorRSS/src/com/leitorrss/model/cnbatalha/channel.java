package com.leitorrss.model.cnbatalha;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("channel")
public class channel {

	public String title;
	
	public String link;
	
	public String description;
	
	public String language;
	
	public String category;
	
	public String copyright;
	
	@XStreamOmitField()
	public Object image;

	@XStreamImplicit(itemFieldName="item")
	public List<item> items;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public List<item> getItems() {
		return items;
	}

	public void setItems(List<item> items) {
		this.items = items;
	}

}
