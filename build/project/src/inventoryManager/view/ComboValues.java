package inventoryManager.view;

import javafx.beans.property.SimpleStringProperty;

public class ComboValues {
private String cmbupfood;
private String cmbupcategory;
public ComboValues(String cmbupfood) {
	this.cmbupfood=new String(cmbupfood);
}
public void setValues(String cmbupfood) {
	this.cmbupfood=cmbupfood;
}
public String getValues() {
	return cmbupfood;
}
@Override
public String toString() {
	return this.getValues();
}
}

