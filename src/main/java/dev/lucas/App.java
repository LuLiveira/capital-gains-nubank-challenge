package dev.lucas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class App {
    public static void main(String[] args) {
        StringBuilder operationsInput = new StringBuilder();
        String line;

        try (Scanner input = new Scanner(System.in)) { //Usando try with resources para encerrar o scanner
            while (!(line = input.nextLine()).isEmpty()) {
                operationsInput.append(line).append("\n"); //Aqui esta sendo considerado o payload usado no documento portanto a quebra de linha é para respeitar a entrada e o código sera pautado nesta quebra. Testes onde foi removido o \n o regex para realizar o split deixa de funcionar
            }
        }

        var operationsSplit = operationsInput.toString().trim().split("\\n(?=\\[)"); //Aqui eu pedi "ajuda" para o chat gpt no uso de regex.

        Gson gson = new Gson();
        Type marketOperationListConverter = new TypeToken<List<MarketOperation>>() {}.getType();

//        Map<String, List<MarketOperation>> operations = new LinkedHashMap<>();
        for (String operationList : operationsSplit) {
            List<MarketOperation> operations = gson.fromJson(operationList, marketOperationListConverter); //Operations
            List<OperationOutput> taxsList = operations.stream().map(MarketOperation::tax).toList(); //Tax from Operations
            System.out.println(gson.toJson(taxsList));

//            operations.put(UUID.randomUUID().toString(), gson.fromJson(operationList, marketOperationListConverter)); Não vai ser util mas foi um exercicio para entendimento
        }

    }
}
