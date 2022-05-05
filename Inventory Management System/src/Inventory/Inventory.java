package Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.TreeMap;

public class Inventory {
	private static Inventory inventory;
	private TreeMap <String, InventoryItem> inventoryItems;
	private List <String> cards;
	private HashMap<String, Integer> capCategory;
	public int totalPrice = 0;
	private HashMap<String, Integer> capCategoryCount;
	 
	
	private Inventory() {
		inventoryItems = new TreeMap<String, InventoryItem>(String.CASE_INSENSITIVE_ORDER);
		
		InventoryItem item = new InventoryItem("Essentials", 100, 20);
		inventoryItems.put("Clothes", item);
		
		item = new InventoryItem("Essentials", 200, 5);
		inventoryItems.put("Soap", item);
		
		item = new InventoryItem("Essentials", 200, 10);
		inventoryItems.put("Shampoo", item);
		
		item = new InventoryItem( "Essentials", 100, 5);
		inventoryItems.put("Milk", item);
		
		item = new InventoryItem("Luxury", 50, 53);
		inventoryItems.put("Perfume", item);
		
		item = new InventoryItem("Luxury", 300, 3);
		inventoryItems.put("Chocolates", item);
		
		item = new InventoryItem("Luxury", 75, 150);
		inventoryItems.put("Handbag", item);
		
		item = new InventoryItem("Luxury", 100, 100);
		inventoryItems.put("Wallet", item);
		
		item = new InventoryItem("Misc", 150, 75);
		inventoryItems.put("Bedsheet", item);
		
		item = new InventoryItem("Misc", 200, 25);
		inventoryItems.put("Footware", item);
		
		item = new InventoryItem("Misc", 100, 40);
		inventoryItems.put("HomeDecorPiece", item);
		
		item = new InventoryItem("Misc", 400, 3);
		inventoryItems.put("Pen", item);
		
		item = new InventoryItem("Misc", 400, 20);
		inventoryItems.put("Pencil", item);
		
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

		capCategoryCount = new HashMap<String, Integer>();
	}
	
	public static Inventory getInstance() {
		if (inventory == null) {
			inventory = new Inventory();
		}
		return inventory;
	}
	
	public TreeMap getItems() {
		return inventoryItems;
	}
	
	public List getCards() {
		return cards;
	}
	public HashMap getCapCategory() {
		return capCategory;
	}
	
	public boolean checkQuantity(String itemName, int quantityRequested) {
		InventoryItem item = inventoryItems.get(itemName);
		if(quantityRequested > item.stock) {
			System.out.println("Invalid "+ quantityRequested);
			return false;
		} else {
			//check cap of category if valid
			int currCount = capCategoryCount.getOrDefault(item.category, 0) + 1;
			if (currCount < capCategory.get(item.category)) {
				capCategoryCount.put(item.category, currCount);
				return true;
			} else {
				return false;
			}
		}
	}
	
	public List order( List <ItemsRequested> itemsRequested) {
		List <ItemsRequested> invalidItems = new ArrayList<ItemsRequested>();
		totalPrice = 0;
		for (int i = 0; i < itemsRequested.size(); i++) {
			ItemsRequested item = itemsRequested.get(i);
			if (inventory.checkQuantity(item.itemName, item.quantityRequested)) {
				InventoryItem item1 = inventoryItems.get(item.itemName);
				totalPrice += item1.price* item.quantityRequested;
				System.out.println("Valid");
			} else {
				invalidItems.add(item);
				System.out.println(item.itemName);
			}
		}
		return invalidItems;
		
	}
	
	public void addCard(String card) {
		cards.add(card);
	}
	
	public int getPrice(String itemName) {
		InventoryItem item = inventoryItems.get(itemName);
		return item.price;
	}
}
