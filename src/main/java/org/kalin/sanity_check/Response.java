package org.kalin.sanity_check;

public class Response {
    private String uniqueId;
    private String status;
    private String usage;
    private String amount;
    private String transactionTime;
    private String message;
    private int statusCode;

    public Response(String uniqueId, String status, String usage, String amount, String transactionTime, String message, int statusCode) {
        this.uniqueId = uniqueId;
        this.status = status;
        this.usage = usage;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
