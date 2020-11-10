package inventoryManager;

import java.io.IOException;

import javax.management.loading.PrivateClassLoader;

import inventoryManager.view.ManagerUploadStock;
import inventoryManager.view.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
private Stage primaryStage;
private static BorderPane mainLayout;
Parent root;
Stage stage;

	@Override
	public void start(Stage primaryStage) throws IOException {
		 root=FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Cool Hotel");
//		showMainView();
		showLoginView();
//		showManagerView();
//		showWaiterView();
		
	}
	private void showLoginView() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
		mainLayout=loader.load();
		Scene scene=new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
//		System.out.println(System.getProperties());
		
		
		
	}
	private void showMainView() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout=loader.load();
		Scene scene=new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println(System.getProperties());	
	}
	public void showManagerView() throws IOException {	
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/ManagerView.fxml"));
		BorderPane ManagerView=loader.load();
		Screen screen=Screen.getPrimary();
		Rectangle2D bounds=screen.getVisualBounds();
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		mainLayout.setCenter(ManagerView);				
	}
	
	public void showWaiterView()throws IOException{
		FXMLLoader waitloader=new FXMLLoader();
		waitloader.setLocation((Main.class.getResource("view/WaiterView.fxml")));
		BorderPane waiterView=waitloader.load();
		Screen screen=Screen.getPrimary();
		Rectangle2D bounds=screen.getVisualBounds();
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		mainLayout.setCenter(waiterView);
	}
	
	public static void showMainViewPanel() throws IOException {
		FXMLLoader mainloader=new FXMLLoader();
		mainloader.setLocation((Main.class.getResource("view/MainView.fxml")));
	BorderPane mainView=mainloader.load();
	mainLayout.setCenter(mainView);
}
//
	public static void showWaiterViewPanel() throws IOException {
		FXMLLoader waitloader=new FXMLLoader();
		waitloader.setLocation((Main.class.getResource("view/WaiterView.fxml")));
	BorderPane waiterView=waitloader.load();
	mainLayout.setCenter(waiterView);
}
	public static void showManagerViewPanel() throws IOException {
//		Rectangle2D bounds=screen.getVisualBounds();
//		primaryStage.setWidth(bounds.getWidth());
//		primaryStage.setHeight(bounds.getHeight());
		
		FXMLLoader managerloader=new FXMLLoader();
		managerloader.setLocation((Main.class.getResource("view/ManagerView.fxml")));
	BorderPane managerView=managerloader.load();
	mainLayout.setCenter(managerView);
}
	public ObservableList<Person> getProducts() {
		ObservableList<Person> products=FXCollections.observableArrayList();
		return products ;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
