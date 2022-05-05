package Inventory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FailureStrategy implements OutputStrategy{

	@Override
	public void writeToFile(String cardNumber, String directory, List<ItemsRequested> invalidItems)  {
		System.out.println("Valid order");
		Inventory inventory = Inventory.getInstance();
		System.out.println("Total price is: " + inventory.totalPrice);
		
		//add card number in local DB
		inventory.addCard(cardNumber);
		System.out.println("Card added in inventory card list!");
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(directory + "Output.txt");
			StringBuilder sb = new StringBuilder();
			
			sb.append("Please correct quantities.\n");
			String quantityErrorType = inventory.quantityErrorType;
			sb.append("Reason for unsuccessful processing of order is: " + quantityErrorType + "\n");
			sb.append("Items with incorrect quantities are: \n");
			sb.append("Item");
			sb.append(',');
			sb.append("Quantity");
			sb.append('\n');
			
			System.out.println("Invalid order.");
			for(int i = 0; i < invalidItems.size(); i++) {
				sb.append(invalidItems.get(i).itemName);
				sb.append(',');
				sb.append(invalidItems.get(i).quantityRequested);
				sb.append('\n');
			}
			writer.write(sb.toString());
		    writer.close();
		    System.out.println("Wrote to output txt file!");
		} catch (FileNotFoundException e) {
			System.out.println("Failed with exception: " + e);
			e.printStackTrace();
		}	
	}
}
