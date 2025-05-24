/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author aditya
 */
public class tables {
    
    public static void main(String ... args)
    {
        Connection con = null;
        Statement st = null;
        try{
            con=ConnectionProvider.getCon();
            st=con.createStatement();
            st.execute("create table users(name varchar(200), email varchar(200) , password varchar(50) , securityQuestion varchar(400) , answer varchar(200) , address varchar(200) , status varchar(20))");
            st.executeUpdate("create table room(roomNo varchar(10) , roomType varchar(200) , bed varchar(200) , price int , status varchar(20))");

st.executeUpdate(
    "CREATE TABLE customer ("
    + "id INT, "
    + "name VARCHAR(150), "
    + "mobileNumber VARCHAR(10), "
    + "nationality VARCHAR(150), "
    + "gender VARCHAR(20), "
    + "email VARCHAR(150), "
    + "idproof VARCHAR(200), "
    + "address VARCHAR(300), "
    + "checkIn VARCHAR(30), "
    + "roomNumber VARCHAR(10), "
    + "bed VARCHAR(150), "
    + "roomType VARCHAR(150), "
    + "pricePerDay INT, "
    + "numberOfDaysStay INT, "
    + "totalAmount VARCHAR(200), "
    + "`checkOut VARCHAR(50)"
    + ")"
);
            JOptionPane.showConfirmDialog(null, "Table crete Successfully");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        finally
        {
            try{
                con.close();
                st.close();
            
        }catch(Exception e)
        {
            
        }
        }
        
    }
    
}
