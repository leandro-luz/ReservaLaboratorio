package maill;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	public static void enviarEmail(String email, String senha) {
		Properties props = new Properties();
		/** Par�metros de conex�o com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		//A senha não pode conter caracteres especiais
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("centralcomunicacao2018@gmail.com", "Aaa22222");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("centralcomunicacao2018@gmail.com")); // Remetente

			Address[] toUser = InternetAddress // Destinat�rio(s)
					.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Senha provisária - RESERVA LABORATÓRIO");// Assunto
			message.setText("Olá,\n Sua senha provisória é: "+ senha 
					+ "\n Favor logar no sistema com essa senha \n"
					+ " e posteriormente deverá alterá-la. \n\n\n\n\n\n"
					+ " Favor não responder este email!");
			/** Método para enviar a mensagem criada */
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}