package inventoryManager.view;

public class TblPurchase {
	private String date;
	private String category;
	private String item;
	private Double quantity;
	private Double price;
	private String supplier;
	public TblPurchase() {
		
	}

	public TblPurchase(String date, String category, String item, Double quantity, Double price, String supplier) {
				this.date = date;
		this.category = category;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
		this.supplier = supplier;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
}
