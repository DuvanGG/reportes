package co.com.pragma.sqs.listener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoAprobadoEvent {
	
	 private Long idPrestamo;
	 private Double valor;

}
