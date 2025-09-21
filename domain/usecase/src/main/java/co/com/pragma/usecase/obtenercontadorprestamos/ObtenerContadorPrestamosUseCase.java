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

	public Mono<Void> actualizarCantidadYSumarValor(long nuevaCantidad, Double valor) {
	    System.out.println("Iniciando actualizarCantidadYSumarValor");
	    return contadorPrestamosRepository.obtenerTotalAcumulado()
	        .doOnSubscribe(s -> System.out.println("Suscrito a obtenerTotalAcumulado"))
	        .switchIfEmpty(Mono.defer(() -> {
	            System.out.println("No hay total acumulado previo, usando cero");
	            return Mono.just(0.0);
	        }))
	        .doOnNext(total -> System.out.println("Total acumulado actual: " + total))
	        .flatMap(totalActual -> {
	            double nuevoTotal = totalActual + valor;
	            System.out.println("Nuevo total acumulado calculado: " + nuevoTotal);
	            return contadorPrestamosRepository.guardarTotalAcumulado(nuevoTotal)
	                .doOnSuccess(v -> System.out.println("Total acumulado guardado correctamente"))
	                .doOnError(e -> System.err.println("Error guardando total acumulado: " + e.getMessage()));
	        })
	        .doOnError(e -> System.err.println("Error en flujo actualizarCantidadYSumarValor: " + e.getMessage()))
	        .then(actualizarCantidad(nuevaCantidad)
	            .doOnSuccess(v -> System.out.println("Contador actualizado correctamente"))
	            .doOnError(e -> System.err.println("Error actualizando contador: " + e.getMessage())));
	}

}
