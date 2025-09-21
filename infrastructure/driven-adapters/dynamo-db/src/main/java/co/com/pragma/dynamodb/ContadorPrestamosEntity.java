package co.com.pragma.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ContadorPrestamosEntity {

    private String contador; 
    private long contadorPrestamos;
    private double acumulado;

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

	public double getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(double acumulado) {
		this.acumulado = acumulado;
	}
    
    
}
