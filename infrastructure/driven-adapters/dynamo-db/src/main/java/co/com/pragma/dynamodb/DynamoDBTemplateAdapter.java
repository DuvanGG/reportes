package co.com.pragma.dynamodb;

import co.com.pragma.dynamodb.helper.TemplateAdapterOperations;
import co.com.pragma.model.contadorprestamos.ContadorPrestamos;
import co.com.pragma.model.contadorprestamos.gateways.ContadorPrestamosRepository;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@Repository
public class DynamoDBTemplateAdapter
		extends TemplateAdapterOperations<ContadorPrestamos, String, ContadorPrestamosEntity>
		implements ContadorPrestamosRepository {

	private static final String TABLE_NAME = "ContadorPrestamos";

	public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper) {
		/**
		 * Could be use mapper.mapBuilder if your domain model implement builder pattern
		 * super(repository, mapper, d ->
		 * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using
		 * mapper.map with the class of the object model
		 */
		super(connectionFactory, mapper, d -> mapper.map(d, ContadorPrestamos.class), TABLE_NAME);
	}

	public Mono<List<ContadorPrestamos>> getEntityBySomeKeys(String partitionKey, String sortKey) {
		QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
		return query(queryExpression);
	}

	public Mono<List<ContadorPrestamos>> getEntityBySomeKeysByIndex(String partitionKey, String sortKey) {
		QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
		return queryByIndex(queryExpression);
	}

	private QueryEnhancedRequest generateQueryExpression(String partitionKey, String sortKey) {
		return QueryEnhancedRequest.builder()
				.queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build()))
				.queryConditional(QueryConditional.sortGreaterThanOrEqualTo(Key.builder().sortValue(sortKey).build()))
				.build();
	}

	@Override
	public Mono<Long> obtenerCantidad() {
		return this.getById("contador").map(ContadorPrestamos::getContadorPrestamos).switchIfEmpty(Mono.just(0L));
	}

	@Override
	public Mono<Void> actualizarCantidad(long nuevaCantidad) {
	    ContadorPrestamos contador = ContadorPrestamos.builder()
	        .contador("contador")
	        .contadorPrestamos(nuevaCantidad)
	        .build();

	    return this.save(contador).then();
	}
}
