package Inventory;import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 class Billing {

	public static void main(String[] args) throws Exception {
		String line = "";  
		String cardNumber = "";
		List <ItemsRequested> itemsRequested = new ArrayList<ItemsRequested>();
		 
		//parsing a CSV file into BufferedReader class constructor  
		BufferedReader br = new BufferedReader(new FileReader("/Users/ruturaj/Library/CloudStorage/OneDrive-sjsu.edu/Input.csv"));  
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] items = line.split(",");    // use comma as separator 
			if (items.length == 3) {
				cardNumber = items[2];
				System.out.println("Item Name: " + items[0] + ", Quantity: " + items[1] + ", Card Number: " + items[2]);  
			}
			ItemsRequested item = new ItemsRequested(items[0], Integer.parseInt(items[1]));
			itemsRequested.add(item);
		}   
		
		Inventory inventory = Inventory.getInstance();
		HashMap<String, Integer> capCategory = inventory.getCapCategory();
		for (Map.Entry<String, Integer> set: capCategory.entrySet()) {
			System.out.println(set.getKey() + " -> " + set.getValue());
		}
	}
 }
