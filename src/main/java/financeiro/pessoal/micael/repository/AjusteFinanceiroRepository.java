package financeiro.pessoal.micael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import financeiro.pessoal.micael.model.AjusteFinanceiro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AjusteFinanceiroRepository extends JpaRepository<AjusteFinanceiro, Long> {

    // Buscar ajustes por um intervalo de datas
    List<AjusteFinanceiro> findByDataAjusteBetween(LocalDate startDate, LocalDate endDate);

    // Buscar ajustes por valor
    List<AjusteFinanceiro> findByValorAjusteBetween(BigDecimal minValue, BigDecimal maxValue);

    // Buscar ajustes até uma determinada data
    List<AjusteFinanceiro> findByDataAjusteLessThanEqual(LocalDate date);
    
    //criar metodo para buscar tudo
    List<AjusteFinanceiro> findAll();
    

    // Outros métodos conforme necessário...
}
