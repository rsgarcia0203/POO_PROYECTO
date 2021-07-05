/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto1p;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


import ec.edu.espol.model.Mail;
import ec.edu.espol.model.Vendedor;
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
public class Main {

 
    /**
     * @param args the command line arguments
     */
    
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {      
    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.

        Properties props;
        Session session;
        props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", "ronnydarkmaster@gmail.com");
        props.put("mail.smtp.clave", "000000000000");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("ronnydarkmaster@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            try (Transport transport = session.getTransport("smtp")) {
                transport.connect("smtp.gmail.com", "ronnydarkmaster@gmail.com", "00000000000");
                transport.sendMessage(message, message.getAllRecipients());
            }
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
    

    public static void main(String[] args) throws MessagingException {
        // TODO code application logic here
        Mail m;
        try {
            m = new Mail("config/configuracion.prop");
            
            m.enviarEmail("Test","Hola mundo","rsgarcia@espol.edu.ec");
            System.out.println("Correo enviado");
        }catch(InvalidParameterException | IOException ex){
            System.out.println(ex.getMessage());
        }
              
       
        Scanner sn = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        int subopcion;
        do{
            boolean sub_salir = false;
            System.out.println("===MENU DE OPCIONES===");
            System.out.println("1. Vendedor");
            System.out.println("2. Comprador");
            System.out.println("3. Salir\n");
            
            System.out.println("Escribe una de las opciones: ");
            opcion = sn.nextInt();
            
            switch(opcion){
                case 1:
                {
                    do{
                        System.out.println("==OPCIONES DEL VENDEDOR==");
                        System.out.println("1. Registrar un nuevo vendedor");
                        System.out.println("2. Ingresar un nuevo vehiculo");
                        System.out.println("3. Aceptar oferta");
                        System.out.println("4. Regresar\n");
                    
                        System.out.println("Escribe una de las opciones: ");
                        subopcion = sn.nextInt();
                        switch(subopcion){
                            case 1:
                                System.out.println("\n=REGISTRAR=");
                                Vendedor v1 = Vendedor.registrarNuevoVendedor(sn);
                                if(v1.getCorreo() == null){
                                    
                                }
                                    
                                
                                    
                            case 2:
                                
                                break;
                            case 3:
                                break;
                            case 4:
                                sub_salir = true;
                                break;
                            default:
                                System.out.println("El submenu solo tiene 4 opciones");
                        }
                    }
                    while(subopcion != 4);
                }
                    
                case 2:
                    while(!sub_salir){
                        System.out.println("==OPCIONES DEL COMPRADOR==");
                        System.out.println("1. Registrar un nuevo comprador");
                        System.out.println("2. Ofertar por un vehículo");
                        System.out.println("3. Regresar");
                    
                        System.out.println("Escribe una de las opciones: ");
                        subopcion = sn.nextInt();
                        switch(subopcion){
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                sub_salir = true;
                                break;
                            default:
                                System.out.println("El submenu solo tiene 3 opciones");
                        }
                    }

                    
                case 3:
                    System.out.println("Gracias");;
                    
                default:
                    System.out.println("El programa solo tiene 3 opciones");
           }
          
       }while(opcion != 3);
    //rsgar
    }
}   
   
