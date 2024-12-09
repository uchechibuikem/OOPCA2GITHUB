import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name: Brandyvie Mbuyi Mayombo
 * Class Group:sd2B
 */
public class Question7 
{
    
    static class Block {
        int quantity;
        double price;

        public Block(int quantity, double price) {
            this.quantity = quantity;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter command (buy/sell/quit): ");
        Scanner in = new Scanner(System.in);
        Queue<Block> stockQueue = new LinkedList<>(); // FIFO queue to manage blocks of shares
        String command = "";

        do {
            System.out.print(">");
            command = in.next();

            if (command.equalsIgnoreCase("buy")) {
                
                int qty = in.nextInt();
                double price = in.nextDouble();
                stockQueue.add(new Block(qty, price)); // Add block to queue
                System.out.println("Bought " + qty + " shares at $" + price + " each.");
            } else if (command.equalsIgnoreCase("sell")) {
               
                int qty = in.nextInt();
                double sellPrice = in.nextDouble();
                double totalProfit = 0.0;

                while (qty > 0 && !stockQueue.isEmpty()) {
                    Block block = stockQueue.peek(); 
                    if (block.quantity <= qty) {
                        
                        double profit = block.quantity * (sellPrice - block.price);
                        totalProfit += profit;
                        System.out.println("Sold " + block.quantity + " shares bought at $" + block.price + " for a profit of $" + profit);
                        qty -= block.quantity;
                        stockQueue.poll(); 
                    } else {
                        
                        double profit = qty * (sellPrice - block.price);
                        totalProfit += profit;
                        block.quantity -= qty;
                        System.out.println("Sold " + qty + " shares bought at $" + block.price + " for a profit of $" + profit);
                        qty = 0;
                    }
                }

                if (qty > 0) {
                    System.out.println("Not enough shares to sell. Remaining " + qty + " shares could not be sold.");
                }

                System.out.printf("Total profit from this sale: $%.2f%n", totalProfit);
            }
        } while (!command.equalsIgnoreCase("quit"));

        System.out.println("Exiting program.");
        in.close();
    }
}

}
