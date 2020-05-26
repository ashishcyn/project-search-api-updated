
package com.ashish.tech.projectsearchapi.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.client.RestTemplate;

import com.ashish.tech.projectsearchapi.service.dto.ProjectDTO;
import com.ashish.tech.projectsearchapi.service.dto.UserDTO;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProjectsearchServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private ProjectsearchServiceImpl projectsearchService;

	@Test
	void testGetUserDetails_shouldReturnMockedObject() throws Exception {
		final UserDTO user = new UserDTO();
		user.setName("Ashish Cn");

		Mockito.when(restTemplate.getForObject("https://api.github.com/users/ashishcyn", UserDTO.class))
				.thenReturn(user);

		UserDTO result = projectsearchService.getUserDetails("ashishcyn");
		Assertions.assertEquals(user.getName(), result.getName());
	}

	@Test
	void testGetAllProjects_shouldReturnMockedObject() throws Exception {

		LinkedHashMap<Object, Object> map1 = new LinkedHashMap<Object, Object>();
		LinkedHashMap<Object, Object> map2 = new LinkedHashMap<Object, Object>();
		LinkedHashMap<Object, Object> map3 = new LinkedHashMap<Object, Object>();
		map1.put("id", "001");
		map1.put("html_url", "https://github.com/ashishcyn/project-search-api");
		map1.put("name", "project-search-api");

		map2.put("id", "002");
		map2.put("html_url", "https://github.com/ashishcyn/project-search-apiv1");
		map2.put("name", "project-search-apiv1");

		map3.put("id", "003");
		map3.put("html_url", "https://github.com/ashishcyn/project-search-apiv2");
		map3.put("name", "project-search-apiv2");

		List<LinkedHashMap<Object, Object>> repos = new ArrayList<>();
		repos.add(map1);
		repos.add(map2);
		repos.add(map3);

		Mockito.when(restTemplate.getForObject("https://api.github.com/users/ashishcyn/repos", List.class))
				.thenReturn(repos);

		List<ProjectDTO> results = projectsearchService.getAllProjects("ashishcyn");
		Assertions.assertEquals(3,results.size(), "Method returend value successfully");
	}

	@Test
	void testGetAllContributors_shouldReturnMockedObject() throws Exception {
		UserDTO user;
		List<UserDTO> contributors = new ArrayList<>();

		LinkedHashMap<Object, Object> map1 = new LinkedHashMap<Object, Object>();
		LinkedHashMap<Object, Object> map2 = new LinkedHashMap<Object, Object>();

		map1.put("login", "ashish");
		map1.put("contributions", "10");

		map2.put("login", "tony");
		map2.put("contributions", "14");

		List<LinkedHashMap<Object, Object>> contributorsMock = new ArrayList<>();
		contributorsMock.add(map1);
		contributorsMock.add(map2);

		Mockito.when(restTemplate.getForObject("https://api.github.com/repos/ashishcyn/project-search-api/contributors",
				List.class)).thenReturn(contributorsMock);

		List<UserDTO> results = projectsearchService.getAllContributors("ashishcyn", "project-search-api");
		Assertions.assertEquals(2, results.size(), "Method returend value successfully");

	}

	@Test
	void testGetReadmeData_shouldReturnMockedObject() throws Exception {

		String readmedata = "unit testing ";

		Mockito.when(restTemplate.getForObject(
				"https://raw.githubusercontent.com/ashishcyn/project-search-api/master/README.md", String.class))
				.thenReturn(readmedata);

		String results = projectsearchService.getReadmeData("ashishcyn", "project-search-api");
		Assertions.assertTrue(results.length() >= 0, "Method returend value successfully");

	}

}
