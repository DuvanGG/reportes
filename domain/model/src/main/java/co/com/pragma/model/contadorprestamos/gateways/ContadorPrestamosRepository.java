package co.com.pragma.model.contadorprestamos.gateways;

import reactor.core.publisher.Mono;

public interface ContadorPrestamosRepository {
	
	Mono<Long> obtenerCantidad();
	
    Mono<Void> actualizarCantidad(long nuevaCantidad);
    
}
