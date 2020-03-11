package org.kalin.sanity_check;

public enum TransactionType {
    Sale("sale"), Void("void");
    private String parameterName;

    TransactionType(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
