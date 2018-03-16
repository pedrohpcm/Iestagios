package iEstagios.email;

import iEstagios.modelo.Aluno;
import iEstagios.modelo.Concedente;
import iEstagios.modelo.Vaga;
import iEstagios.modelo.Conta;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email extends Object {

    public static void enviarParaConcedente(Concedente concedente, Aluno aluno, Vaga vaga) {

        try {

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("iestagios.ifrn@gmail.com", "iestagios@123");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage(mailSession);

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress("iestagios.ifrn@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(concedente.getConta().getLogin()));
            msg.setSentDate(new Date());
            msg.setSubject("Bem vindo ao iEstagios!");

            //--[ Create the body of the mail
            msg.setText("Olá, "+concedente.getRepresentante()+"\nA sua empresa "+concedente.getNome()+  " acaba de contratar "+aluno.getNome()
                    + "\nPara acessar sua conta no iEstagios utilize seu email e a seguinte senha: "+concedente.getConta().getSenha());

            //--[ Ask the Transport class to send our mail message
            Transport.send(msg);

        } catch (Exception E) {
            System.out.println("Oops something has gone pearshaped!");
            System.out.println(E);
        }
    }
    
    public static void enviarSenha(Conta usuario) {

        try {

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("iestagios.ifrn@gmail.com", "iestagios@123");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage(mailSession);

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress("iestagios.ifrn@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getLogin()));
            msg.setSentDate(new Date());
            msg.setSubject("Bem vindo ao iEstagios!");

            //--[ Create the body of the mail
            msg.setText("Olá, você acaba de se cadastrar no iEstagios! "
                    + "\nPara acessar sua conta, utilize como login seu email e a seguinte senha: "+usuario.getSenha()
                    + "\nPara uma melhor utilização da nossa plataforma, preencha seu perfil por completo no próximo login.");

            //--[ Ask the Transport class to send our mail message
            Transport.send(msg);

        } catch (Exception E) {
            System.out.println("Oops something has gone pearshaped!");
            System.out.println(E);
        }
    }
    
        public static void enviarEntrevista(Aluno aluno, Concedente concedente, Vaga vaga, String mensagem) {

        try {

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("iestagios.ifrn@gmail.com", "iestagios@123");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage(mailSession);

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress("iestagios.ifrn@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(aluno.getConta().getLogin()));
            msg.setSentDate(new Date());
            msg.setSubject("Você foi escolhido!");

            //--[ Create the body of the mail
            msg.setText("Olá, você foi convocado pela empresa "+concedente.getNome()
                    + " para uma entrevista referente à seguinte vaga de estágio :"+vaga.getNomeDaVaga()
                    + "\n"+mensagem);

            //--[ Ask the Transport class to send our mail message
            Transport.send(msg);

        } catch (Exception E) {
            System.out.println("Oops something has gone pearshaped!");
            System.out.println(E);
        }
    }
}
