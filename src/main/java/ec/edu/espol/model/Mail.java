/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rsgar
 */
public class Mail {
    private Properties props;
    private Session session;
    
    public Mail(String ruta) throws IOException
    {
        this.props = new Properties();
        loadConfig(ruta);
    }
    
    private void loadConfig(String ruta) throws IOException, InvalidParameterException
    {
        FileReader is = new FileReader(ruta);
        this.props.load(is);  
        checkConfig();
    }
    
    private void checkConfig() throws InvalidParameterException
    {
        String[] keys = {
            "mail.smtp.host",
            "mail.smtp.port",
            "mail.smtp.user",
            "mail.smtp.clave",
            "mail.smtp.starttls.enable",
            "mail.smtp.auth",
        };
        
        for (int i = 0; i < keys.length; i++)
        {
            if(this.props.get(keys[i]) == null){
                throw new InvalidParameterException("No existe la clave " + keys[i]);
            }
        }
    }
    
    public void enviarEmail(String asunto, String mensaje, String correo) throws MessagingException
    {
        Session session = Session.getDefaultInstance(this.props);
        MimeMessage contenedor = new MimeMessage(session);
        
        contenedor.setFrom(new InternetAddress((String) this.props.get("mail.smtp.user")));
        contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
        contenedor.setSubject(asunto);
        contenedor.setText(mensaje);
        Transport t = session.getTransport("smtp");
        t.connect("smtp.gmail.com",(String) this.props.get("mail.smtp.user"),(String) this.props.get("mail.smtp.password"));
        t.sendMessage(contenedor, contenedor.getAllRecipients());
        t.close();
    }
    
    
}
