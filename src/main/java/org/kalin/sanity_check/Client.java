package org.kalin.sanity_check;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

public class Client {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private String username;
    private String password;
    private String endPoint = "/payment_transactions";

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void close() throws IOException {
        httpClient.close();
    }

    private Response sendRequest(Request request) throws Exception {
        HttpPost post = new HttpPost(new URI("http", null, "localhost", 3001, endPoint, null, null));

        post.setEntity(new StringEntity(request.toJsonObject().toString()));
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        String credentialsString = username + ":" + password;
        String credentialsEncoded = Base64.getEncoder().encodeToString(credentialsString.getBytes());
        post.addHeader("Authorization", "Basic " + credentialsEncoded);
        int statusCode = 0;
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(post)) {
            String entity = EntityUtils.toString(response.getEntity());
            statusCode = response.getStatusLine().getStatusCode();

            if (entity != null && !entity.isEmpty()) {
                JsonObject result = new JsonParser().parse(entity).getAsJsonObject();


                return new Response(result.get("unique_id").getAsString(), result.get("status").getAsString(), result.get("usage").getAsString(), result.get("amount").getAsString(), result.get("transaction_time").getAsString(), result.get("message").getAsString(), statusCode);
            } else {
                throw new ClientException("entity null", statusCode);
            }
        } catch (Exception e) {
            throw new ClientException("unknownException", statusCode);
        }
    }
}
