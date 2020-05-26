/**
 * ProjectSearchApiApplication: ProjectsearchService implementation 
 * 
 * @author  ashish
 * @version 1.0
 * @since   2020-05-21 
 */
package com.ashish.tech.projectsearchapi.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ashish.tech.projectsearchapi.service.ProjectsearchService;
import com.ashish.tech.projectsearchapi.service.dto.ProjectDTO;
import com.ashish.tech.projectsearchapi.service.dto.UserDTO;

@Service("projectsearchservices")
public class ProjectsearchServiceImpl implements ProjectsearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsearchServiceImpl.class);
	
	
	RestTemplate restTemplate = new RestTemplate();;

	

	/*
	 * Get User Details
	 * 
	 * @param username
	 * 
	 * @return user
	 */
	@Override
	public @NotNull(message = "username should not be null") UserDTO getUserDetails(@Valid String username)
			throws Exception {
		UserDTO user = restTemplate.getForObject("https://api.github.com/users/" + username, UserDTO.class);
		LOGGER.info("Returing user object");
		return user;
	}

	/*
	 * Get Project Details
	 * 
	 * @param username
	 * 
	 * @return projects
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public @NotNull(message = "username should not be null") List<ProjectDTO> getAllProjects(@Valid String username)
			throws Exception {
		List<ProjectDTO> projects = new ArrayList<>();
		List<LinkedHashMap<Object, Object>> repos = restTemplate
				.getForObject("https://api.github.com/users/" + username + "/repos", List.class);

		for (LinkedHashMap<Object, Object> repo : repos) {
			// New repo object
			ProjectDTO project = new ProjectDTO();
			project.setId(String.valueOf(repo.get("id")));
			project.setUrl(String.valueOf(repo.get("html_url")));
			project.setTitle(String.valueOf(repo.get("name")));
			// Adding the project to the list
			projects.add(project);
		}
		LOGGER.info("Returing projects");
		return projects;
	}

	/*
	 * Get Contributor Details
	 * 
	 * @param username
	 * 
	 * @param projectid
	 * 
	 * @return contributors
	 */
	@SuppressWarnings("unchecked")
	@Override
	public @NotNull(message = "username and projectid should not be null") List<UserDTO> getAllContributors(
			@Valid String username, @Valid String projectid) throws Exception {

		UserDTO user;
		List<UserDTO> contributors = new ArrayList<>();
		List<LinkedHashMap<Object, Object>> contributorsReceived = restTemplate.getForObject(
				"https://api.github.com/repos/" + username + "/" + projectid + "/contributors", List.class);
		for (LinkedHashMap<Object, Object> contributor : contributorsReceived) {
			user = new UserDTO();
			user.setCommits(String.valueOf(contributor.get("contributions")));
			user.setUserName(String.valueOf(contributor.get("login")));
			// Adding the contributor to the list
			contributors.add(user);
		}
		LOGGER.info("Returing contributors");
		return contributors;
	}

	/*
	 * Get readme data
	 * 
	 * @param username
	 * 
	 * @param projectid
	 * 
	 * @return readme data in a String
	 */
	@Override
	public @NotNull(message = "username and projectid should not be null") String getReadmeData(@Valid String username,
			@Valid String projectid) throws Exception {
		String readmeData = restTemplate.getForObject(
				"https://raw.githubusercontent.com/" + username + "/" + projectid + "/master/README.md", String.class);

		// getting the first 50 words from readnedata data
		int numWords = 50;
		int i = nthOccurrence(readmeData, ' ', numWords - 1);
		readmeData = i == -1 ? readmeData : readmeData.substring(0, i);
		LOGGER.info("Returing readmeData");
		return readmeData;
	}

	/**
	 * Supporting method for the readme data extraction
	 * 
	 * @param str 
	 * @param c
	 * @param n
	 * @return
	 */
	private int nthOccurrence(String str, char c, int n) {
		int pos = str.indexOf(c, 0);
		while (n-- > 0 && pos != -1)
			pos = str.indexOf(c, pos + 1);
		return pos;
	}

}
