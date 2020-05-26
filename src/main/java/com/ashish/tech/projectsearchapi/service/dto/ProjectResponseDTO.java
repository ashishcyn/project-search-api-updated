/**
 * ProjectSearchApiApplication: DTO class for Project details  Rest call
 * 
 * @author  ashish
 * @version 1.0
 * @since   2020-05-21 
 */
package com.ashish.tech.projectsearchapi.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectResponseDTO implements Serializable {

	private static final long serialVersionUID = -4677079815964911438L;

	private List<UserDTO> contributors = new ArrayList<>();
	private String readme;

	public List<UserDTO> getContributors() {
		return contributors;
	}

	public void setContributors(List<UserDTO> contributors) {
		this.contributors = contributors;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

}
