/**
 * ProjectSearchApiApplication: Response  DTO class for UserId Rest call
 * 
 * @author  ashish
 * @version 1.0
 * @since   2020-05-21 
 */
package com.ashish.tech.projectsearchapi.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO implements Serializable {

	private static final long serialVersionUID = -4677079815964911438L;

	private String name;
	private List<ProjectDTO> projects = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}

}
