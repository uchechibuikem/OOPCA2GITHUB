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

        System.out.println("Welcome to the Car Parking Simulation!");
        System.out.println("Instructions:");
        System.out.println("Enter a positive number to park a car (e.g., 1, 2, 3).");
        System.out.println("Enter a negative number to retrieve a car (e.g., -2).");
        System.out.println("Enter 0 to stop the simulation.");

        while (true) {
            System.out.print("\nEnter your command: ");
            int command = scanner.nextInt();

            if (command == 0) {
                System.out.println("Simulation ended.");
                break;
            }

            if (command > 0) {
                // Add car to the driveway
                driveway.push(command);
                System.out.println("Car " + command + " added to the driveway.");
            } else {
                // Retrieve a car from the driveway
                int carToRetrieve = Math.abs(command);
                if (driveway.contains(carToRetrieve)) {
                    System.out.println("Retrieving car " + carToRetrieve + "...");

                    // Move cars blocking the target car to the street
                    while (!driveway.isEmpty() && driveway.peek() != carToRetrieve) {
                        int movedCar = driveway.pop();
                        street.push(movedCar);
                        System.out.println("Moved car " + movedCar + " to the street.");
                    }
                    // Remove the target car
                    driveway.pop();
                    System.out.println("Car " + carToRetrieve + " retrieved from the driveway.");

                    // Move cars back from the street to the driveway
                    while (!street.isEmpty()) {
                        int movedCarBack = street.pop();
                        driveway.push(movedCarBack);
                        System.out.println("Moved car " + movedCarBack + " back to the driveway.");
                    }
                } else {
                    System.out.println("Car " + carToRetrieve + " is not in the driveway.");
                }
            }

            // Print the current state of the driveway
            System.out.println("Current state of driveway: " + driveway);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
