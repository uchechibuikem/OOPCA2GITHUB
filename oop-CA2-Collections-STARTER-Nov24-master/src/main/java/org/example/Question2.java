package org.example;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class Question2  // Car Parking - Stack
{
    public static void runSimulation() {
        Stack<Integer> driveway = new Stack<>();
        Stack<Integer> street = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Car Parking Simulation");
        System.out.println("Enter a positive number to add a car, a negative number to remove a car, or 0 to stop:");
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
