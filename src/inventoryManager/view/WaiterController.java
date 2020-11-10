package inventoryManager.view;







import java.awt.BorderLayout;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.controlsfx.control.textfield.TextFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;


public class WaiterController  implements Initializable {
	@FXML private TableView<TblOrdersetget> tblplorder;
	@FXML	private TableColumn<TblOrdersetget, String> ordate;
	@FXML	private TableColumn<TblOrdersetget, String> ortblno;
	@FXML	private TableColumn<TblOrdersetget, String> orfood;
	@FXML		private TableColumn<TblOrdersetget, Double> orqty;
	@FXML		private TableColumn<TblOrdersetget, Double> orprice;
	@FXML		private TableColumn<TblOrdersetget, Double> oramt;
	@FXML		private TableColumn<TblOrdersetget, String> orcategory;

	@FXML private Button btnplorder;
	@FXML private Button	btnsaveAll;
	@FXML private Button btnProcessOder;
	@FXML private Button btndismis;
	@FXML private TextField pltblno;
	@FXML private TextField sale_id;
	@FXML private ChoiceBox<ComboValues>plcmbcat;
	@FXML private TextField pclcmbfood;
	@FXML private TextField pltxtqty;
	@FXML private TextField pltxtprice;
	@FXML private TextField pltxtamt;
	@FXML private DatePicker plordate;
	@FXML private TextField	txttry;
	@FXML private AnchorPane mainAnchor;
	@FXML private DialogPane brRptPane;
	@FXML private TableView<Pendordtbl> tblpendorder;
	@FXML private TabPane waitertabpane;
	
	
	@FXML	private TableColumn<Pendordtbl, Integer> pendid;
	@FXML	private TableColumn<Pendordtbl, String> pendate;
	@FXML	private TableColumn<Pendordtbl, String> pendtbl;
	@FXML	private TableColumn<Pendordtbl, String> pendprod;
	@FXML	private TableColumn<Pendordtbl, Double> pendqty;
	@FXML	private TableColumn<Pendordtbl, Double> pendprice;
	@FXML	private TableColumn<Pendordtbl, Double> pendamt;
	
	@FXML private TableView<Pndorderprev>tblorprev ;
	@FXML	private TableColumn<Pndorderprev, String> prevpendtbl;
	@FXML	private TableColumn<Pndorderprev, String> prevpendprod;
	@FXML	private TableColumn<Pndorderprev, Double> prevpendqty;
	@FXML	private TableColumn<Pndorderprev, Double> prevpendprice;
	@FXML	private TableColumn<Pndorderprev, Double> prevpendamt;
	@Override
	public void initialize (URL Location, ResourceBundle resources) {
		prevpendtbl.setCellValueFactory(new PropertyValueFactory<Pndorderprev, String>(("Pltblno")));
		prevpendprod.setCellValueFactory(new PropertyValueFactory<Pndorderprev, String>(("Pclcmbfood")));
		prevpendqty.setCellValueFactory(new PropertyValueFactory<Pndorderprev, Double>(("Pltxtqty")));
		prevpendprice.setCellValueFactory(new PropertyValueFactory<Pndorderprev, Double>(("Pltxtprice")));
		prevpendamt.setCellValueFactory(new PropertyValueFactory<Pndorderprev, Double>(("Pltxtamt")));	
		
		ordate.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, String>("Date"));
		ortblno.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, String>(("TblNo")));
		orfood.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, String>(("Food")));
		orqty.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, Double>(("Qty")));
		orprice.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, Double>(("Pltxtprice")));
		oramt.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, Double>(("Qty")));
		orcategory.setCellValueFactory(new PropertyValueFactory<TblOrdersetget, String>(("Category")));
		//		tblplorder.setItems(getProducte());

		pendid.setCellValueFactory(new PropertyValueFactory<Pendordtbl, Integer>(("Plsaleid")));	
		pendate.setCellValueFactory(new PropertyValueFactory<Pendordtbl, String>("Date"));
		pendtbl.setCellValueFactory(new PropertyValueFactory<Pendordtbl, String>(("TblNo")));
		pendprod.setCellValueFactory(new PropertyValueFactory<Pendordtbl, String>(("Food")));
		pendqty.setCellValueFactory(new PropertyValueFactory<Pendordtbl, Double>(("Qty")));
		pendprice.setCellValueFactory(new PropertyValueFactory<Pendordtbl, Double>(("Pltxtprice")));
		pendamt.setCellValueFactory(new PropertyValueFactory<Pendordtbl, Double>(("Pltxtamt")));

		//		pclcmbfood.setItems(getcmbFood());
		TextFields.bindAutoCompletion(pclcmbfood, foodAutocomplete());
		plcmbcat.setItems(getcmbCategory());	

	}	
	public void getorderpreview() {
		try {
String sid=sale_id.getText();
			String sqlget="select * from sales_table where sale_id=?";
					PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
			psget.setString(1, sid);
					ResultSet rs=psget.executeQuery();
			while(rs.next()) {					
				pendorderpreview.add(new Pndorderprev(rs.getString("table_no"),rs.getString("food"),rs.getDouble("qty_food"),rs.getDouble("price"),rs.getDouble("sales_amount")));						
			}
			tblorprev.setItems(pendorderpreview);
			//			tblupstocks.setItems(null);
		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printOrderDialog() {
		getorderpreview();
		final Stage dialog=new Stage();
//		dialog.initModality(Modalitys.APPLICATION_MODAL);
//		dialog.initOwner(primarystage);
//		Dialog dialog= brRptPane.new Dialog();
//		brRptPane.showAndWait();
//		dialog.setTitle("Order Details");
//		ButtonType type=new ButtonType("Ok", ButtonData.OK_DONE);
//		dialog.setDialogPane(brRptPane);
//		brRptPane.
		Scene dialogscene=new Scene(brRptPane);
				dialog.setScene(dialogscene);
		dialog.show();
	}
	
	
	public void getId() {
		Pendordtbl strak= tblpendorder.getSelectionModel().getSelectedItem();
		String id=String.valueOf(strak.getPlsaleid());
		//		sale_id.setText(pendtbl.getCellData(1));
		sale_id.setText(id);
	}
	public void processOrder() {
		Pendordtbl strak= tblpendorder.getSelectionModel().getSelectedItem();
		String id=String.valueOf(strak.getPlsaleid());
		
		//		Object id=pendtbl.gett
		String sqlget="select * from sales_table where sale_id=?";

		try {
			PreparedStatement sql=dbConnection.getInstance().prepareStatement(sqlget);
			sql.setString(1, id);
			ResultSet raes=sql.executeQuery();
			while (raes.next()) {
				PreparedStatement sps=dbConnection.getInstance().prepareStatement("update sales_table set order_status='Sold' where sale_id=?");
								sps.setString(1, id);
				sps.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Order Successfully Processed");
			getDataSales();
//
//            try {
//                Connection connn = (Connection) dbConnection.getInstance();//                JasperDesign jd = JRXmlLoader.load("C:\\Users\\Stevoski\\Documents\\Scanned Documents\\Canning\\src\\CostReport.jrxml");
//                JasperDesign jd = JRXmlLoader.load("C:\\Program Files\\Cost\\newReport.jrxml");
//                String hotsql = "select * FROM sales_table where sale_id='" + id + "'  order by Production_ID";
//                JRDesignQuery NewQuery = new JRDesignQuery();
//                NewQuery.setText(hotsql);
//                jd.setQuery(NewQuery);
//                JasperReport jr = JasperCompileManager.compileReport(jd);
//                JasperPrint jp = JasperFillManager.fillReport(jr,null, connn);
////DISPLAY IN JPANEL
//                BorderPane layoutPanel = new BorderPane();
//                Pane panelRPT = new Pane(layoutPanel);
//                JRViewer vw = new JRViewer(jp);
//                mainAnchor.prefWidthProperty();
//                Stage mtage=new Stage();
//                Scene ctage=new Scene(mainAnchor);
//                ((Node) ctage).toFront():
//                	TabPane sx= new TabPane();
//                sx.sets
////                brRptPane.br
//                
//                
////                panelRPT.setLayout(new BorderPane());
////                panelRPT.repaint();
////                panelRPT.add(vw);
////                panelRPT.revalidate();
////                MainPanel.removeAll();
////                MainPanel.add(panelRPT);
////                MainPanel.repaint();
////                MainPanel.revalidate();
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//                e.printStackTrace();
//            }
		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void getItmPrice(){
		String sqlget="select item_price from all_items where item_name=?";

		try {
			PreparedStatement sql=dbConnection.getInstance().prepareStatement(sqlget);
			sql.setString(1, pclcmbfood.getText());
			ResultSet raes=sql.executeQuery();
			if (raes.next()) {
				Double pri=raes.getDouble("item_price");
				pltxtprice.setText(String.valueOf(pri));				
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	ObservableList<TblOrdersetget> tblorder=FXCollections.observableArrayList();
	ObservableList<Pendordtbl> pendorder=FXCollections.observableArrayList();
	ObservableList<Pndorderprev> pendorderpreview=FXCollections.observableArrayList();
	@FXML
	public void addItems() {
		//	String food=cmbupfood.getsel
		TblOrdersetget addorder= new TblOrdersetget(plordate.getValue().toString(),pltblno.getText(),plcmbcat.getValue().toString(),pclcmbfood.getText(), Double.valueOf(pltxtqty.getText()), Double.valueOf(pltxtprice.getText()));
		tblplorder.getItems().add(addorder);

	}

	@FXML
	public void saveAllfromDb() {
			try {
			int numero=0;
			PreparedStatement pst = dbConnection.getInstance().prepareStatement("insert into main_sales (sales_date)values (?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, plordate.getValue().toString());
			numero = pst.executeUpdate();
			ResultSet ares = pst.getGeneratedKeys();
			while(ares.next()) {					 
				//				 String saleid=String.valueOf(ares.getInt(1));
				sale_id.setText(String.valueOf(ares.getInt(1)));
			}	
//			
						 ares.close();
			try {
				PreparedStatement psSaveAll=dbConnection.getInstance().prepareStatement("insert into sales_table (date,table_no,category,food,qty_food,price,sale_id,order_status) values (?,?,?,?,?,?,?,'Pending')");
				for (int i=0; i<tblorder.size(); i++) {	
					try {
						//	ResultSet sql=dbConnection.getInstance().prepareStatement("select item_price from all_items where item_category='food' and item_name='" + tblorder.get(i).getTblNo() + "' ").executeQuery();
						//			while (sql.next()) {

						psSaveAll.setString(1, tblorder.get(i).getDate().toString());
						psSaveAll.setString(2, tblorder.get(i).getTblNo().toString());
						psSaveAll.setString(3, tblorder.get(i).getTblNo().toString());
						psSaveAll.setString(4, tblorder.get(i).getFood().toString());
						psSaveAll.setDouble(5, tblorder.get(i).getQty().doubleValue());
						psSaveAll.setDouble(6, tblorder.get(i).getPltxtprice().doubleValue());
						psSaveAll.setString(7, sale_id.getText());
						String dates=tblorder.get(i).getDate().toString();
						String tblno=tblorder.get(i).getTblNo().toString();
						String id= sale_id.getText();
						txttry.setText(id);
//						System.out.println("+dates,+tblno,+id ");
						psSaveAll.executeUpdate();
						System.out.println("saved to db");
						//				 }
						//			sql.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			
				try {

					String sqlget="select * from sales_table where sale_id=?";
					PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
					psget.setString(1, sale_id.getText());
					ResultSet rs=psget.executeQuery();
					while(rs.next()) {					
						tblorder.add(new TblOrdersetget(rs.getString("date"),rs.getString("food"),rs.getString("table_no"),rs.getString("food"),rs.getDouble("qty_food"),rs.getDouble("price")));						

					}

					tblplorder.setItems(tblorder);			

				} catch (InstantiationException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//			 psSaveAll.close();
			} catch (InstantiationException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

		} catch (InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void getDataSales() {
		try {

			String sqlget="select * from sales_table where order_status='Pending'";
			PreparedStatement psget=dbConnection.getInstance().prepareStatement(sqlget);
			ResultSet rs=psget.executeQuery();
			while(rs.next()) {					
				pendorder.add(new Pendordtbl(rs.getInt("sale_id"),rs.getString("date"),rs.getString("table_no"),rs.getString("food"),rs.getString("food"),rs.getDouble("qty_food"),rs.getDouble("price"),rs.getDouble("sales_amount")));						
			}


			tblpendorder.setItems(pendorder);
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

					//			foodcmbauto.bindAutocompletion(foodcmbauto, list);
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

	public ObservableList<TblOrdersetget> getProducte(){
		ObservableList<TblOrdersetget> productse=FXCollections.observableArrayList(
				new TblOrdersetget("2020-09-01","1","Food","chips",3.0,4.0)			
				);
		return productse;
	}
	public ObservableList<ComboValues> getcmbFood(){
		ObservableList<ComboValues> foode=FXCollections.observableArrayList(
				new ComboValues("chips")			
				);
		return foode;
	}
	public ObservableList<ComboValues> getcmbCategory(){
		ObservableList<ComboValues> foode=FXCollections.observableArrayList(
				new ComboValues("Food"),
				new ComboValues("Drinks"),
				new ComboValues("Desserts")			
				);
		return foode;
	}


}
