package com.impacta.microservices.debito.demo.repository;

import com.impacta.microservices.debito.demo.domain.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Integer> {

    List<Debito> findByContaIdAndTipoConta(Integer contaId, String tipoConta);
}
