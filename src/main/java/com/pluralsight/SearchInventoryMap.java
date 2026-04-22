package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();

    public static void main(String[] args) {
        // this method loads product objects into inventory
        Scanner scanner = new Scanner(System.in);
        loadInventory(scanner);

        String command = "yes";

        while (command.equalsIgnoreCase("yes")) {

            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();

            Product matchedProduct = inventory.get(id);

            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f",
                        matchedProduct.getName(), matchedProduct.getPrice());
            }
            System.out.print("Do you want to search again? ");
            command = scanner.nextLine();
        }
        System.out.println("Have a good day!");
    }

    private static void loadInventory(Scanner scanner) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                List<String> tokens = new ArrayList<>(Arrays.asList(line.split("\\|")));

                int id = Integer.parseInt(tokens.get(0));
                String name = tokens.get(1);
                double price = Double.parseDouble(tokens.get(2));

                Product product = new Product(id, name, price);

                inventory.put(product.getId(), product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
