package inventoryManager.view;



import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import inventoryManager.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
	private Main main;
//	private MainViewController;
	private Stage primaryStage;
	private static BorderPane mainLayout;
	@FXML
	private  BorderPane loginpanel;
	@FXML
    private TextField txtUsername;
	@FXML
    private TextField txtPassword;
	@FXML
    private Button btnLogin;
	@FXML
    private Button btnChange;
	@FXML
	private void goMainview() throws IOException {
			String logarithmic=	txtUsername.getText();
	String password=DigestUtils.sha1Hex(txtPassword.getText());
	String sql="select * from usertbl where username =? and password=?";
	try {
		PreparedStatement ps1=dbConnection.getInstance().prepareStatement(sql);
ps1.setString(1, logarithmic);
ps1.setString(2,  password);
ResultSet rs=ps1.executeQuery();
if (rs.next()) {
	String pudii = rs.getString("status");
    switch (pudii) {
    case "Administrator":
      	main.showManagerViewPanel();
//      	 main.setExtendedState(main.MAXIMIZED_BOTH);
    	break;
    case "Waiter":
    	main.showWaiterViewPanel();
    	break;
    case "Manager":
   	main.showManagerViewPanel();
    	break;
    
    }
	
}

	} catch (InstantiationException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	txtUsername.setText(password);
	
	}
	private void goManagerView() throws IOException {
		main.showMainViewPanel();
		main.showManagerViewPanel();
		
	}
	private void goWaiterView() throws IOException {
		main.showMainViewPanel();
		main.showWaiterViewPanel();
	}
	
}
