package co.com.pragma.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.pragma.usecase.obtenercontadorprestamos.ObtenerContadorPrestamosUseCase;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
	
	private final ObtenerContadorPrestamosUseCase obtenerContadorPrestamosUseCase;

	public Mono<ServerResponse> obtenerCantidadPrestamos(ServerRequest request) {
        return obtenerContadorPrestamosUseCase.obtenerCantidad()
                .flatMap(cantidad ->
                        ServerResponse.ok().bodyValue(cantidad))
                .onErrorResume(e ->
                        ServerResponse.status(500).bodyValue("Error interno" + e));
    }
}
