package com.chatapp;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Specify 'server' or 'client'");
        } else if (args[0].equals("server")) {
            int port = 8989;
            Server server = new Server(port);
            server.execute();
        } else if (args[0].equals("client")) {
            String hostname = "localhost";
            int port = 8989;
            Client client = new Client(hostname, port);
            client.execute();
        } else {
            System.err.println("Specify 'server' or 'client'");
        }
    }
}
