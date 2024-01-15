package financeiro.pessoal.micael.controller;

import java.util.List;

import javax.validation.Valid;

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

import financeiro.pessoal.micael.dto.RecebimentoDTO;
import financeiro.pessoal.micael.service.RecebimentoService;

@RestController
@RequestMapping("/recebimentos")
@Validated
public class RecebimentoController {

    private final RecebimentoService recebimentoService;

    @Autowired
    public RecebimentoController(RecebimentoService recebimentoService) {
        this.recebimentoService = recebimentoService;
    }

    @PostMapping
    public ResponseEntity<RecebimentoDTO> createRecebimento(@Valid @RequestBody RecebimentoDTO recebimentoDTO) {
        RecebimentoDTO novoRecebimento = recebimentoService.create(recebimentoDTO);
        return new ResponseEntity<>(novoRecebimento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecebimentoDTO>> getAllRecebimentos() {
        List<RecebimentoDTO> recebimentoDTOs = recebimentoService.findAll();
        return ResponseEntity.ok(recebimentoDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecebimentoDTO> getRecebimentoById(@PathVariable Long id) {
        RecebimentoDTO recebimentoDTO = recebimentoService.findById(id);
        return ResponseEntity.ok(recebimentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecebimentoDTO> updateRecebimento(@PathVariable Long id, @Valid @RequestBody RecebimentoDTO recebimentoDTO) {
        RecebimentoDTO atualizadoRecebimento = recebimentoService.update(id, recebimentoDTO);
        return ResponseEntity.ok(atualizadoRecebimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecebimento(@PathVariable Long id) {
        recebimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
