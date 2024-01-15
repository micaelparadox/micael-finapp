package financeiro.pessoal.micael.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financeiro.pessoal.micael.dto.DividaDTO;
import financeiro.pessoal.micael.model.Divida;
import financeiro.pessoal.micael.service.DividaService;

@RestController
@RequestMapping("/dividas")
@Validated
public class DividaController {

	private final DividaService dividaService;

	@Autowired
	public DividaController(DividaService dividaService) {
		this.dividaService = dividaService;
	}

	@PostMapping
	public ResponseEntity<DividaDTO> createDivida(@RequestBody DividaDTO dividaDTO) {
		Divida divida = dividaService.create(dividaDTO);
		return new ResponseEntity<>(convertToDto(divida), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<DividaDTO>> getAllDividas() {
		List<Divida> dividas = dividaService.findAll();
		List<DividaDTO> dividaDTOs = dividas.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.ok(dividaDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DividaDTO> getDividaById(@PathVariable Long id) {
		Divida divida = dividaService.findById(id);
		return ResponseEntity.ok(convertToDto(divida));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DividaDTO> updateDivida(@PathVariable Long id, @RequestBody DividaDTO dividaDTO) {
		Divida updatedDivida = dividaService.update(id, dividaDTO);
		return ResponseEntity.ok(convertToDto(updatedDivida));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDivida(@PathVariable Long id) {
		dividaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	private DividaDTO convertToDto(Divida divida) {
		DividaDTO dto = new DividaDTO();
		dto.setId(divida.getId());
		dto.setCodigoBarraRef(divida.getCodigoBarraRef());
		dto.setDataVencimento(divida.getDataVencimento());
		dto.setStatus(divida.getStatus());
		dto.setValorPrevisto(divida.getValorPrevisto());
		dto.setValorRealizado(divida.getValorRealizado());
		return dto;
	}
	
	

}
