/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;
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
public class Util {
    
    private Util(){}
    
    public static int nextID(String nomfile) 
    {
        int id = 0;
        try(Scanner sc = new Scanner(new File(nomfile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                id = Integer.parseInt(tokens[0]);
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        return id+1;
    }
    
    public static void enviarEmail(String correo, String mensaje) throws FileNotFoundException, IOException{
        Properties properties = new Properties();
        properties.load(new FileReader("config/configuracion.properties"));
        Session session = Session.getDefaultInstance(properties);
        MimeMessage contenedor = new MimeMessage(session);
        try{
            contenedor.setFrom(new InternetAddress((String) properties.get("mail.smtp.user")));
            contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            contenedor.setSubject("NOTIFICACION DEL SISTEMA - OFERTA ACEPTADA");
            contenedor.setText(mensaje, "UTF-8");
            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com",(String) properties.get("mail.smtp.user"),(String) properties.get("mail.smtp.password"));
            t.sendMessage(contenedor, contenedor.getAllRecipients());
            t.close();
        }
        
        catch (MessagingException ex) {
            ex.printStackTrace();   //Si se produce un error
        }
    }
    
    //funcion Hash
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
    
    public static void MenuPrincipal(Scanner sc){
        
    }
    
    public static void MenuComprador(Scanner sc){
        
    }
    
    public static void MenuVendedor(Scanner sc){
        
    }
}
