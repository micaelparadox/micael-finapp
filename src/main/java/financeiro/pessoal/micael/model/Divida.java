package financeiro.pessoal.micael.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Divida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dataVencimento;
	private BigDecimal valorPrevisto;
	private BigDecimal valorRealizado;
	private String codigoBarraRef;

	@Enumerated(EnumType.STRING)
	private StatusDivida status;

	@OneToMany(mappedBy = "divida")
	private List<AjusteFinanceiro> ajustesFinanceiros;
}
