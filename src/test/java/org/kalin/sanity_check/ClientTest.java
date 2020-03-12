package org.kalin.sanity_check;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientTest {
    private Client client;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String username = "pandahapva";
        String password = "kachamak";
        client = new Client(username, password);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() throws Exception {
        client.close();
    }

    @org.junit.jupiter.api.Test
    void sendValidSaleTransaction() throws Exception {
        Request request = new Request("4200000000000000", 123, "10/10", 10, "coffee", "person", "test@test.com", "126 street");
        Response response = client.sendSaleTransaction(request);

        assertNotNull(response);
        assertEquals(response.getStatus(), "approved", "Status should be approved");
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @org.junit.jupiter.api.Test
    void sendValidVoidTransaction() throws Exception {
        // first add a new sale
        Request createRequest = new Request("4200000000000000", 123, "10/10", 10, "coffee", "person", "test@test.com", "126 street");
        Response createResponse = client.sendSaleTransaction(createRequest);

        assertNotNull(createResponse);

        // then void it
        Request request = new Request(createResponse.getUniqueId());
        Response response = client.sendVoidTransaction(request);

        assertNotNull(response);
        assertEquals(response.getStatus(), "approved", "Status should be approved");
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @org.junit.jupiter.api.Test
    void sendInvalidSaleTransaction() throws Exception {
        Client invalidClient = new Client("invalid", "invalid");
        Request request = new Request("4200000000000000", 123, "10/10", 10, "coffee", "person", "test@test.com", "126 street");
        try {
            invalidClient.sendSaleTransaction(request);
        } catch (ClientException clientException) {
            assertEquals(clientException.getStatusCode(), 401);
        }
    }

    @org.junit.jupiter.api.Test
    void sendInvalidVoidTransaction() throws Exception {
        Request request = new Request("invalidId");
        try {
            client.sendVoidTransaction(request);
        } catch (ClientException clientException) {
            assertEquals(clientException.getStatusCode(), 422);
        }

    }
}