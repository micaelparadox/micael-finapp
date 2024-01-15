package financeiro.pessoal.micael.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import financeiro.pessoal.micael.model.Recebimento;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {

	Optional<Recebimento> findByDataPagamento(LocalDate date);

    List<Recebimento> findByDataPagamentoLessThanEqual(LocalDate date);

    List<Recebimento> findByDataPagamentoBetween(LocalDate startDate, LocalDate endDate);

    List<Recebimento> findByValorRecebidoBetween(BigDecimal minValue, BigDecimal maxValue);
}
