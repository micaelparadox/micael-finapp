package financeiro.pessoal.micael.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import financeiro.pessoal.micael.dto.DividaDTO;
import financeiro.pessoal.micael.exceptions.ResourceNotFoundException;
import financeiro.pessoal.micael.model.Divida;
import financeiro.pessoal.micael.repository.DividaRepository;

@Service
public class DividaService {

	private final DividaRepository dividaRepository;

	@Autowired
	public DividaService(DividaRepository dividaRepository) {
		this.dividaRepository = dividaRepository;
	}

	@Transactional
	public Divida create(DividaDTO dividaDTO) {
		Divida divida = new Divida();
		divida.setDataVencimento(dividaDTO.getDataVencimento());
		divida.setValorPrevisto(dividaDTO.getValorPrevisto());
		divida.setValorRealizado(dividaDTO.getValorRealizado());
		divida.setCodigoBarraRef(dividaDTO.getCodigoBarraRef());
		divida.setStatus(dividaDTO.getStatus());
		return dividaRepository.save(divida);
	}

	public List<Divida> findAll() {
		return dividaRepository.findAll();
	}

	public Divida findById(Long id) {
		return dividaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Divida", "id", id));
	}

	@Transactional
	public Divida update(Long id, DividaDTO dividaDTO) {
		Divida divida = findById(id);
		divida.setDataVencimento(dividaDTO.getDataVencimento());
		divida.setValorPrevisto(dividaDTO.getValorPrevisto());
		divida.setValorRealizado(dividaDTO.getValorRealizado());
		divida.setCodigoBarraRef(dividaDTO.getCodigoBarraRef());
		divida.setStatus(dividaDTO.getStatus());
		return dividaRepository.save(divida);
	}

	@Transactional
	public void delete(Long id) {
		Divida divida = findById(id);
		dividaRepository.delete(divida);
	}
}
