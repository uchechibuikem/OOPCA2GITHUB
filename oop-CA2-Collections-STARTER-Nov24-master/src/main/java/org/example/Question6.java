package org.example;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 *  Name: Ewan Clarke
 *  Class Group: SD2b
 */

public class Question6 { // Flight take-off (Queue)
    // Creaing the queue for both
    static Queue<String> takeOffQueue = new LinkedList<>();
    static Queue<String> landQueue = new LinkedList<>();

    // Main function that goes to menu
    public static void main(String[] args) {
        System.out.println("[*] takeoff (name) : land (name) : next : quit");
        flightMenu();
    }

    // Function for menu
    public static void flightMenu() {
        Scanner kb = new Scanner(System.in);
        System.out.print("> ");
        String input = kb.nextLine();;

        if (input.contains("takeoff")) {
            takeoff(input.substring(8));
        } else if (input.contains("land")) {
            land(input.substring(5));
        } else if (input.equals("next")) {
            next();
        } else if (input.equals("quit")) {
            System.exit(0);
        } else {
            System.out.println("[!] Invalid input");
        }

        System.out.print("\n");
        flightMenu();
    }

    // Function to add to the takeoff queue
    public static void takeoff(String flightCode) {
        takeOffQueue.add(flightCode);
        System.out.println("[+] Added flight " + flightCode + " to takeoff queue");
    }

    // Function to add to the land queue
    public static void land(String flightCode) {
        landQueue.add(flightCode);
        System.out.println("[+] Added flight " + flightCode + " to land queue");
    }

    // Function to do the next resonable action
    public static void next() {
        if (!landQueue.isEmpty()) {
            String landed = landQueue.poll();
            System.out.println("[N] Flight " + landed + " has landed");
        } else if (!takeOffQueue.isEmpty()) {
            String tookOff = takeOffQueue.poll();
            System.out.println("[N] Flight " + tookOff + " has taken off");
        } else {
            System.out.println("[N] Queue's are empty");
        }
    }
}
