package inventoryManager.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class Pndorderprev {
	@FXML private SimpleStringProperty pltblno;
	@FXML private SimpleStringProperty plcmbcat;
	@FXML private SimpleStringProperty pclcmbfood;
	@FXML private SimpleDoubleProperty pltxtqty;
	@FXML private SimpleDoubleProperty pltxtprice;
	@FXML private SimpleDoubleProperty pltxtamt;
	@FXML private SimpleStringProperty plordate;
	@FXML private SimpleIntegerProperty plsaleid;
	
	public Pndorderprev (String pltblno, String pclcmbfood,Double pltxtqty,Double pltxtprice,Double pltxtamt) {
		this.pltblno=new SimpleStringProperty(pltblno);
				this.pclcmbfood=new SimpleStringProperty(pclcmbfood);
		this.pltxtqty=new SimpleDoubleProperty(pltxtqty);		
		this.pltxtprice = new SimpleDoubleProperty(pltxtprice);
		this.pltxtamt = new SimpleDoubleProperty(pltxtamt);

	}

	public String getPltblno() {
		return pltblno.get();
	}

	public void setPltblno(SimpleStringProperty pltblno) {
		this.pltblno = pltblno;
	}

	public String getPclcmbfood() {
		return pclcmbfood.get();
	}

	public void setPclcmbfood(SimpleStringProperty pclcmbfood) {
		this.pclcmbfood = pclcmbfood;
	}

	public Double getPltxtqty() {
		return pltxtqty.get();
	}

	public void setPltxtqty(SimpleDoubleProperty pltxtqty) {
		this.pltxtqty = pltxtqty;
	}

	public Double getPltxtprice() {
		return pltxtprice.get();
	}

	public void setPltxtprice(SimpleDoubleProperty pltxtprice) {
		this.pltxtprice = pltxtprice;
	}

	public Double getPltxtamt() {
		return pltxtamt.get();
	}

	public void setPltxtamt(SimpleDoubleProperty pltxtamt) {
		this.pltxtamt = pltxtamt;
	}
}
