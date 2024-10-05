package com.test.service;

import java.io.File;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.test.beans.Mail;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	JavaMailSender mailSender;

	public void sendMail(Mail mail) {
		
			String from = mail.getFrom();
			
			try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo("javapankaj123@gmail.com");
			message.setSubject(mail.getSubject());
			message.setText(mail.getText());

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendMailWithAttechament(Mail mail) {
		String from = mail.getFrom();
		
		// Creating a mime message
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo("javapankaj123@gmail.com");
			mimeMessageHelper.setText(mail.getText());
			mimeMessageHelper.setSubject(mail.getSubject());

			// Adding the attachment
			FileSystemResource file = new FileSystemResource(new File(mail.getText()));

			mimeMessageHelper.addAttachment(file.getFilename(), file);

			// Sending the mail
			mailSender.send(mimeMessage);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
