package financeiro.pessoal.micael.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RecebimentoDTO {

	private Long id;

	@NotNull(message = "A data de pagamento é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor recebido deve ser maior que zero.")
	private BigDecimal valorRecebido;
}
