package org.example;

import java.util.*;

/**
 *  Name: Ewan Clarke
 *  Class Group: SD2b
 */

// The block class
class Block {
    int quantity;
    double price;

    public Block(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Block{" + "quantity=" + quantity + ", price=" + price + '}';
    }
}

// Main class
public class Question8 {
    // Setting up a hash map (Given hint from question)
    public final Map<String, Queue<Block>> stockMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("[*] buy (name) (quant) (price) : sell (name) (quant) : quit\n");
        stockMenu();
    }

    // Function for menu
    public static void stockMenu() {
        Question8 stockExchange = new Question8();
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = kb.nextLine();
            String[] args = input.split(" ");

            try {
                if (input.startsWith("buy")) {
                    stockExchange.buy(args[1], Integer.valueOf(args[2]), Double.valueOf(args[3]));
                } else if (input.startsWith("sell")) {
                    stockExchange.sell(args[1], Integer.valueOf(args[2]));
                } else if (input.equals("quit")) {
                    System.exit(0);
                } else {
                    System.out.println("[!] Invalid input, check your command.");
                }

                System.out.print("\n");
            } catch (Exception e) {
                System.out.println("[!] Invalid input, check your arguments.\n");
            }
        }
    }

    public void buy(String symbol, int quantity, double price) {
        symbol = symbol.toUpperCase();

        // Create a new queue inside the map if needed, then access that queue and place in the new block
        stockMap.putIfAbsent(symbol, new LinkedList<>());
        stockMap.get(symbol).add(new Block(quantity, price));

        // Tell the user
        if (quantity > 1) {
            System.out.println("[+] Bought " + quantity + " shares of " + symbol + " @ " + price + " (" + price * quantity + " in total)");
        } else {
            System.out.println("[+] Bought " + quantity + " shares of " + symbol + " @ " + price);
        }
    }

    public void sell(String symbol, int quantity) {
        symbol = symbol.toUpperCase();
        double total = 0;

        // Check if there are any shares to sell
        if ((!stockMap.containsKey(symbol)) || (stockMap.get(symbol).isEmpty())) {
            System.out.println("[!] You do not own any shares of " + symbol);
            return;
        }

        // Get them from hashMap
        Queue<Block> stockQueue = stockMap.get(symbol);
        Block block = stockQueue.peek();

        // Check how much of the stock were selling
        if (block.quantity <= quantity) {
            total += block.price * quantity;
            stockQueue.poll();

            if (quantity > 1) {
                System.out.println("[-] Sold " + quantity + " shares of " + symbol + " @ " + block.price + " (" + block.price * quantity + " in total)");
            } else {
                System.out.println("[-] Sold " + quantity + " shares of " + symbol + " @ " + block.price);
            }
        } else {
            total += block.price * quantity;
            block.quantity -= quantity;

            if (quantity > 1) {
                System.out.println("[-] Sold " + quantity + " shares of " + symbol + " @ " + block.price + " (" + block.price * quantity + " in total) with " + block.quantity + " shares left unsold");
            } else {
                System.out.println("[-] Sold " + quantity + " shares of " + symbol + " @ " + block.price + " with " + block.quantity + " shares left unsold");
            }
        }
    }
}