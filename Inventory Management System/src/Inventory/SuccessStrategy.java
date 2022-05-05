package Inventory;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SuccessStrategy implements OutputStrategy {

	@Override
	public void writeToFile(String cardNumber, String directory, List<ItemsRequested> itemsRequested)  {
		System.out.println("Valid order");
		Inventory inventory = Inventory.getInstance();
		System.out.println("Total price is: " + inventory.totalPrice);
		
		//add card number in local DB
		inventory.addCard(cardNumber);
		System.out.println("Card added in inventory card list!");
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(directory + "Output.csv");
			StringBuilder sb = new StringBuilder();
			
			sb.append("Item");
			sb.append(',');
			sb.append("Quantity");
			sb.append(',');
			sb.append("Price");
			sb.append(',');
			sb.append("TotalPrice");
			sb.append('\n');
			
			int i = 0;
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

		      System.out.println("Wrote to output file!");
		} catch (FileNotFoundException e) {
			System.out.println("Failed with exception: " + e);
			e.printStackTrace();
		}
	}
}
