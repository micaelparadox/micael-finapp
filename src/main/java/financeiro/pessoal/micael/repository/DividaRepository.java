package financeiro.pessoal.micael.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import financeiro.pessoal.micael.model.Divida;
import financeiro.pessoal.micael.model.StatusDivida;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Long> {

	List<Divida> findByStatus(StatusDivida status);

	List<Divida> findByDataVencimentoLessThanEqual(LocalDate date);

	List<Divida> findByDataVencimentoBetween(LocalDate startDate, LocalDate endDate);

	List<Divida> findByValorPrevistoBetween(BigDecimal minValue, BigDecimal maxValue);

	List<Divida> findByValorRealizadoAndDataVencimentoLessThanEqual(BigDecimal valorRealizado, LocalDate date);
}
