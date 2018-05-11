package net.vivekkumar.spring.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	private String email;
	private String msg;
	private String sub;

	public SendMail(String email, String msg, String sub) {

		super();
		this.email = email;
		this.msg = msg;
		this.sub = sub;
	}

	public SendMail() {

	}

	public static boolean sendMail(String email, String message, String sub) {
		boolean isSent = false;
		isSent = new SendMail(email, message, sub).sendSimpleMail();
		if (isSent) {
			System.out.println("Email has been sent to user" + message);
		}
		return isSent;
	}

	private boolean sendSimpleMail() {
		boolean isSent = false;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kumarviv353@gmail.com", "testapp@123");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("kumarviv353@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(sub);
			// message.setText(msg);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(msg);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource("Asset_Allocation_report.xls");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Asset_Allocation_report.xls");
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);
			Transport.send(message);
			isSent = true;
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isSent;

	}
}