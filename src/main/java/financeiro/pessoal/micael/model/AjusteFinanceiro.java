package financeiro.pessoal.micael.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AjusteFinanceiro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dataAjuste;
	private BigDecimal valorAjuste;

	private Long idRelacionado; // ID da Divida ou Recebimento relacionado

	@Enumerated(EnumType.STRING)
	private TipoAjuste tipoDeAjuste;

	@ManyToOne
	@JoinColumn(name = "divida_id") // Nome da coluna que faz referência à Divida
	private Divida divida;

	@ManyToOne
	@JoinColumn(name = "recebimento_id") // Nome da coluna que faz referência ao Recebimento
	private Recebimento recebimento;

	// Getters e setters para todos os campos
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipoDeAjuste(TipoAjuste tipoDeAjuste) {
		this.tipoDeAjuste = tipoDeAjuste;
	}

	public TipoAjuste getTipoDeAjuste() {
		return tipoDeAjuste;
	}

	public LocalDate getDataAjuste() {
		return dataAjuste;
	}

	public void setDataAjuste(LocalDate dataAjuste) {
		this.dataAjuste = dataAjuste;
	}

	public BigDecimal getValorAjuste() {
		return valorAjuste;
	}

	public void setValorAjuste(BigDecimal valorAjuste) {
		this.valorAjuste = valorAjuste;
	}

	public Long getIdRelacionado() {
		return idRelacionado;
	}

	public void setIdRelacionado(Long idRelacionado) {
		this.idRelacionado = idRelacionado;
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

}
