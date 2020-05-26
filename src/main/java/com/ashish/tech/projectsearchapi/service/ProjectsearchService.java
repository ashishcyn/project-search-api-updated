/**
 * ProjectSearchApiApplication: ProjectsearchService 
 * 
 * @author  ashish
 * @version 1.0
 * @since   2020-05-21 
 */
package com.ashish.tech.projectsearchapi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.ashish.tech.projectsearchapi.service.dto.ProjectDTO;
import com.ashish.tech.projectsearchapi.service.dto.UserDTO;

@Validated
public interface ProjectsearchService {

	/*
	 * Get User Details
	 * 
	 * @param username
	 * 
	 * @return UserDTO
	 */

	@NotNull(message = "username should not be null")
	UserDTO getUserDetails(@Valid String username) throws Exception;

	/*
	 * Get Project Details
	 * 
	 * @param username
	 * 
	 * @return Projects
	 */
	@NotNull(message = "username should not be null")
	List<ProjectDTO> getAllProjects(@Valid String username) throws Exception;

	/*
	 * Get Contributor Details
	 * 
	 * @param username
	 * 
	 * @param projectid
	 * 
	 * @return Contributors
	 */
	@NotNull(message = "username and projectid should not be null")
	List<UserDTO> getAllContributors(@Valid String username, @Valid String projectid) throws Exception;

	/*
	 * Get readme data
	 * 
	 * @param username
	 * 
	 * @param projectid
	 * 
	 * @return readme data in a String
	 */
	@NotNull(message = "username and projectid should not be null")
	String getReadmeData(@Valid String username, @Valid String projectid) throws Exception;

}
