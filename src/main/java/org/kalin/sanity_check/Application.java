package org.kalin.sanity_check;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        String username;
        String password;

        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = inputScanner.nextLine();
        System.out.print("Enter password: ");
        password = inputScanner.nextLine();

        Client client = new Client(username, password);
        boolean run = true;
        while (run) {
            System.out.println("Insert 1, 2 or 3 as options:\n1 : Send Sale Transaction\n2 : Send Void Transaction\n3 : Quit");
            int option = inputScanner.nextInt();

            switch (option) {
                case 1:
                    Request request = new Request("4200000000000000", 123, "10/10", 10, "coffee", "person", "test@test.com", "126 street");
                    try {
                        Response response = client.sendSaleTransaction(request);
                        System.out.println("DONE");
                        System.out.println(response.getMessage());
                        System.out.println("status: " + response.getStatus());
                        System.out.println("TRANSACTION ID: " + response.getUniqueId());
                        System.out.println("transaction time " + response.getTransactionTime());
                    } catch (ClientException clientException) {
                        System.out.println("Something bad went wrong (" + clientException.getMessage() + ")");
                        System.out.println("status code: " + clientException.getStatusCode());
                    } catch (Exception ex) {
                        System.out.println("unhandled exception, exiting...");
                        run = false;
                    }
                    break;
                case 2:
                    System.out.print("Insert the transaction id given by sale: ");
                    Scanner voidInputScanner = new Scanner(System.in);
                    String transactionId = voidInputScanner.nextLine();
                    System.out.println("TRANSACTION ID : " + transactionId);
                    Request voidRequest = new Request(transactionId);
                    try {
                        Response response = client.sendVoidTransaction(voidRequest);
                        System.out.println("DONE");
                        System.out.println(response.getMessage());
                        System.out.println("status: " + response.getStatus());
                        System.out.println("TRANSACTION ID: " + response.getUniqueId());
                        System.out.println("transaction time " + response.getTransactionTime());
                    } catch (ClientException clientException) {
                        System.out.println("Something bad went wrong (" + clientException.getMessage() + ")");
                        System.out.println("status code: " + clientException.getStatusCode());
                    } catch (Exception ex) {
                        System.out.println("unhandled exception, exiting...");
                        run = false;
                    }
                    break;
                case 3:
                    run = false;
                    break;
            }


        }
        client.close();
    }
}

