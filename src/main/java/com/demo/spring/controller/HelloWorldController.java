package com.demo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Dummy class to just show Spring MVC is working fine on the project
 */
@Controller
public class HelloWorldController {

	private static final String MESSAGE = "message";
	private static final String HELLOWORLD = "/helloworld";
	private static final String JSP_WELCOME = "welcome";

	/**
	 * Display the welcome page. The message needs to be passed on to the
	 * web page using the ModelMap
	 *
	 * @param modelMap - Used to pass on the messages to the web page
	 * @return the jsp page to be displayed
	 */
	@RequestMapping(HELLOWORLD)
	public String displayHelloWorld (ModelMap modelMap) {
		String message = "Hello World!";

		modelMap.addAttribute(MESSAGE, message);
		return JSP_WELCOME;
	}
}
