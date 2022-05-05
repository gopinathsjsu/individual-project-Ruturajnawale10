package Inventory;
import java.util.List;

public interface OutputStrategy {
	public void writeToFile(String cardNumber, String directory, List<ItemsRequested> itemsRequested);
}
