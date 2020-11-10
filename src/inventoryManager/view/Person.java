package inventoryManager.view;


import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
private SimpleStringProperty food;
private SimpleDoubleProperty qty;
private SimpleStringProperty stockDate;
public Person() {
	this.stockDate=new SimpleStringProperty("2020-02-01");
	this.food=new SimpleStringProperty("fooda");
	this.qty= new SimpleDoubleProperty(0);
	}
public Person (String stockDate, String food,Double qty) {
	this.food=new SimpleStringProperty(food);
	this.qty=new SimpleDoubleProperty(qty);
this.stockDate=new SimpleStringProperty(stockDate);
}

public String getDate() {
	return stockDate.get();
}
public void setDate(SimpleStringProperty stockDate) {
	this.stockDate= stockDate;
}

	public String getFood() {
		return food.get();
	
	}
	public void setFood(SimpleStringProperty food) {
		this.food=food;
	}
public Double getQty() {
	return qty.get();
}
public void setQty(SimpleDoubleProperty qty) {
	this.qty=qty;
}

}
