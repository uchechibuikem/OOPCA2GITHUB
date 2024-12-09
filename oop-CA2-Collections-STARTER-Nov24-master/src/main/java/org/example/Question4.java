package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Seán Afolabi
 *  Class Group: SD2B
 */

public class Question4  // Flood Fill (Stack, 2D Array)
{
    public static void main(String[] args) {
        start();
    }
    public static void start()
    {
        Scanner kb = new Scanner(System.in);
        int[][] arr = floodFillStart();

        // Enter starting positions
        System.out.println("Enter the starting row from 0 - 9: ");
        int r = kb.nextInt();
        System.out.println("Enter the starting column from 0 - 9: ");
        int c = kb.nextInt();

        // If the starting positions are incorrect, you get an error
        if(r < 0 || r > 10 || c < 0 || c > 10)
        {
            System.out.println("Invalid starting position. Please enter the correct format");
        }

        fill(r, c, arr);

        display(arr);
    }

    /*
        Starter function to create the 2D array and populate it with zeros
     */
    public static int[][]  floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
        return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    private static void fill(int r, int c, int[][] arr)
    {
        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(r, c));
        int fillValue = 1;

        while(!stack.isEmpty())
        {
            Cell currentPos = stack.pop();
            int row = currentPos.row;
            int col = currentPos.col;

            // If the cell is already filled, we then skip it
            if(arr[row][col] != 0)
            {
                continue;
            }

            // Here, we fill the current cell
            arr[row][col] = fillValue++;

            // Cells and the fill directions
            // North
            if(row > 0 && arr[row - 1][col] == 0)
            {
                stack.push(new Cell(row - 1, col));
            }

            // South
            if(row < 9 && arr[row + 1][col] == 0)
            {
                stack.push(new Cell(row + 1, col));
            }

            // West
            if(col > 0 && arr[row][col - 1] == 0)
            {
                stack.push(new Cell(row, col - 1));
            }

            // East
            if(col < 9 && arr[row][col + 1] == 0)
            {
                stack.push(new Cell(row, col + 1));
            }
        }
    }
}
