package com.App.webApp.helperClasses;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.App.webApp.service.LoginUser;




public class EmailActions {
	static public boolean sendResetPasswordEmailMessage(LoginUser u, String verificationCode, JavaMailSender emailSender) {
		boolean flag = false;
		try {
			String subject = "Reset the password";
			String sendarName = "Nuvio Team";
			String mailContent = "<p>Dear " + u.getFirstName() + ", </p>";
			mailContent += "<p> please click the link below to reset your <b>nuvio seller</b> account password .</p>";
			String verifyUrl = "http://localhost:9000/resetPasswordcodeVerification?code=" + verificationCode;

			mailContent += "<h3> <a href=\"" + verifyUrl + "\">Reset Password</a></h3>";
			mailContent += "<p> Thank you<br>Nuvio Team</p>";

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("ramanpro5656@gmail.com", sendarName);
			helper.setTo(u.getEmailAddr());
			helper.setSubject(subject);
			helper.setText(mailContent, true);
			emailSender.send(message);

			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return flag;
	}

}
