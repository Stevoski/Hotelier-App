package inventoryManager.view;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TblOrdersetget {
	
	@FXML private SimpleStringProperty pltblno;
	@FXML private SimpleStringProperty plcmbcat;
	@FXML private SimpleStringProperty pclcmbfood;
	@FXML private SimpleDoubleProperty pltxtqty;
	@FXML private SimpleDoubleProperty pltxtprice;
	@FXML private SimpleDoubleProperty pltxtamt;
	@FXML private SimpleStringProperty plordate;
	@FXML private SimpleIntegerProperty plsaleid;
	public TblOrdersetget() {
		this.plordate=new SimpleStringProperty("2020-02-01");
		this.pclcmbfood=new SimpleStringProperty("fooda");
		this.pltxtqty= new SimpleDoubleProperty(0);
		this.pltblno=new SimpleStringProperty("1");
		this.plcmbcat=new SimpleStringProperty("Drinks");		
	}
	public TblOrdersetget (String plordate,String pltblno,String plcmbcat, String pclcmbfood,Double pltxtqty,Double pltxtprice) {
		this.pltblno=new SimpleStringProperty(pltblno);
		this.plcmbcat=new SimpleStringProperty(plcmbcat);
		this.pclcmbfood=new SimpleStringProperty(pclcmbfood);
		this.pltxtqty=new SimpleDoubleProperty(pltxtqty);		
	this.plordate=new SimpleStringProperty(plordate);
	this.pltxtprice = new SimpleDoubleProperty(pltxtprice);

	}

	
	public Double getPltxtprice() {
		return pltxtprice.get();
	}
	public void setPltxtprice(SimpleDoubleProperty pltxtprice) {
		this.pltxtprice = pltxtprice;
	}
	
	public String getDate() {
		return plordate.get();
	}
	public void setDate(SimpleStringProperty stockDate) {
		this.plordate= plordate;
	}

		public String getFood() {
			return pclcmbfood.get();
		
		}
		public void setFood(SimpleStringProperty food) {
			this.pclcmbfood=pclcmbfood;
		}
	public Double getQty() {
		return pltxtqty.get();
	}
	public void setQty(SimpleDoubleProperty pltxtqty) {
		this.pltxtqty=pltxtqty;
	}

	public String getCategory() {
		return plcmbcat.get();
	}
	public void setCategory(SimpleStringProperty pltxtqty) {
		this.plcmbcat=plcmbcat;
	}
	public String getTblNo() {
		return pltblno.get();
	}
	public void setTblNo(SimpleStringProperty pltblno) {
		this.pltblno=pltblno;
	}
	public Integer getPlsaleid() {
		return plsaleid.get();
	}
	public void setPlsaleid(SimpleIntegerProperty plsaleid) {
		this.plsaleid = plsaleid;
	}
}
