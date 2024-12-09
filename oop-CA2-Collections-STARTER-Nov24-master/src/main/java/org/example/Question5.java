package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

/**
 *  Name:Uche Chibuikem
 *  Class Group:SD2B
 */
public class Question5 {    //Java Identifier Count (Map)

    public static void readFile(String fileName)
    {
        Map<String, Integer> identifierCountMap = new HashMap<>();
        Map<String, List<String>> identifierLinesMap = new HashMap<>();

        // Regex to match identifiers
        Pattern identifierPattern = Pattern.compile("\\b[A-Za-z_][A-Za-z0-9_]*\\b");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                Matcher matcher = identifierPattern.matcher(line);

                while (matcher.find()) {
                    String identifier = matcher.group();

                    // Update the count in identifierCountMap
                    identifierCountMap.put(identifier, identifierCountMap.getOrDefault(identifier, 0) + 1);

                    // Add the line to identifierLinesMap
                    identifierLinesMap
                            .computeIfAbsent(identifier, k -> new ArrayList<>())
                            .add(lineNumber + ". " + line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Output the results
        System.out.println("Identifiers and their details:\n");
        identifierCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String identifier = entry.getKey();
                    int count = entry.getValue();
                    List<String> lines = identifierLinesMap.get(identifier);

                    System.out.println(identifier + "   " + count);
                    if (lines != null) {
                        lines.forEach(System.out::println);
                    }
                });
    }

    public static void main(String[] args) throws FileNotFoundException {
        try {
            readFile("C:\\Users\\chibu\\OneDrive\\Desktop\\OOP CA2\\oop-CA2-Collections-STARTER-Nov24-master\\oop-CA2-Collections-STARTER-Nov24-master\\src\\main\\java\\org\\example\\Question2.java");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
