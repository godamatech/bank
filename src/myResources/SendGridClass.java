package myResources;

//using SendGrid's Java Library
//https://github.com/sendgrid/sendgrid-java

import com.sendgrid.*;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SendGridClass extends APIKeyClass {

	String userEmail = "bayero8944@gmail.com";

	public void sendEmailMsg() throws IOException, Exception {
		Email from = new Email("abdullahibello8944@gmail.com");
		String subject = "Godama Technologies - One Time Password (Password Confirmation)";
		Email toRecipient = new Email(userEmail);
		Content content = new Content("text/html", " Dear  " + userEmail + ", " + "<br/>"
				+ "<br/> Thank you for your application.  <br/> Copy the generated information below to log into your dashboard");
		Mail mail = new Mail(from, subject, toRecipient, content);

		SendGrid sg = new SendGrid(APIKeyClass.getApiKey());

		Request request = new Request();

		System.out.println("Testing before sending the mail");

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());

		} catch (Exception e) {
			throw e;
		}

	}// end of the sendMail method

	public static void main(String[] args) {
		SendGridClass sgc = new SendGridClass();
		try {
			sgc.sendEmailMsg();
			System.out.println("Email was Sent Successfully" );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Email Failed,\n Ensure you have a Strong Internet Connection and then try again.\n Thank you.",
					"Email Message", JOptionPane.WARNING_MESSAGE);
		}

	}

}
