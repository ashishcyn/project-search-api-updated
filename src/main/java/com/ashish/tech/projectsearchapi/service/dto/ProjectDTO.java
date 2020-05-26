/**
 * ProjectSearchApiApplication: DTO class for Project
 * 
 * @author  ashish
 * @version 1.0
 * @since   2020-05-21 
 */
package com.ashish.tech.projectsearchapi.service.dto;

import java.io.Serializable;

public class ProjectDTO implements Serializable {

	private static final long serialVersionUID = -7921916044995979300L;
	private String id;
	private String url;
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title; 
	}
	

}