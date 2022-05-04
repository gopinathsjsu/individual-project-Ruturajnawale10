package Inventory;

public class InventoryItem {
	private String itemName;
	private String category;
	private int stock;
	private float price;
	
	public InventoryItem(String itemName, String category, int stock, float price) {
		this.itemName = itemName;
		this.category = category;
		this.stock = stock;
		this.price = price;
	}
}
