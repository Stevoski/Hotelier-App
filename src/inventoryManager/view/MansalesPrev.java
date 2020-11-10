package inventoryManager.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class MansalesPrev {
	@FXML private SimpleStringProperty mancat;
	@FXML private SimpleStringProperty mantbl;
	@FXML private SimpleDoubleProperty manamt;
	@FXML private SimpleStringProperty mansby;
public MansalesPrev(String mancat, String mantbl, Double manamt,String mansby) {
		super();
		this.mancat = new SimpleStringProperty(mancat);
		this.mantbl = new SimpleStringProperty(mantbl);
		this.manamt = new SimpleDoubleProperty(manamt);
		this.mansby = new SimpleStringProperty(mansby);
	}

public String getMancat() {
	return mancat.get();
}
public void setMancat(SimpleStringProperty mancat) {
	this.mancat = mancat;
}
public String getMantbl() {
	return mantbl.get();
}
public void setMantbl(SimpleStringProperty mantbl) {
	this.mantbl = mantbl;
}
public Double getManamt() {
	return manamt.get();
}
public void setManamt(SimpleDoubleProperty manamt) {
	this.manamt = manamt;
}
public String getMansby() {
	return mansby.get();
}
public void setMansby(SimpleStringProperty mansby) {
	this.mansby = mansby;
}

}
