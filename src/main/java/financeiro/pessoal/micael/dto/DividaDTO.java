package financeiro.pessoal.micael.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import financeiro.pessoal.micael.model.StatusDivida;
import lombok.Data;

@Data
public class DividaDTO {

	private Long id;

	@NotNull(message = "A data de vencimento é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor previsto deve ser maior que zero.")
	private BigDecimal valorPrevisto;

	@DecimalMin(value = "0.0", message = "O valor realizado deve ser maior ou igual a zero.")
	private BigDecimal valorRealizado;

	@NotBlank(message = "O código de barras não pode estar vazio.")
	private String codigoBarraRef;

	@NotNull(message = "O status é obrigatório.")
	private StatusDivida status;
}
