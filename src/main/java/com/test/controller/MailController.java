package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.beans.Mail;
import com.test.service.MailService;

@RestController
public class MailController {
	// this is mail contriller class 
	//  this second commite
	@Autowired
	MailService service;
	
	@GetMapping("/show")
	public String now()
	{
		return "welcome";
	}
	
	@PostMapping("/sendEmail")
	public String sendEmail(@RequestBody Mail mail)
	{
		try {
		service.sendMail(mail);
		System.out.println(mail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return " successfull sends ";
	}
	@PostMapping("/sendMailTamp")
	public String sendMailTamp(@RequestBody Mail mail)
	{
		service.sendMailWithAttechament(mail);
		return "send tamplete done";
	}
	

}
