package co.com.pragma.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ContadorPrestamosEntity {

    private String contador; // clave primaria, ej: "contador"
    private long contadorPrestamos;

    @DynamoDbPartitionKey
    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador =contador;
    }

    public long getContadorPrestamos() {
        return contadorPrestamos;
    }

    public void setContadorPrestamos(long contadorPrestamos) {
        this.contadorPrestamos = contadorPrestamos;
    }
}
