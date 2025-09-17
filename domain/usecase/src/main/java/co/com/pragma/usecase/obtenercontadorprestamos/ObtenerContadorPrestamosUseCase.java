package co.com.pragma.usecase.obtenercontadorprestamos;

import co.com.pragma.model.contadorprestamos.gateways.ContadorPrestamosRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObtenerContadorPrestamosUseCase {

	private final ContadorPrestamosRepository contadorPrestamosRepository;

	public Mono<Long> obtenerCantidad() {
		return contadorPrestamosRepository.obtenerCantidad();
	}

	public Mono<Void> actualizarCantidad(long nuevaCantidad) {
		return contadorPrestamosRepository.actualizarCantidad(nuevaCantidad);
	}

}
