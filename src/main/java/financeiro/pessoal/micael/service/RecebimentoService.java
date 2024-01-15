package financeiro.pessoal.micael.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import financeiro.pessoal.micael.dto.RecebimentoDTO;
import financeiro.pessoal.micael.exceptions.ResourceNotFoundException;
import financeiro.pessoal.micael.model.Recebimento;
import financeiro.pessoal.micael.repository.RecebimentoRepository;

@Service
public class RecebimentoService {

    private final RecebimentoRepository recebimentoRepository;

    @Autowired
    public RecebimentoService(RecebimentoRepository recebimentoRepository) {
        this.recebimentoRepository = recebimentoRepository;
    }

    @Transactional
    public RecebimentoDTO create(RecebimentoDTO recebimentoDTO) {
        Recebimento recebimento = convertToEntity(recebimentoDTO);
        recebimento = recebimentoRepository.save(recebimento);
        return convertToDTO(recebimento);
    }

    public List<RecebimentoDTO> findAll() {
        return recebimentoRepository.findAll().stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    public RecebimentoDTO findById(Long id) {
        Recebimento recebimento = recebimentoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recebimento", "id", id));
        return convertToDTO(recebimento);
    }

    @Transactional
    public RecebimentoDTO update(Long id, RecebimentoDTO recebimentoDTO) {
        Recebimento recebimento = recebimentoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recebimento", "id", id));
        recebimento.setDataPagamento(recebimentoDTO.getDataPagamento());
        recebimento.setValorRecebido(recebimentoDTO.getValorRecebido());
        recebimento = recebimentoRepository.save(recebimento);
        return convertToDTO(recebimento);
    }

    @Transactional
    public void delete(Long id) {
        Recebimento recebimento = recebimentoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recebimento", "id", id));
        recebimentoRepository.delete(recebimento);
    }

    private RecebimentoDTO convertToDTO(Recebimento recebimento) {
        RecebimentoDTO dto = new RecebimentoDTO();
        dto.setId(recebimento.getId());
        dto.setDataPagamento(recebimento.getDataPagamento());
        dto.setValorRecebido(recebimento.getValorRecebido());
        return dto;
    }

    private Recebimento convertToEntity(RecebimentoDTO dto) {
        Recebimento recebimento = new Recebimento();
        recebimento.setDataPagamento(dto.getDataPagamento());
        recebimento.setValorRecebido(dto.getValorRecebido());
        return recebimento;
    }
}
