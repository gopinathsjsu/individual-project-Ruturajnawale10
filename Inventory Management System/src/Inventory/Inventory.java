package Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Inventory {
	private static Inventory inventory;
	private List <InventoryItem> inventoryItems;
	private List <String> cards;
	private HashMap<String, Integer> capCategory;
	 
	
	private Inventory() {
		inventoryItems = new ArrayList<InventoryItem>();
		InventoryItem item = new InventoryItem("Clothes", "Essentials", 100, 20);
		inventoryItems.add(item);
		item = new InventoryItem("Soap", "Essentials", 200, 5);
		inventoryItems.add(item);
		item = new InventoryItem("Shampoo", "Essentials", 200, 10);
		inventoryItems.add(item);
		item = new InventoryItem("Milk", "Essentials", 100, 5);
		inventoryItems.add(item);
		item = new InventoryItem("Perfume", "Luxury", 50, 53);
		inventoryItems.add(item);
		item = new InventoryItem("Chocolates", "Luxury", 300, 3);
		inventoryItems.add(item);
		item = new InventoryItem("Handbag", "Luxury", 75, 150);
		inventoryItems.add(item);
		item = new InventoryItem("Wallet", "Luxury", 100, 100);
		inventoryItems.add(item);
		item = new InventoryItem("Bedsheet", "Misc", 150, 75);
		inventoryItems.add(item);
		item = new InventoryItem("Footware", "Misc", 200, 25);
		inventoryItems.add(item);
		item = new InventoryItem("HomeDecorPiece", "Misc", 100, 40);
		inventoryItems.add(item);
		item = new InventoryItem("Pen", "Misc", 400, 3);
		inventoryItems.add(item);
		item = new InventoryItem("Pencil", "Misc", 400, 20);
		inventoryItems.add(item);
		
		cards = new ArrayList<String>();
		String card = "5.41E+15";
		cards.add(card);
		card = "4.12E+12";
		cards.add(card);
		card = "3.41E+14";
		cards.add(card);
		card = "6.01E+15";
		cards.add(card);
		
		capCategory = new HashMap<String, Integer>();
		capCategory.put("Essentials", 3);
		capCategory.put("Luxury", 4);
		capCategory.put("Misc", 6);
	}
	
	public static Inventory getInstance() {
		if (inventory == null) {
			inventory = new Inventory();
		}
		return inventory;
	}
	
	public List getItems() {
		return inventoryItems;
	}
	
	public List getCards() {
		return cards;
	}
	public HashMap getCapCategory() {
		return capCategory;
	}
}
