package com.thyoun.casino;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CasinoControl {

	@RequestMapping(value="/")
	public void homePage(HttpServletResponse response) {
		try {
		response.sendRedirect("/index.html");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
