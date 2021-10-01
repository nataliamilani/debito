package com.impacta.microservices.debito.demo.repository;

import com.impacta.microservices.debito.demo.domain.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Integer> {

    List<Debito> findByContaIdAndTipoConta(Integer contaId, String tipoConta);

    List<Debito> findByTipoConta(@Param("tipoConta") String tipoConta);

    @Query(value = "select * from debito", nativeQuery = true )
    List<Debito> listarContas();

    @Query(value= "select SUM(valor_debito) from debito where conta_id = ?1", nativeQuery = true)
    double findByContaIdSaldoDebito(Integer contaId);

    @Query(value = "select SUM(valor_debito) from debito where conta_id = ?1 and tipo_conta = ?2", nativeQuery = true)
    double findBySaldoDebitoPorTipoConta(Integer contaId, String tipoConta);
}
