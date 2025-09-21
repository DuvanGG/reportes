package co.com.pragma.usecase.obtenercontadorprestamos;

import co.com.pragma.model.contadorprestamos.gateways.ContadorPrestamosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class ObtenerContadorPrestamosUseCaseTest {

    private ContadorPrestamosRepository repository;
    private ObtenerContadorPrestamosUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(ContadorPrestamosRepository.class);
        useCase = new ObtenerContadorPrestamosUseCase(repository);
    }

    @Test
    void obtenerCantidad_deberiaRetornarCantidadDelRepositorio() {
        // Arrange
        when(repository.obtenerCantidad()).thenReturn(Mono.just(5L));

        // Act & Assert
        StepVerifier.create(useCase.obtenerCantidad())
                .expectNext(5L)
                .verifyComplete();

        verify(repository).obtenerCantidad();
    }

    @Test
    void actualizarCantidad_deberiaLlamarRepositorioYCompletar() {
        // Arrange
        long nuevaCantidad = 10L;
        when(repository.actualizarCantidad(nuevaCantidad)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.actualizarCantidad(nuevaCantidad))
                .verifyComplete();

        verify(repository).actualizarCantidad(nuevaCantidad);
    }

    @Test
    void actualizarCantidadYSumarValor_deberiaSumarYActualizarCorrectamente() {
        // Arrange
        long nuevaCantidad = 15L;
        double valor = 20.0;
        double totalActual = 30.0;
        double totalEsperado = totalActual + valor;

        when(repository.obtenerTotalAcumulado()).thenReturn(Mono.just(totalActual));
        when(repository.guardarTotalAcumulado(totalEsperado)).thenReturn(Mono.empty());
        when(repository.actualizarCantidad(nuevaCantidad)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.actualizarCantidadYSumarValor(nuevaCantidad, valor))
                .verifyComplete();

        verify(repository).obtenerTotalAcumulado();
        verify(repository).guardarTotalAcumulado(totalEsperado);
        verify(repository).actualizarCantidad(nuevaCantidad);
    }

    @Test
    void actualizarCantidadYSumarValor_deberiaUsarCeroSiNoHayTotalPrevio() {
        // Arrange
        long nuevaCantidad = 5L;
        double valor = 10.0;
        double totalEsperado = 0.0 + valor;

        when(repository.obtenerTotalAcumulado()).thenReturn(Mono.empty());
        when(repository.guardarTotalAcumulado(totalEsperado)).thenReturn(Mono.empty());
        when(repository.actualizarCantidad(nuevaCantidad)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.actualizarCantidadYSumarValor(nuevaCantidad, valor))
                .verifyComplete();

        verify(repository).obtenerTotalAcumulado();
        verify(repository).guardarTotalAcumulado(totalEsperado);
        verify(repository).actualizarCantidad(nuevaCantidad);
    }

//    @Test
//    void actualizarCantidadYSumarValor_deberiaPropagarErrorSiFallaGuardarTotal() {
//        // Arrange
//        long nuevaCantidad = 5L;
//        double valor = 10.0;
//        double totalActual = 40.0;
//        double totalEsperado = totalActual + valor;
//
//        when(repository.obtenerTotalAcumulado()).thenReturn(Mono.just(totalActual));
//        when(repository.guardarTotalAcumulado(totalEsperado)).thenReturn(Mono.error(new RuntimeException("Error guardando")));
//        // actualizarCantidad no debería llamarse
//
//        // Act & Assert
//        StepVerifier.create(useCase.actualizarCantidadYSumarValor(nuevaCantidad, valor))
//                .expectErrorMessage("Error guardando")
//                .verify();
//
//        verify(repository).obtenerTotalAcumulado();
//        verify(repository).guardarTotalAcumulado(totalEsperado);
//        verify(repository, never()).actualizarCantidad(anyLong());
//    }

    @Test
    void actualizarCantidadYSumarValor_deberiaPropagarErrorSiFallaActualizarCantidad() {
        // Arrange
        long nuevaCantidad = 5L;
        double valor = 10.0;
        double totalActual = 40.0;
        double totalEsperado = totalActual + valor;

        when(repository.obtenerTotalAcumulado()).thenReturn(Mono.just(totalActual));
        when(repository.guardarTotalAcumulado(totalEsperado)).thenReturn(Mono.empty());
        when(repository.actualizarCantidad(nuevaCantidad)).thenReturn(Mono.error(new RuntimeException("Error actualizando")));

        // Act & Assert
        StepVerifier.create(useCase.actualizarCantidadYSumarValor(nuevaCantidad, valor))
                .expectErrorMessage("Error actualizando")
                .verify();

        verify(repository).obtenerTotalAcumulado();
        verify(repository).guardarTotalAcumulado(totalEsperado);
        verify(repository).actualizarCantidad(nuevaCantidad);
    }
}