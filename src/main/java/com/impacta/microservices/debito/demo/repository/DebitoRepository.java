package com.impacta.microservices.debito.demo.repository;

import com.impacta.microservices.debito.demo.domain.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Integer> {
}
