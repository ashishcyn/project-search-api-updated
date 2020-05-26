/**
* <h1>Github project search API</h1>
* The Github project search program implements an application that
* fetch the github repo details based on the username and project id.
* <p>
* We have two rest end points for this, one is to fetch user's full name 
* and repos associated with the user and the other one is to fetch the details of a 
* particular repo
* 
*
* @author  ashish
* @version 1.0
* @since   2020-05-21 
*/
package com.ashish.tech.projectsearchapi.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.tech.projectsearchapi.service.ProjectsearchService;
import com.ashish.tech.projectsearchapi.service.dto.ProjectDTO;
import com.ashish.tech.projectsearchapi.service.dto.ProjectResponseDTO;
import com.ashish.tech.projectsearchapi.service.dto.UserDTO;
import com.ashish.tech.projectsearchapi.service.dto.UserResponseDTO;

@RestController
public class ProjectsearchController {

	@Autowired
	ProjectsearchService projectsearchService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsearchController.class);

	/**
	 * Getting the Name and project details of a git user
	 * 
	 * @param username
	 * @return UserResponseDTO 
	 * 
	 */
	@RequestMapping(value = "project-search-api/projects/{username}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	public ResponseEntity<UserResponseDTO> getAllprojects(@PathVariable String username) {

		LOGGER.info("Inside API:getAllprojects");
		UserResponseDTO responseDTO = new UserResponseDTO();
		List<ProjectDTO> projects = new ArrayList<>();
		UserDTO user = null;

		if (null != username) {
			// Getting the Name of the user
			try {
				user = projectsearchService.getUserDetails(username);
			} catch (Exception e) {
				LOGGER.error("An exception occurred while getting the user details", e);
				return new ResponseEntity<UserResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
			}
			// Getting all the repos of the user
			try {
				projects = projectsearchService.getAllProjects(username);
			} catch (Exception e) {
				LOGGER.error("An exception occurred while getting the project  details", e);
				return new ResponseEntity<UserResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
			}

			if (null != user && null != projects) {
				responseDTO.setName(user.getName());
				responseDTO.setProjects(projects);
			} else {
				return new ResponseEntity<UserResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<UserResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);

		}
		LOGGER.info("Returing the API response");
		return new ResponseEntity<UserResponseDTO>(responseDTO, HttpStatus.OK);

	}

	/**
	 * Getting 50-word excerpt from the project’s readme.md, list of contributors
	 * and number of total commits for a project.
	 * 
	 * @param username
	 * @param projectid
	 * @return ProjectResponseDTO
	 */

	@RequestMapping(value = "project-search-api/projects/{username}/{projectid}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	public ResponseEntity<ProjectResponseDTO> getProjectDetails(@PathVariable String username,
			@PathVariable String projectid) {

		LOGGER.info("Inside API:getProjectDetails");

		ProjectResponseDTO responseDTO = new ProjectResponseDTO();
		List<UserDTO> contributors = new ArrayList<>();
		String readmeData = "";

		if (null != username && null != projectid) {
			// Getting all contributors
			try {
				contributors = projectsearchService.getAllContributors(username, projectid);
			} catch (Exception e) {
				LOGGER.error("An exception occurred while getting the contributors", e);
				return new ResponseEntity<ProjectResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
			}
			// Getting the readme data
			try {
				readmeData = projectsearchService.getReadmeData(username, projectid);
			} catch (Exception e) {
				LOGGER.error("An exception occurred while getting the readmeData", e);
				return new ResponseEntity<ProjectResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
			}

			if (null != contributors && null != readmeData) {
				responseDTO.setContributors(contributors);
				responseDTO.setReadme(readmeData);
			} else {
				return new ResponseEntity<ProjectResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<ProjectResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Returing the API response");
		return new ResponseEntity<ProjectResponseDTO>(responseDTO, HttpStatus.OK);
	}

}
