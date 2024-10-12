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

        /**
         * Esse trecho de código recebe um input JSON
         * O padrão esperado é:
         * [{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
         * {"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
         * [{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
         * {"operation":"sell", "unit-cost":10.00, "quantity": 5000}]
         *
         * Realizei alguns experimentos para receber valores em JSON diferentes e deixar o programa mais dinamico, todos exigiam mais do código
         * então, assumi como premissa o uso especifico de um JSON que seguisse o padrão assim. Busquei auxilio de IA após estressar as possibilidades
         * mesmo com IA o resultado não me agradou e seria um esforço maior, aumentando a complexidade do código sem um ganho real
         *
         */
        try (Scanner input = new Scanner(System.in)) { //Usando try with resources para encerrar o scanner
            while (!(line = input.nextLine()).isEmpty()) {
                operationsInput.append(line).append("\n"); //Aqui esta sendo considerado o payload usado no documento portanto a quebra de linha é para respeitar a entrada e o código sera pautado nesta quebra. Testes onde foi removido o \n o regex para realizar o split deixa de funcionar
            }
        }

        /**
         * Dado que o INPUT mapeado pode ser N listas no padrão JSON se faz necessário um split entre essas listas
         */
        var operationsSplit = operationsInput.toString().trim().split("\\n(?=\\[)"); //Aqui eu pedi "ajuda" para o chat gpt no uso de regex.

        /**
         * Boilerplat para conversão de JSON to JAVA OBJECT
         */
        Gson gson = new Gson();
        Type marketOperationListConverter = new TypeToken<List<MarketOperation>>() {}.getType();

        /**
         * Percorre a lista de operações para aplicar a taxa
         */
//        Map<String, List<MarketOperation>> operations = new LinkedHashMap<>();
        for (String operationList : operationsSplit) {
            List<MarketOperation> operations = gson.fromJson(operationList, marketOperationListConverter); //Operations
            List<OperationOutput> taxsList = operations.stream().map(MarketOperation::tax).toList(); //Tax from Operations
            System.out.println(gson.toJson(taxsList));

//            operations.put(UUID.randomUUID().toString(), gson.fromJson(operationList, marketOperationListConverter)); Não vai ser util mas foi um exercicio para entendimento
        }

    }
}
