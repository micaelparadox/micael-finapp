package financeiro.pessoal.micael.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import financeiro.pessoal.micael.dto.AjusteFinanceiroDTO;
import financeiro.pessoal.micael.service.FinanceiroService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/financeiro")
@Slf4j
public class FinanceiroController {

	private final FinanceiroService financeiroService;

	@Autowired
	public FinanceiroController(FinanceiroService financeiroService) {
		this.financeiroService = financeiroService;
	}

	@GetMapping("/saldo-atual")
	public ResponseEntity<BigDecimal> getSaldoAtual() {
		BigDecimal saldoAtual = financeiroService.calcularSaldoAtual();
		return ResponseEntity.ok(saldoAtual);
	}

	@GetMapping("/saldo-para-data")
	public ResponseEntity<BigDecimal> getSaldoParaData(
			@RequestParam("data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
		BigDecimal saldoParaData = financeiroService.calcularSaldoParaData(data);
		return ResponseEntity.ok(saldoParaData);
	}

	@GetMapping("/ajustes")
	public ResponseEntity<List<AjusteFinanceiroDTO>> getAllAjustesFinanceiros() {
		List<AjusteFinanceiroDTO> ajustesDTO = financeiroService
				.buscarTodosAjustesFinanceiros().stream().map(ajuste -> new AjusteFinanceiroDTO(ajuste.getDataAjuste(),
						ajuste.getValorAjuste(), ajuste.getTipoDeAjuste(), ajuste.getIdRelacionado()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(ajustesDTO);
	}

	@PostMapping("/ajustes")
	public ResponseEntity<AjusteFinanceiroDTO> createAjusteFinanceiro(@RequestBody AjusteFinanceiroDTO ajusteDTO) {
		if (ajusteDTO.getValorAjuste() == null || ajusteDTO.getValorAjuste().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Valor do ajuste é necessário e deve ser maior que 0");
		}
		financeiroService.criarAjusteFinanceiro(ajusteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ajusteDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptions(Exception exception) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (exception instanceof ResponseStatusException) {
			status = ((ResponseStatusException) exception).getStatus();
		}
		return ResponseEntity.status(status).body(exception.getMessage());
	}
}
