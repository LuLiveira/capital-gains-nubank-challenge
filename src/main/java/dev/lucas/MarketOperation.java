package dev.lucas;

import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 *
 * @param operation
 * @param unitCost
 * @param quantity
 *
 * Encare o uso das annotations do hibernate validor como um aviso para quaisquer desenvolvedor que venha a dar manutenção no código
 * embora não tenha um validação de fato a ideia é deixar explicito que se espera que os valores respeitem o que determina cada anotação.
 *
 * Aqui estou seguindo muito do que aprendo com Alberto Souza em seu curso Dev+Eficiente e que julgo fazer sentido.
 */
public record MarketOperation (@NotNull @NotBlank OperationType operation,
                                @SerializedName("unit-cost") //Para conviver o padrão camelCase no codigo com kebab-case do JSON
                                @NotNull @NotBlank @Positive BigDecimal unitCost,
                               @NotNull @NotBlank @Min(0) Integer quantity){

    /**
     *
     * @return OperationOutput valor da taxa da operação
     */
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
