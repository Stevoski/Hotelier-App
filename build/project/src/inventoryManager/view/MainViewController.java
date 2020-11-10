package inventoryManager.view;

import java.io.IOException;

import inventoryManager.Main;
import javafx.fxml.FXML;

public class MainViewController {
private Main main;
@FXML
private void goWaiter() throws IOException {
	main.showWaiterViewPanel();
}
@FXML
private void goManager() throws IOException {
	main.showManagerViewPanel();
}

}
