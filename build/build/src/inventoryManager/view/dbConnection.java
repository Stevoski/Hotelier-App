package inventoryManager.view;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class dbConnection {

	   private static Connection conexion = null;
	   dbConnection() {
	       }
	       public static Connection getInstance() throws InstantiationException {
	   if (conexion == null) {
	               try {
	                   Class.forName("com.mysql.cj.jdbc.Driver");
	                   conexion = DriverManager.getConnection("jdbc:mysql://stevoski-pc:3306/hotel_manager?allowMultiQueries=true&autoreconnect=true", "HotelAdmin", "P@ssw0rd");
//	                   java.util.Properties connProperties = new java.util.Properties();
//	                   connProperties.put(MYSQL_AUTO_RECONNECT, "true");
//	       connProperties.put(MYSQL_MAX_RECONNECTS, "4");
	       // set additional connection properties:
	       // if connection stales, then make automatically
	       // reconnect; make it alive again;
	       // if connection stales, then try for reconnection;
	       
	       
	               
	               } catch (Exception e) {
	                               JOptionPane.showMessageDialog(null, e.getMessage());
//	                               e.printStackTrace();
	                           }
	                           return conexion;
	           } else {
	               return conexion;
	           }
	       }
	   }                                                                                                                               