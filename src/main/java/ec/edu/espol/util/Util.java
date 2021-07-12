/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    private Util() {
    }

    public static int nextID(String nomfile) {
        int id = 0;
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                id = Integer.parseInt(tokens[0]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return id + 1;
    }

    public static void enviarEmail(String correo, String mensaje) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config/configuracion.properties"));
        Session session = Session.getDefaultInstance(properties);
        MimeMessage contenedor = new MimeMessage(session);
        try {
            contenedor.setFrom(new InternetAddress((String) properties.get("mail.smtp.user")));
            contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            contenedor.setSubject("NOTIFICACION DEL SISTEMA - OFERTA ACEPTADA");
            contenedor.setText(mensaje, "utf-8", "html");
            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", (String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.clave"));
            t.sendMessage(contenedor, contenedor.getAllRecipients());
            t.close();
        } catch (MessagingException ex) {
            ex.printStackTrace();   //Si se produce un error
        }
    }

    //funcion Hash
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static int MenuPrincipal(Scanner sc) {
        System.out.println("===MENU DE OPCIONES===");
        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");
        System.out.println("3. Salir\n");
        System.out.println("Escribe una de las opciones: ");
        int opcion = sc.nextInt();
        return opcion;

    }

    public static int MenuComprador(Scanner sc) {
        System.out.println("\n==OPCIONES DEL COMPRADOR==");
        System.out.println("1. Registrar un nuevo comprador");
        System.out.println("2. Ofertar por un vehículo");
        System.out.println("3. Regresar\n");
        System.out.println("Escribe una de las opciones: ");
        int subopcion = sc.nextInt();
        return subopcion;

    }

    public static int MenuVendedor(Scanner sc) {
        System.out.println("\n==OPCIONES DEL VENDEDOR==");
        System.out.println("1. Registrar un nuevo vendedor");
        System.out.println("2. Ingresar un nuevo vehiculo");
        System.out.println("3. Aceptar oferta");
        System.out.println("4. Regresar\n");
        System.out.println("Escribe una de las opciones: ");
        int subopcion = sc.nextInt();
        return subopcion;
    }

    public static String ofertarVehiculos(Scanner sc, int i, int fin) {
        String opc = "";
        int op;
        if (fin != 0) {
            if (i == 0) {
                System.out.println("1.- Siguiente Vehiculo");
                System.out.println("2.- Ofertar");
                System.out.println("3.- Regresar");
                System.out.println("\nIngrese la opcion:");
                op = sc.nextInt();
                if (op == 1) {
                    opc = "siguiente";
                } else if (op == 2) {
                    opc = "ofertar";
                } else if (op == 3){
                    opc = "regresar";
                }else {
                    System.out.println("Opcion invalida");
                }
            } else if (i == fin) {
                System.out.println("1.- Anterior Vehiculo");
                System.out.println("2.- Ofertar");
                System.out.println("3.- Regresar");
                System.out.println("\nIngrese la opcion:");
                op = sc.nextInt();
                if (op == 1) {
                    opc = "anterior";
                } else if (op == 2) {
                    opc = "ofertar";
                } else if (op == 3){
                    opc = "regresar";
                }else {
                    System.out.println("Opcion invalida");
                }
            } else {
                System.out.println("1.- Siguiente Vehiculo");
                System.out.println("2.- Anterior Vehiculo");
                System.out.println("3.- Ofertar");
                System.out.println("4.- Regresar");
                System.out.println("\nIngrese la opcion:");
                op = sc.nextInt();
                if (op == 1) {
                    opc = "siguiente";
                } else if (op == 2) {
                    opc = "anterior";
                } else if (op == 3) {
                    opc = "ofertar";
                } else if (op == 4){
                    opc = "regresar";
                }else {
                    System.out.println("Opcion invalida");
                }
            }
        } else {
            System.out.println("1.- Ofertar");
            System.out.println("2.- Regresar");
            System.out.println("\nIngrese la opcion:");
            op = sc.nextInt();
            if (op == 1) {
                opc = "ofertar";
            } else if (op == 2){
                opc = "regresar";
            }else {
                System.out.println("Opcion invalida");
            }
        }
        return opc;
    }

    public static String aceptarOfertas(Scanner sc, int i, int fin) {
        String opc = "";
        int op;
        if (fin != 0) {
            if (i == 0) {
                System.out.println("1.- Siguiente Oferta");
                System.out.println("2.- Aceptar Oferta");
                System.out.println("3.- Regresar");
                System.out.println("\nIngrese la opcion:");
                op = sc.nextInt();
                if (op == 1) {
                    opc = "siguiente";
                } else if (op == 2) {
                    opc = "aceptar";
                } else if (op == 3){
                    opc = "regresar";
                } else {
                    System.out.println("Opcion invalida");
                }
            } else if (i == fin) {
                System.out.println("1.- Anterior Oferta");
                System.out.println("2.- Aceptar Oferta");
                System.out.println("3.- Regresar");
                System.out.println("\nIngrese la opcion:");
                op = sc.nextInt();
                if (op == 1) {
                    opc = "anterior";
                } else if (op == 2) {
                    opc = "aceptar";
                } else if (op == 3){
                    opc = "regresar";
                } else {
                    System.out.println("Opcion invalida");
                }
            } else {
                System.out.println("1.- Siguiente Oferta");
                System.out.println("2.- Anterior Oferta");
                System.out.println("3.- Aceptar Oferta");
                System.out.println("4.- Regresar");
                System.out.println("\nIngrese la opcion:");
                op = sc.nextInt();
                if (op == 1) {
                    opc = "siguiente";
                } else if (op == 2) {
                    opc = "anterior";
                } else if (op == 3) {
                    opc = "aceptar";
                } else if (op == 4){
                    opc = "regresar";
                }else {
                    System.out.println("Opcion invalida");
                }
            }
        } else {
            System.out.println("1.- Aceptar Oferta");
            System.out.println("2.- Regresar");
            System.out.println("\nIngrese la opcion:");
            op = sc.nextInt();
            if (op == 1) {
                opc = "aceptar";
            } else if (op == 2){
                opc = "regresar";
            }else {
                System.out.println("Opcion invalida");
            }
        }
        return opc;
    }

    public static void limpiarArchivo(String nomfile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nomfile));
        bw.write("");
        bw.close();
    }
}
