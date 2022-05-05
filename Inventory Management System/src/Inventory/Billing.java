package Inventory;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 class Billing {

	public static void main(String[] args) throws Exception {
		String line = "";  
		String cardNumber = "";
		List <ItemsRequested> itemsRequested = new ArrayList<ItemsRequested>();
		 
		Scanner inScanner = new Scanner(System.in);
	    //Example  /Users/xyz/Input.csv
		System.out.print("Enter file path of input csv:");
		
		String filePath = inScanner.next();
		inScanner.close();
		
		int lastIndexOfSlash = filePath.lastIndexOf("/");
		String directory = filePath.substring(0, lastIndexOfSlash + 1);
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));  
		
		int i = 0;
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			//ignore the fist line in input CSV which does not consist of actual items
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
		
		br.close();
		
		Inventory inventory = Inventory.getInstance();
		
		List <ItemsRequested> invalidItems = inventory.order(itemsRequested);
		
		OutputStrategy outputStrategy;
		if (invalidItems.size() == 0) {
			//this strategy writes to a CSV file
			outputStrategy = new SuccessStrategy();
			outputStrategy.writeToFile(cardNumber, directory, itemsRequested);
		} else {
			//this strategy writes to a TXT file
			outputStrategy = new FailureStrategy();
			outputStrategy.writeToFile(null, directory, invalidItems);
		}
	}
 }
