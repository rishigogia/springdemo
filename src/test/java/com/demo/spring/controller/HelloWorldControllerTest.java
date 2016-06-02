package com.demo.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({"classpath:springdemo-servlet.xml"})
@WebAppConfiguration
public class HelloWorldControllerTest {

	@InjectMocks
	private HelloWorldController helloWorldController;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.alwaysDo(print())
				.build();
	}

	@Test
	public void checkWhetherFullWebWorkflowIsWorking () throws Exception {
		mockMvc.perform(get("/helloworld"))
				.andExpect(status().isOk())
				.andExpect(view().name("welcome"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", is("Hello World!")));
	}

	@Test
	public void errorOnProvidingWrongContext () throws Exception {
		mockMvc.perform(get("/helloworld1"))
				.andExpect(status().is4xxClientError());
	}

}