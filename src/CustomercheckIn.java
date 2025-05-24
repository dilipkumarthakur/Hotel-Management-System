
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
import javax.swing.JOptionPane;
import project.*;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author aditya
 */
public class CustomercheckIn extends javax.swing.JFrame {

    /**
     * Creates new form CustomercheckIn
     */
    public CustomercheckIn() {
        initComponents();
        jTextField7.setEditable(false);
        jTextField8.setEditable(false);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        jTextField7.setText(myFormat.format(cal.getTime()));
    }
    
    String bed;
    String roomType;
    String roomNo;
    String price;
    
    public void  roomDetails()
    {
//        jComboBox4.removeAllItems();
//        jTextField8.setText("");
//        bed =(String)jComboBox2.getSelectedItem();
//        roomType =(String)jComboBox3.getSelectedItem();
//        try
//        {
//            ResultSet rs = Select.getData("select * from room where bed ='"+bed+"' and roomType='"+roomType+"' and status='Not Booked'");
//            while(rs.next())
//            {
//                jComboBox4.addItem(rs.getString(1));
//            }
//        }catch(Exception e)
//        {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        roomNo = (String) jComboBox4.getSelectedItem(); // Get selected room number
//
//    try {
//        ResultSet rs = Select.getData("SELECT price FROM room WHERE roomNo='" + roomNo + "'");
//        if (rs.next()) {
//            jTextField8.setText(rs.getString(1)); // Set price in jTextField8
//        } else {
//            jTextField8.setText(""); // Clear price if no room is found
//        }
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(null, e);
//    }
//        
//        
//        
//        jComboBox4.removeAllItems();
//    jTextField8.setText("");
//    bed = (String) jComboBox2.getSelectedItem();
//    roomType = (String) jComboBox3.getSelectedItem();
//    
//    System.out.println("Fetching rooms for: BedType=" + bed + ", RoomType=" + roomType);
//    
//    try {
//        ResultSet rs = Select.getData("SELECT * FROM room WHERE bed ='" + bed + "' AND roomType='" + roomType + "' AND status='Not Booked '");
//        boolean found = false;
//
//        while (rs.next()) {
//            jComboBox4.addItem(rs.getString(1));
//            found = true;
//            System.out.println("Room found: " + rs.getString(1));
//        }
//        
//        if (!found) {
//            System.out.println("No available rooms found for this bed type and room type.");
//        }
//
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(null, e);
//        e.printStackTrace();
//    }
//    
        
        jComboBox4.removeAllItems();
jTextField8.setText("");
bed = (String) jComboBox2.getSelectedItem();
roomType = (String) jComboBox3.getSelectedItem();

System.out.println("Fetching rooms for: BedType=" + bed + ", RoomType=" + roomType);

try {
    ResultSet rs = Select.getData("SELECT * FROM room WHERE bed ='" + bed + "' AND roomType='" + roomType + "' AND status='Not Booked '");
    boolean found = false;

    while (rs.next()) {
        jComboBox4.addItem(rs.getString(1));
        found = true;
        System.out.println("Room found: " + rs.getString(1));
    }

    if (!found) {
        System.out.println("No available rooms found for this bed type and room type.");
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);
    e.printStackTrace();
}

// ✅ Fix: Remove extra space in 'Not Booked' and ensure roomNo is set correctly
roomNo = (String) jComboBox4.getSelectedItem(); 

try {
    ResultSet rs = Select.getData("SELECT price FROM room WHERE roomNo='" + roomNo + "'");
    if (rs.next()) {
        jTextField8.setText(rs.getString(1)); 
    } else {
        jTextField8.setText(""); 
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);
}

    }
    
    
    
    
    //sending mail method 
    public void sendEmail(String recipientEmail, String name, String roomNumber, String checkIn, String bed, String roomType, String price) {
    final String senderEmail = "mastimasti227@gmail.com";  // Your email
    final String senderPassword = "ucofqfpgteawhmpk"; // Your app password

    // Email properties
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    // Session
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    });

    try {
        // Create the email message
        Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(senderEmail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
message.setSubject("🏨 Room Allocation Confirmation - Namaste Hotel Supaul ✨");

// HTML email content with extended information and emojis
String emailContent = "<html>"
    + "<body style='font-family: Arial, sans-serif; color: #333;'>"
    + "<h2 style='color: #2E86C1;'>Dear " + name + " 😊,</h2>"
    + "<p>We are delighted to inform you that your room has been successfully allocated at <b>Namaste Hotel Supaul</b>. 🎉 Here are all the details you need to know for a comfortable stay with us:</p>"
    + "<h3 style='color: #FF6347;'>🛏️ Room Allocation Details:</h3>"
    + "<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; width: 100%; max-width: 600px;'>"
    + "<tr><th style='background-color: #2E86C1; color: white;'>Room Details 🏡</th><th style='background-color: #2E86C1; color: white;'>Information 📋</th></tr>"
    + "<tr><td><b>Room Number 🛏️</b></td><td>" + roomNumber + "</td></tr>"
    + "<tr><td><b>Check-In Date 📅</b></td><td>" + checkIn + "</td></tr>"
    + "<tr><td><b>Bed Type 🛏️</b></td><td>" + bed + "</td></tr>"
    + "<tr><td><b>Room Type 🏨</b></td><td>" + roomType + "</td></tr>"
    + "<tr><td><b>Price Per Day 💰</b></td><td>" + price + "</td></tr>"
    + "</table>"
    + "<h3 style='color: #FF6347;'>🛎️ Additional Services Available:</h3>"
    + "<p>We offer a variety of services to make your stay more enjoyable:</p>"
    + "<ul>"
    + "<li>🌐 Free Wi-Fi throughout the hotel</li>"
    + "<li>🍽️ 24/7 In-Room Dining Service</li>"
    + "<li>🧖‍♀️ On-Site Spa and Wellness Center</li>"
    + "<li>🚗 Airport Shuttle Service (Available upon request)</li>"
    + "<li>📺 Entertainment channels available in the room</li>"
    + "</ul>"
    + "<h3 style='color: #FF6347;'>📍 Location & Directions:</h3>"
    + "<p>Namaste Hotel Supaul is conveniently located in the heart of the city, close to major attractions like:</p>"
    + "<ul>"
    + "<li>🕌 Famous Landmark 1 (5 minutes by car)</li>"
    + "<li>🛍️ Shopping Mall (10 minutes by walking)</li>"
    + "<li>🍽️ Best Restaurants & Cafes (Around the corner)</li>"
    + "</ul>"
    + "<p>Here is the map link to our location: <a href='https://maps.app.goo.gl/khhQUEkNGJ4bnF98A'>Hotel Location</a></p>"
    + "<h3 style='color: #FF6347;'>📞 Contact Us:</h3>"
    + "<p>If you have any questions or need assistance, feel free to contact us at: <b>8603731247</b>.</p>"
    + "<p>Our team at <b>Namaste Hotel Supaul</b> is here to ensure that your stay is perfect and stress-free. If you need any assistance or have special requests, feel free to reach out to us at any time! 🌟</p>"
    + "<p>Thank you for choosing <b>Namaste Hotel Supaul</b>. We look forward to welcoming you soon! 🏨💖</p>"
    + "<p style='font-weight: bold;'>Best Regards,<br>Hotel Management Team 🏨</p>"
    + "<p><em>Feel free to follow us on social media for updates and special promotions!</em> 📲</p>"
    + "</body></html>";

// Set email content as HTML with emojis
message.setContent(emailContent, "text/html; charset=utf-8");

// Send the email
Transport.send(message);
System.out.println("Email sent successfully. 📧");


    } catch (MessagingException e) {
        // Handle any messaging exceptions that may occur
        e.printStackTrace();
    }
}

    
    
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(50, 118));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Customer Registration & Check IN.png"))); // NOI18N
        jLabel1.setText("Customer Check IN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 33, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mobile Number");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 185, -1, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 141, 350, -1));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 223, 350, -1));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 305, 350, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 103, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nationality");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 267, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Gender");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 349, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 387, 350, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Email");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 431, -1, -1));

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 350, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Aadhar Number");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 103, -1, -1));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 223, 350, -1));

        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 141, 350, -1));

        jTextField7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 305, 350, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Address");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 185, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Check IN Date (Today)");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 267, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Bed");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 103, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Double", "Triple" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 141, 350, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Price");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 349, -1, -1));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "Non-AC" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 223, 350, -1));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox4.setToolTipText("");
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 305, 350, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Room Type");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 185, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Room Number");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 267, -1, -1));

        jButton2.setBackground(new java.awt.Color(102, 0, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Allote Room");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(898, 442, -1, -1));

        jButton3.setBackground(new java.awt.Color(102, 0, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1085, 442, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, 350, -1));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, 351, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/all pages background.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new CustomercheckIn().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        roomDetails();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        roomDetails();
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
//        roomNo=(String)jComboBox4.getSelectedItem();
//        try
//        {
//            ResultSet rs = Select.getData("select * from room where roomNo='"+roomNo+"'");
//            while(rs.next())
//            {
//                jTextField8.setText(rs.getString(4));
//            }
//        }catch(Exception e)
//        {
//            JOptionPane.showMessageDialog(null, e);
//        }

 roomNo = (String) jComboBox4.getSelectedItem();

    try {
        ResultSet rs = Select.getData("SELECT * FROM room WHERE roomNo = '" + roomNo + "' AND status = 'Not Booked'");
        while (rs.next()) {
            jTextField8.setText(rs.getString(4)); // Assuming 4th column contains required data
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
//        int id=  1;
//        String name = jTextField1.getText();
//        String mobileNumber = jTextField2.getText();
//        String nationlity = jTextField3.getText();
//        String gender = (String)jComboBox1.getSelectedItem();
//        String email = jTextField4.getText();
//        String idproof = jTextField5.getText();
//        String address = jTextField6.getText();
//        String checkIn= jTextField7.getText();
//        String bed = (String)jComboBox2.getSelectedItem();
//        String roomType = (String)jComboBox3.getSelectedItem();
//        String roomNumber = (String)jComboBox4.getSelectedItem();
//        String price = jTextField8.getText();
//        String Query= "select max(id) from customer";
//        try{
//            ResultSet rs = Select.getData(Query);
//            while(rs.next())
//            {
//                id=rs.getInt(1);
//             id=id+1;
//             
//             if(!price.equals(""))
//             {
//                 Query="update room set status='Booked' where roomNo='"+roomNo+"'";
//                 InsertUpdateDelete.setData(Query, "");
//                 //Query="insert into customer (id,name,mobileNumber , nationality , gender ,email ,idproof,address,checkIn , roomNumber,bed , roomType , pricePerDay) values("+id+" , '"+name+"','"+mobileNumber+"','"+nationlity+"','"+gender+"','"+email+"','"+idproof+"','"+address+"','"+checkIn+"','"+roomNumber+"','"+bed+"','"+roomType+"',"+price+")";
//                 //Query = "insert into customer (id, name, mobileNumber, nationality, gender, email, idproof, address, checkIn, roomNumber, bed, roomType, pricePerDay) values (...)";
//                 Query = "insert into customer (id, name, mobileNumber, nationality, gender, email, idproof, address, checkIn, roomNumber, bed, roomType, pricePerDay, checkOut) values(" + id + ",'" + name + "','" + mobileNumber + "','" + nationlity + "','" + gender + "','" + email + "','" + idproof + "','" + address + "','" + checkIn + "','" + roomNumber + "','" + bed + "','" + roomType + "'," + price + ", NULL)";
//
//
//                 //InsertUpdateDelete.setData(Query, "Customer Check In Successfully");
//                 InsertUpdateDelete.setData(Query, "Customer Check In Successfully");
//
//// Send confirmation email
//sendEmail(email, name, roomNumber, checkIn, bed, roomType, price);
//JOptionPane.showMessageDialog(null, "Email sent successfully to " + email);

//                 new CustomercheckIn().setVisible(true);
//             }
//            }
//            
//        }catch(Exception e)
//                {
//                    JOptionPane.showMessageDialog(null, e);
//                }






 int id = 1;
    String name = jTextField1.getText();
    String mobileNumber = jTextField2.getText();
    String nationlity = jTextField3.getText();
    String gender = (String) jComboBox1.getSelectedItem();
    String email = jTextField4.getText();
    String idproof = jTextField5.getText();
    String address = jTextField6.getText();
    String checkIn = jTextField7.getText();
    String bed = (String) jComboBox2.getSelectedItem();
    String roomType = (String) jComboBox3.getSelectedItem();
    String roomNumber = (String) jComboBox4.getSelectedItem();
    String price = jTextField8.getText();
    
    
    
    // name validation 
    
    if(name == null || name.trim().isEmpty())
    {
        JOptionPane.showMessageDialog(null, "Please Enter Your Name ");
        return;
    }
    // mobile number 
    
    if(mobileNumber.length() != 10)
    {
        JOptionPane.showMessageDialog(null, "Please Enter Valid Mobile Number ");
        return; 
        
    }
    // Email validation
    if (email == null || email.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Email address cannot be empty.");
        return; // Stop further processing
    }

    // Simple email format validation
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    if (!email.matches(emailRegex)) {
        JOptionPane.showMessageDialog(null, "Invalid email format.");
        return; // Stop further processing
    }

    String Query = "select max(id) from customer";
    try {
        ResultSet rs = Select.getData(Query);
        while (rs.next()) {
            id = rs.getInt(1);
            id = id + 1;

            if (!price.equals("")) {
                // Update room status to "Booked"
                Query = "update room set status='Booked' where roomNo='" + roomNumber + "'";
                InsertUpdateDelete.setData(Query, "");

                // Insert customer data into the database
                Query = "insert into customer (id, name, mobileNumber, nationality, gender, email, idproof, address, checkIn, roomNumber, bed, roomType, pricePerDay, checkOut) "
                        + "values(" + id + ",'" + name + "','" + mobileNumber + "','" + nationlity + "','" + gender + "','" + email + "','" + idproof + "','" + address + "','" + checkIn + "','" + roomNumber + "','" + bed + "','" + roomType + "'," + price + ", NULL)";

                InsertUpdateDelete.setData(Query, "Customer Check In Successfully");

                // Send confirmation email
                sendEmail(email, name, roomNumber, checkIn, bed, roomType, price);
                JOptionPane.showMessageDialog(null, "Email sent successfully to " + email);

                // Open the check-in confirmation page
                new CustomercheckIn().setVisible(true);
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomercheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomercheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomercheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomercheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomercheckIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
