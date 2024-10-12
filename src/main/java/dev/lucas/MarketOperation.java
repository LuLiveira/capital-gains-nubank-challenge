package dev.lucas;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public record MarketOperation (OperationType operation,
                                @SerializedName("unit-cost") //Para conviver o padrão camelCase no codigo com kebab-case do JSON
                                BigDecimal unitCost,
                               Integer quantity){

    public OperationOutput tax() {
        return new OperationOutput(new BigDecimal(0));
    }

    @Override
    public String toString() {
        return "MarketOperation{" +
                "operation=" + operation +
                ", unitCost=" + unitCost +
                ", quantity=" + quantity +
                '}';
    }
}
