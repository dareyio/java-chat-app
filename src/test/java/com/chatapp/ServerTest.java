package com.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {
    @Test
    public void testAddUser() {
        Server server = new Server(8989);
        server.addUser("user1");
        assertTrue(server.hasUsers());
    }
}
