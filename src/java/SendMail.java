
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author animesh_singh
 */
public class SendMail {

    public static void send(String to, String subject, String message,final String user, final String password) 
    {
       //Step1:Creating the Instances of the Properties Class for the Configuration;
        
      
        Properties props=new Properties(); //like hash table key vale
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587"); //For the TLS port and for SSL port is -465
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable", "true");
        
        
        //Step2: Authentication Purpose the UserName aand PAssword of asingh1248@gmail.com
        //Error:500 Internal Server but replaced DefaultInstance with getInstance and build error cannot delete jar file restart the netbeans
        Session session=Session.getInstance(props,new javax.mail.Authenticator()
        {
           protected PasswordAuthentication getPasswordAuthentication()
           {
              return new PasswordAuthentication(user,password);
           }
        });
         
        //Step3:Creating the Mime Instance and accept MIME types 
        
        try {
            MimeMessage message1=new MimeMessage(session);
            message1.setFrom(new InternetAddress(user));
            //V.Imp Line
//            message1.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            //For Mutilple Receipent
            //Very Interesting Logic separting string with "," and storing in array
            String[] receipentaddress = to.split(",");
            InternetAddress[] sendTo = new InternetAddress[receipentaddress.length];
            
            for (int i = 0; i < receipentaddress.length; i++) {
                System.out.println("Send to :"+receipentaddress[i]);
                sendTo[i]=new InternetAddress(receipentaddress[i]);
            }
            
            message1.addRecipients(Message.RecipientType.TO,sendTo);
            message1.setSubject(subject);
            message1.setText(message);
            
         //Step4:Transport class is used to send the Message
            Transport.send(message1);
              
            //GlassFish;Info:   Send to :asingh1248@gmail.com
//Info:   Send to :singh.sharda@somaiya.edu
            
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        
    }
    
}
