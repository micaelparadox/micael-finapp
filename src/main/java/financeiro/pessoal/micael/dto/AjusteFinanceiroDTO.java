package financeiro.pessoal.micael.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // Adicionando construtor com todos os argumentos
public class AjusteFinanceiroDTO {

    @NotNull(message = "A data do ajuste é obrigatória.")
    @PastOrPresent(message = "A data do ajuste deve ser hoje ou uma data passada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAjuste;

    @NotNull(message = "O valor do ajuste é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor do ajuste deve ser maior que zero.")
    private BigDecimal valorAjuste;

    @NotNull(message = "O tipo de ajuste é obrigatório.")
    private String tipoDeAjuste; // Pode ser "DIVIDA" ou "RECEBIMENTO"

    @NotNull(message = "O ID relacionado é obrigatório.")
    private Long idRelacionado; // ID da Dívida ou Recebimento relacionado
}
