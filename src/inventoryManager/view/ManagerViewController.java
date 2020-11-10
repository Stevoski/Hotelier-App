package inventoryManager.view;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class ManagerViewController implements Initializable{
	@FXML private TableView<Person> tblupstocks;
	@FXML		private TableColumn<Person, String> coldate;
		@FXML	private TableColumn<Person, String> colfood;
		@FXML		private TableColumn<Person, Double> colqty;
		@FXML private Button btnupremove;
	 @FXML private Button btnupsave;
	 @FXML		private ChoiceBox<ComboValues> cmbupcategory;
	 @FXML		private TextField cmbupfood;
	 @FXML		private TextField txtupqty;
	 @FXML		private DatePicker stockDate;
	 @FXML		private TextField foodcmbauto;
	 @FXML		private DatePicker prdate;
	 @FXML		private  ChoiceBox<ComboValues> prcategory;
	 @FXML		private TextField pritem;
	 @FXML		private TextField prqty;
	 @FXML		private TextField prsupplier;
	 @FXML		private TextField prprice;
	 @FXML		private Button btnsalesRefresh;
	 @FXML		private Button pradd;
	 @FXML		private Button prsaveall;
	 @FXML		private Button prcanceladd;
	 @FXML private TableView<MansalesPrev>tblmansalesprev;
	 @FXML	private TableColumn<MansalesPrev, String> mancat;
	 @FXML	private TableColumn<MansalesPrev, String> mantbl;
	 @FXML	private TableColumn<MansalesPrev, String> mannby;
	 @FXML		private TableColumn<MansalesPrev, Double> manamt;
	 	 
	 @FXML private TableView<TblPurchase>  tblpurchase;
	 @FXML	private TableColumn<TblPurchase, String> pcoldate;
	 @FXML	private TableColumn<TblPurchase, String> pcolcategory;
	 @FXML	private TableColumn<TblPurchase, String> pcolitem;
	 @FXML		private TableColumn<TblPurchase, Double> pcolqty;
	 @FXML		private TableColumn<TblPurchase, Double> pcolprice;
		@FXML	private TableColumn<TblPurchase, String> pcolsupplier;	
		
	@Override
	public void initialize(URL Location, ResourceBundle resources) {
		mancat.setCellValueFactory(new PropertyValueFactory<MansalesPrev, String>("Mancat"));
		mantbl.setCellValueFactory(new PropertyValueFactory<MansalesPrev, String>(("Mantbl")));
		mannby.setCellValueFactory(new PropertyValueFactory<MansalesPrev, String>(("Mansby")));
		manamt.setCellValueFactory(new PropertyValueFactory<MansalesPrev, Double>(("Manamt")));
		
		coldate.setCellValueFactory(new PropertyValueFactory<Person, String>("Date"));
		colfood.setCellValueFactory(new PropertyValueFactory<Person, String>(("Food")));
		colqty.setCellValueFactory(new PropertyValueFactory<Person, Double>(("qty")));
				tblupstocks.setItems(getProducte());			
				
				pcoldate.setCellValueFactory(new PropertyValueFactory<TblPurchase, String>(("Date")));		
				pcolcategory.setCellValueFactory(new PropertyValueFactory<TblPurchase, String>(("Category")));
				pcolitem.setCellValueFactory(new PropertyValueFactory<TblPurchase, String>(("Item")));
				pcolqty.setCellValueFactory(new PropertyValueFactory<TblPurchase, Double>(("Quantity")));
				pcolprice.setCellValueFactory(new PropertyValueFactory<TblPurchase, Double>(("Price")));
				pcolsupplier.setCellValueFactory(new PropertyValueFactory<TblPurchase, String>(("Supplier")));				
				
	String [] possibleys= {"Chips","Fish","Soda","pilau","Samosa"};
		cmbupcategory.setItems(getcmbcategory());
	TextFields.bindAutoCompletion(pritem, foodAutocomplete());
	TextFields.bindAutoCompletion(cmbupfood, foodAutocomplete());
		tblupstocks.setItems(data);
		prcategory.setItems(getcmbcategory());
	}	
	
	public void getsalespreview() {
		try {
			String sqlget="select * from sales_view where order_status='sold' order by date ";
					PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
								ResultSet rs=psget.executeQuery();
			while(rs.next()) {					
				salesdata.add(new MansalesPrev(rs.getString("date"),rs.getString("table_no"),rs.getDouble("sales_amount"),rs.getString("order_status")));						
			}


			tblmansalesprev.setItems(salesdata);
			//			tblupstocks.setItems(null);
		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> foodAutocomplete() {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement psffood;
		try {
			psffood = dbConnection.getInstance().prepareStatement("select item_name from all_items where `item_category`='food'");
		
		ResultSet rs;
		try {
			rs = psffood.executeQuery();		
		
		try {
			while (rs.next()) {
				list.add(rs.getString("item_name"));
			
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		} catch (InstantiationException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return list;
		
	}
	@FXML
		public void addItems() {
//		String food=cmbupfood.getsel
				Person addprod= new Person(stockDate.getValue().toString(),cmbupfood.getText(), Double.valueOf(txtupqty.getText()));		
				tblupstocks.getItems().add(addprod);
					}
	@FXML
	public void salesGenerate() {
		try {
			
			String sqlget="select * from sales_table";
			PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
			ResultSet rs=psget.executeQuery();
			while(rs.next()) {					
					data.add(new Person(rs.getString("date"),rs.getString("food"),rs.getDouble("quantity")));						
															}
			
			tblupstocks.setItems(data);
//			tblupstocks.setItems(null);
		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				}
	
	@FXML
	public void addpurchaseItems() {
//	String food=cmbupfood.getsel
		TblPurchase addpurch= new TblPurchase(prdate.getValue().toString(),prcategory.getValue().toString(),pritem.getText(),Double.valueOf(prprice.getText()), Double.valueOf(prqty.getText()),prsupplier.getText());
//		tblupstocks.setItems(null);	
		tblpurchase.getItems().add(addpurch);
		
	}

	ObservableList<Person> data=FXCollections.observableArrayList();
	ObservableList<TblPurchase> purchasedata=FXCollections.observableArrayList();
	ObservableList<MansalesPrev> salesdata=FXCollections.observableArrayList();
//	private ObservableList<Person>data;
	@FXML
	public void saveAll() {
//		data=FXCollections.observableArrayList();
		String datee=stockDate.getValue().toString();
				String foodee=cmbupfood.getText();
		Double qtyee=Double.valueOf(txtupqty.getText());
		try {
			PreparedStatement psSaveAll=dbConnection.getInstance().prepareStatement("insert into update_food (date,food,quantity) values (?,?,?)");
			psSaveAll.setString(1, datee);
			psSaveAll.setString(2, foodee);
			psSaveAll.setDouble(3, qtyee);
			psSaveAll.executeUpdate();
			System.out.println("saved to db");
			try {
				
				String sqlget="select * from update_food";
				PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
				ResultSet rs=psget.executeQuery();
				while(rs.next()) {					
						data.add(new Person(rs.getString("date"),rs.getString("food"),rs.getDouble("quantity")));						
								
									}
				
				tblupstocks.setItems(data);
//				tblupstocks.setItems(null);
			} catch (InstantiationException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@FXML
	public void saveAllfromDb() {
//		data=FXCollections.observableArrayList();
		String datee=stockDate.getValue().toString();
				String foodee=cmbupfood.getText();
		Double qtyee=Double.valueOf(txtupqty.getText());
		try {
			PreparedStatement psSaveAll=dbConnection.getInstance().prepareStatement("insert into update_food (date,food,quantity) values (?,?,?)");
for (int i=0; i<data.size(); i++) {			
			psSaveAll.setString(1, data.get(i).getDate().toString());
			psSaveAll.setString(2, data.get(i).getFood().toString());
			psSaveAll.setDouble(3, data.get(i).getQty().doubleValue());
			psSaveAll.executeUpdate();
}
			System.out.println("saved to db");
			try {
				
				String sqlget="select * from update_food";
				PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
				ResultSet rs=psget.executeQuery();
				while(rs.next()) {					
						data.add(new Person(rs.getString("date"),rs.getString("food"),rs.getDouble("quantity")));						
								
									}
					
				
				tblupstocks.setItems(data);

			} catch (InstantiationException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@FXML
	public void savePurchase() {
//		data=FXCollections.observableArrayList();
		String datee=prdate.getValue().toString();
				String categs=prcategory.getSelectionModel().getSelectedItem().toString();
		Double qtyee=Double.valueOf(prqty.getText());
		Double pricee=Double.valueOf(prprice.getText());
		String itema=pritem.getText();
		String sppl=prsupplier.getText();
		try {
			PreparedStatement psSaveAll=dbConnection.getInstance().prepareStatement("insert into purchasestbl(date,category,item_name,item_qty,item_price,supplier) values (?,?,?,?,?,?)");
			for(int i=0; i<purchasedata.size(); i++) {
			psSaveAll.setString(1, purchasedata.get(i).getDate());
			psSaveAll.setString(2, purchasedata.get(i).getCategory());
			psSaveAll.setString(3, purchasedata.get(i).getItem());
			psSaveAll.setDouble(4, purchasedata.get(i).getQuantity());
			psSaveAll.setDouble(5, purchasedata.get(i).getPrice());
			psSaveAll.setString(6, purchasedata.get(i).getSupplier());
			
			psSaveAll.executeUpdate();
			}
			System.out.println("saved to db");
			try {
				
				String sqlget="select * from purchasestbl ";
				PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
				ResultSet rs=psget.executeQuery();
				while(rs.next()) {
									
					purchasedata.add(new TblPurchase(rs.getString("date"), rs.getString("category"), rs.getString("item_name"), rs.getDouble("item_qty"), rs.getDouble("item_price"), rs.getString("supplier")));
				}
				
				tblpurchase.setItems(purchasedata);
//				tblpurchase.setItems(null);
			} catch (InstantiationException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public ObservableList<Person> getProducte(){
	ObservableList<Person> productse=FXCollections.observableArrayList(
			new Person("2020-09-01","stravoski",3.0)			
			);
	return productse;
	}
	public ObservableList<ComboValues> getcmbFood(){
		ObservableList<ComboValues> foode=FXCollections.observableArrayList();
			new ComboValues("chips");
		return foode;
		}
	public ObservableList<ComboValues> getcmbcategory(){
		ObservableList<ComboValues> catss=FXCollections.observableArrayList(
				new ComboValues("food"),
				new ComboValues("fuel"),
				new ComboValues("raw material")

				);
		return catss;
		}
 
 
}
