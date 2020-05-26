/**
 * ProjectSearchApiApplication: DTO class for User,Contributor
 * 
 * @author  ashish
 * @version 1.0
 * @since   2020-05-21 
 */
package com.ashish.tech.projectsearchapi.service.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 6562712754091277485L;
	private String userName;
	private String name;
	private String commits;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommits() {
		return commits;
	}

	public void setCommits(String commits) {
		this.commits = commits;
	}
	
}