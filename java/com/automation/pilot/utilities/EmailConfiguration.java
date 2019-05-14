package com.automation.pilot.utilities;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
public class EmailConfiguration {
	
	@SuppressWarnings("deprecation")
	public void sendSimpleEmail() throws EmailException, NoSuchProviderException{
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(467);
		email.setAuthenticator(new DefaultAuthenticator("sendtestemail80@gmail.com", "test123@"));
		email.setSSLOnConnect(true);
		email.setFrom("sendtestemail80@gmail.com");
		email.setSubject("TestMail to test sent latest");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("sendtestemail80@gmail.com");
		email.send();
	}


	public void checkTheEmail() throws IOException {
	try {
		Properties props = System.getProperties();
		props.setProperty("smtp.googlemail.com", "smtp");
	Session session = Session.getDefaultInstance(props, null);
	Store store = session.getStore("imaps");
	  store.connect("smtp.googlemail.com","sendtestemail80@gmail.com", "test123@");
	  Folder emailFolder =  store.getFolder("INBOX");
	  emailFolder.open(Folder.READ_ONLY);

	  // retrieve the messages from the folder in an array and print it
	  Message[] messages = emailFolder.getMessages();
	  System.out.println("messages.length---" + messages.length);

	  for (int i = 0, n = messages.length; i < n; i++) {
	     Message message = messages[i];
	     System.out.println("---------------------------------");
	     System.out.println("Email Number " + (i + 1));
	     System.out.println("Subject: " + message.getSubject());
	     System.out.println("From: " + message.getFrom()[0]);
	     System.out.println("Text: " + message.getContent().toString());
	     System.out.println("Received Time: " + message.getSentDate());

	  }

	  //close the store and folder objects
	  emailFolder.close(false);
	  store.close();
	} catch (NoSuchProviderException e) {
	e.printStackTrace();
	System.exit(1);
	} catch (MessagingException e) {
	e.printStackTrace();
	System.exit(2);
	}

	}
	
	public void getFolders() throws MessagingException
	{
		Properties props = System.getProperties();
		props.setProperty("smtp.googlemail.com", "smtp");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("pop3s");
		Folder[] folder = store.getDefaultFolder().list(); // get the array folders in server
		  for(int i=0;i<folder.length;i++)
		  System.out.println("Press "+i+" to read "+folder[i].getName()+" folder");
		  //create the folder object and open it
	}
    public void sendEmailWithAttachment() throws EmailException{
	  // Create the attachment
	  EmailAttachment attachment = new EmailAttachment();
	  attachment.setPath("mypictures/john.jpg");
	  attachment.setDisposition(EmailAttachment.ATTACHMENT);
	  attachment.setDescription("Picture of John");
	  attachment.setName("John");

	  // Create the email message
	  MultiPartEmail email = new MultiPartEmail();
	  email.setHostName("mail.myserver.com");
	  email.addTo("jdoe@somewhere.org", "John Doe");
	  email.setFrom("me@apache.org", "Me");
	  email.setSubject("The picture");
	  email.setMsg("Here is the picture you wanted");

	  // add the attachment
	  email.attach(attachment);

	  // send the email
	  email.send();
    }
	
	public static void main(String args[]) throws EmailException, NoSuchProviderException, IOException{
		EmailConfiguration email = new EmailConfiguration();
		email.sendSimpleEmail();
		System.out.println("Sent a sample email");
		email.checkTheEmail();
		System.out.println("Checked a inbox");
	}

}
