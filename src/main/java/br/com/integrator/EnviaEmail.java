package br.com.integrator;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


public class EnviaEmail {
	private String de;
	private String para;
	private String assunto;
	private String mensagem;
	
	private static Properties props=null;
	private static Session session = null;
	private static ReadProperties readProperties  = new ReadProperties();
	
	static{
		props = System.getProperties();
		props.put("mail.smtp.host", readProperties.getHost());
		props.put("mail.smtp.port", readProperties.getPort());
		props.put("mail.smtp.auth", "true");
		session = Session.getInstance(props,
				new Autenticar(readProperties.getUser(),readProperties.getPass())
		);
	}
		
	
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean enviar(){
		try{
			MimeMessage message = new MimeMessage(session);
			message.setRecipient(
					Message.RecipientType.TO, 
					new InternetAddress(this.para)
			);
			message.setFrom(new InternetAddress(this.de));
			message.setSubject(this.assunto);
			
			message.setSentDate(new Date());
			
			message.setContent(this.mensagem,"text/html");
			Transport.send(message);
			
			return true;
			
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
