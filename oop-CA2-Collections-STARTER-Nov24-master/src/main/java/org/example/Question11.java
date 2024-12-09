package org.example;

import java.util.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *  Name: Seán Afolabi
 *  Class Group: SD2B
 */
public class Question11
{
    public static void main(String[] args)
    {
        // Map to keep the connections between the cities
        Map<String, TreeSet<DistanceTo>> connections = new HashMap<>();

        // Reading input file via Scanner
        try (Scanner fileScan = new Scanner(new File("input.txt")))
        {
            while(fileScan.hasNextLine())
            {
                String line = fileScan.nextLine().trim();

                if(line.isEmpty())
                {
                    continue;
                }

                String[] parts = line.split("//s+");

                if(parts.length < 3)
                {
                    continue;
                }

                String city1 = parts[0];
                String city2 = parts[1];
                int distance = 0;

                try
                {
                    distance = Integer.parseInt(parts[2]);
                }
                // Code Reference: https://www.javatpoint.com/numberformatexception-in-java
                catch (NumberFormatException e)
                {
                    System.out.println("Incorrect distance value at line: " + line);
                }

                // Adds new entries if they don't exist
                // Code reference: https://www.w3schools.com/java/ref_hashmap_putifabsent.asp
                connections.putIfAbsent(city1, new TreeSet<>());
                connections.putIfAbsent(city2, new TreeSet<>());

                connections.get(city1).add(new DistanceTo(city2, distance));
                connections.get(city2).add(new DistanceTo(city1, distance));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Input file not found.");
            return;
        }

        // Determine the starting point with the first city being the first line.
        String startPoint;
        try {
            startPoint = connections.keySet().iterator().next();
        } catch (NoSuchElementException e) {
            System.out.println("Error: No starting point found. The connections map is empty.");
            return;
        }

        // Find the shortest distance
        Map<String, Integer> shortestDistance = findShortestPaths(startPoint, connections);

        // Print the shortest distance
        System.out.println("Shortest distance between " + startPoint + ":");
        // Code reference: https://www.geeksforgeeks.org/hashmap-entryset-method-in-java/
        for (Map.Entry<String, Integer> entry : shortestDistance.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method for finding the shortest distances
    private static Map<String, Integer> findShortestPaths(String start, Map<String, TreeSet<DistanceTo>> connections)
    {
        // Priority Queue based on the cities with the shortest distance
        PriorityQueue<DistanceTo> priorityQueue = new PriorityQueue<>();
        Map<String, Integer> shortestDistance = new HashMap<>();

        // Here, we add the starting city to the queue.
        priorityQueue.add(new DistanceTo(start, 0));

        while(!priorityQueue.isEmpty())
        {
            DistanceTo current = priorityQueue.poll();
            String targetCity = current.getTarget();
            int currentDistance = current.getDistance();


            // Skip if processed
            if(shortestDistance.containsKey(targetCity))
            {
                continue;
            }

            // Marks the city with its shortest know distance
            shortestDistance.put(targetCity, currentDistance);

            // Add all direct connections to the queue
            if(connections.containsKey(targetCity))
            {
                for(DistanceTo neighbor : connections.get(targetCity))
                {
                    if(!shortestDistance.containsKey((neighbor.getTarget())))
                    {
                        int newDistance = currentDistance + neighbor.getDistance();
                        priorityQueue.add(new DistanceTo(neighbor.getTarget(), newDistance));
                    }
                }
            }
        }

        return shortestDistance;
    }
}
