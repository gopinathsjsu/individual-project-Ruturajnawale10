package Inventory;import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException; 
import java.io.PrintWriter;
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
		
		int i = 0;
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			//ignore the fist line in input csv
			if (i == 0) {
				i += 1;
				continue;
			}
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
		
		List <ItemsRequested> invalidItems = inventory.order(itemsRequested);
		if (invalidItems.size() == 0) {
			System.out.println("Valid order");
			System.out.println("Total price is: " + inventory.totalPrice);
			inventory.addCard(cardNumber);
			System.out.println("Card added in inventory card list!");
			
			PrintWriter writer = new PrintWriter("/Users/ruturaj/Library/CloudStorage/OneDrive-sjsu.edu/Output.csv");
			StringBuilder sb = new StringBuilder();
			
			sb.append("Item");
			sb.append(',');
			sb.append("Quantity");
			sb.append(',');
			sb.append("Price");
			sb.append(',');
			sb.append("TotalPrice");
			sb.append('\n');
			
			i = 0;
			for (ItemsRequested item: itemsRequested) {
				sb.append(item.itemName);
				sb.append(',');
				sb.append(item.quantityRequested);
				sb.append(',');
				int price = inventory.getPrice(item.itemName);
				sb.append(price);
				
				if (i == 0) {
					sb.append(',');
					sb.append(inventory.totalPrice);
					i += 1;
				}
				sb.append('\n');
			}

		      writer.write(sb.toString());
		      writer.close();

		      System.out.println("done!");
		} else {
			PrintWriter writer = new PrintWriter("/Users/ruturaj/Library/CloudStorage/OneDrive-sjsu.edu/Output.csv");
			StringBuilder sb = new StringBuilder();
			
			sb.append("Please correct quantities.\n");
			sb.append("Item");
			sb.append(',');
			sb.append("Quantity");
			sb.append('\n');
			
			System.out.println("Invalid order. Invalid items are: ");
			for(i = 0; i < invalidItems.size(); i++) {
				sb.append(invalidItems.get(i).itemName);
				sb.append(',');
				sb.append(invalidItems.get(i).quantityRequested);
				sb.append('\n');
				System.out.println("Item Name: " + invalidItems.get(i).itemName + " Quantity: " + invalidItems.get(i).quantityRequested);
			}
		}
	}
 }
