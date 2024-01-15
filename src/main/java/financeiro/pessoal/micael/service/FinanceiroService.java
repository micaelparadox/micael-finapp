package financeiro.pessoal.micael.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financeiro.pessoal.micael.dto.AjusteFinanceiroDTO;
import financeiro.pessoal.micael.exceptions.ResourceNotFoundException;
import financeiro.pessoal.micael.model.AjusteFinanceiro;
import financeiro.pessoal.micael.model.Divida;
import financeiro.pessoal.micael.model.Recebimento;
import financeiro.pessoal.micael.model.StatusDivida;
import financeiro.pessoal.micael.model.TipoAjuste; // Adicionado para usar a enumeração TipoAjuste
import financeiro.pessoal.micael.repository.AjusteFinanceiroRepository;
import financeiro.pessoal.micael.repository.DividaRepository;
import financeiro.pessoal.micael.repository.RecebimentoRepository;

@Service
@Transactional
public class FinanceiroService {

	private final RecebimentoRepository recebimentoRepository;
	private final DividaRepository dividaRepository;
	private final AjusteFinanceiroRepository ajusteFinanceiroRepository;

	@Autowired
	public FinanceiroService(RecebimentoRepository recebimentoRepository, DividaRepository dividaRepository,
			AjusteFinanceiroRepository ajusteFinanceiroRepository) {
		this.recebimentoRepository = recebimentoRepository;
		this.dividaRepository = dividaRepository;
		this.ajusteFinanceiroRepository = ajusteFinanceiroRepository;
	}

	public BigDecimal calcularSaldoAtual() {
		LocalDate hoje = LocalDate.now();
		return calcularSaldoParaData(hoje);
	}

	public BigDecimal calcularSaldoParaData(LocalDate data) {
		BigDecimal totalRecebido = somarRecebimentosAteData(data);
		BigDecimal totalDividas = somarDividasAteData(data);
		return totalRecebido.subtract(totalDividas);
	}

	public void criarAjusteFinanceiro(AjusteFinanceiroDTO ajusteDTO) {
		AjusteFinanceiro ajuste = new AjusteFinanceiro();
		ajuste.setDataAjuste(ajusteDTO.getDataAjuste());
		ajuste.setValorAjuste(ajusteDTO.getValorAjuste());

		// Verifique o tipo de ajuste e defina o campo TipoAjuste adequadamente
		if ("RECEBIMENTO".equals(ajusteDTO.getTipoDeAjuste())) {
			ajuste.setTipoDeAjuste(TipoAjuste.PAGO); // Defina como PAGO para RECEBIMENTO
			Recebimento recebimento = recebimentoRepository.findById(ajusteDTO.getIdRelacionado())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Recebimento não encontrado com id: " + ajusteDTO.getIdRelacionado()));
			ajuste.setRecebimento(recebimento);
		} else if ("DIVIDA".equals(ajusteDTO.getTipoDeAjuste())) {
			ajuste.setTipoDeAjuste(TipoAjuste.PENDENTE); // Defina como PENDENTE para DIVIDA
			Divida divida = dividaRepository.findById(ajusteDTO.getIdRelacionado())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Divida não encontrada com id: " + ajusteDTO.getIdRelacionado()));
			ajuste.setDivida(divida);
		}

		ajusteFinanceiroRepository.save(ajuste);
	}

	private BigDecimal somarRecebimentosAteData(LocalDate data) {
		return recebimentoRepository.findByDataPagamentoLessThanEqual(data).stream().map(Recebimento::getValorRecebido)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal somarDividasAteData(LocalDate data) {
		return dividaRepository.findByDataVencimentoLessThanEqual(data).stream()
				.filter(divida -> divida.getStatus().equals(StatusDivida.PENDENTE)
						|| divida.getStatus().equals(StatusDivida.PAGO))
				.map(Divida::getValorPrevisto).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public List<AjusteFinanceiroDTO> buscarTodosAjustesFinanceiros() {
	    return ajusteFinanceiroRepository.findAll().stream()
	            .map(ajuste -> new AjusteFinanceiroDTO(
	                    ajuste.getDataAjuste(),
	                    ajuste.getValorAjuste(),
	                    ajuste.getTipoDeAjuste().name(),
	                    ajuste.getDivida() != null ? ajuste.getDivida().getId() : ajuste.getRecebimento().getId()))
	            .collect(Collectors.toList());
	}

}
