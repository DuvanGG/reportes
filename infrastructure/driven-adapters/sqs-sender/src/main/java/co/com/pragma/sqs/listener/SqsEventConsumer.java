package co.com.pragma.sqs.listener;

import org.springframework.stereotype.Component;

import co.com.pragma.usecase.obtenercontadorprestamos.ObtenerContadorPrestamosUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class SqsEventConsumer {

	private final ObtenerContadorPrestamosUseCase useCase;
	
	@SqsListener("solicitudes-aprobadas-queue")
	public void consumeMensaje(String message) {
	    log.info("Evento SQS recibido: {}", message);

	    useCase.obtenerCantidad()
	        .flatMap(cantidadActual -> useCase.actualizarCantidad(cantidadActual + 1))
	        .doOnError(err -> log.error("Error actualizando contador", err))
	        .subscribe();
	}
	


}
