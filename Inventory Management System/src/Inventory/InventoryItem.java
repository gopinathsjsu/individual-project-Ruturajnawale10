package Inventory;

public class InventoryItem {
	public String category;
	public int stock;
	public int price;
	
	public InventoryItem( String category, int stock, int price) {
		this.category = category;
		this.stock = stock;
		this.price = price;
	}
}
