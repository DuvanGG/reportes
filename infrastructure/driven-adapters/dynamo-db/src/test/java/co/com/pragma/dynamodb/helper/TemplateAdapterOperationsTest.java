package co.com.pragma.dynamodb.helper;

import co.com.pragma.dynamodb.DynamoDBTemplateAdapter;
import co.com.pragma.model.contadorprestamos.ContadorPrestamos;
import co.com.pragma.dynamodb.ContadorPrestamosEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class TemplateAdapterOperationsTest {

    @Mock
    private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private DynamoDbAsyncTable<ContadorPrestamosEntity> customerTable;

    private ContadorPrestamosEntity contadorPrestamosEntity;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        when(dynamoDbEnhancedAsyncClient.table("ContadorPrestamos", TableSchema.fromBean(ContadorPrestamosEntity.class)))
//                .thenReturn(customerTable);
//
//        contadorPrestamosEntity = new ContadorPrestamosEntity();
//        contadorPrestamosEntity.setId("id");
//        contadorPrestamosEntity.setAtr1("atr1");
//    }

//    @Test
//    void ContadorPrestamosEntityPropertiesMustNotBeNull() {
//        ContadorPrestamosEntity ContadorPrestamosEntityUnderTest = new ContadorPrestamosEntity("id", "atr1");
//
//        assertNotNull(ContadorPrestamosEntityUnderTest.getId());
//        assertNotNull(ContadorPrestamosEntityUnderTest.getAtr1());
//    }
//
//    @Test
//    void testSave() {
//        when(customerTable.putItem(any(ContadorPrestamosEntity.class))).thenReturn(CompletableFuture.completedFuture(null));
//        when(mapper.map(any(ContadorPrestamos.class), eq(ContadorPrestamosEntity.class))).thenReturn(new ContadorPrestamosEntity());
//
//        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
//                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);
//
//        StepVerifier.create(dynamoDBTemplateAdapter.save(new ContadorPrestamos()))
//                .verifyComplete();
//    }

//    @Test
//    void testGetById() {
//        String id = "id";
//
//        when(customerTable.getItem(
//                Key.builder().partitionValue(AttributeValue.builder().s(id).build()).build()))
//                .thenReturn(CompletableFuture.completedFuture(contadorPrestamosEntity));
//        when(mapper.map(contadorPrestamosEntity, Object.class)).thenReturn("value");
//
//        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
//                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);
//
//        StepVerifier.create(dynamoDBTemplateAdapter.getById("id"))
//                .expectNext("value")
//                .verifyComplete();
//    }

//    @Test
//    void testDelete() {
//        when(mapper.map(contadorPrestamosEntity, ContadorPrestamosEntity.class)).thenReturn(contadorPrestamosEntity);
//        when(mapper.map(contadorPrestamosEntity, Object.class)).thenReturn("value");
//
//        when(customerTable.deleteItem(contadorPrestamosEntity))
//                .thenReturn(CompletableFuture.completedFuture(contadorPrestamosEntity));
//
//        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
//                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);
//
//        StepVerifier.create(dynamoDBTemplateAdapter.delete(contadorPrestamosEntity))
//                .expectNext("value")
//                .verifyComplete();
//    }
}