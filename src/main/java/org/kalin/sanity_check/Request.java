package org.kalin.sanity_check;

import com.google.gson.JsonObject;

public class Request {
    private String cardNumber;
    private int cvv;
    private String expirationDate;
    private int amount;
    private String usage;
    private TransactionType transactionType;
    private String cardHolder;
    private String email;
    private String address;

    private String referenceId;

    public Request(String cardNumber, int cvv, String expirationDate, int amount, String usage, String cardHolder, String email, String address) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.usage = usage;
        this.cardHolder = cardHolder;
        this.email = email;
        this.address = address;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public Request(String referenceId) {
        this.transactionType = TransactionType.Void;
        this.referenceId = referenceId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JsonObject toJsonObject() {
        JsonObject paymentTransaction = new JsonObject();

        if(this.getTransactionType() == TransactionType.Sale) {
            paymentTransaction.addProperty("card_number", this.cardNumber);
            paymentTransaction.addProperty("cvv", Integer.toString(this.cvv));
            paymentTransaction.addProperty("expiration_date", this.expirationDate);
            paymentTransaction.addProperty("amount", Integer.toString(this.amount));
            paymentTransaction.addProperty("usage", this.usage);
            paymentTransaction.addProperty("transaction_type", this.transactionType.getParameterName());
            paymentTransaction.addProperty("card_holder", this.cardHolder);
            paymentTransaction.addProperty("email", this.email);
            paymentTransaction.addProperty("address", this.address);
        } else {
            paymentTransaction.addProperty("reference_id", this.referenceId);
            paymentTransaction.addProperty("transaction_type", transactionType.getParameterName());
        }

        JsonObject json = new JsonObject();
        json.add("payment_transaction", paymentTransaction);
        return json;
    }
}
