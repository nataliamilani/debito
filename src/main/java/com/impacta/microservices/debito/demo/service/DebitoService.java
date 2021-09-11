package com.impacta.microservices.debito.demo.service;

import com.impacta.microservices.debito.demo.controller.response.SaldoResponse;
import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.repository.DebitoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebitoService {

    private DebitoRepository repository;

    public DebitoService(DebitoRepository repository) {
        this.repository = repository;
    }

    public Debito criarDebito(Debito debito) {
        return repository.save(debito);
    }

    public List<Debito> findByContaIdAndTipoContaCorrente(Integer contaId) {
        return repository.findByContaIdAndTipoConta(contaId, "contacorrente");
    }

    public List<Debito> findByContaIdAndTipoContaInvestimento(Integer contaId) {
        return repository.findByContaIdAndTipoConta(contaId, "investimento");
    }

    public SaldoResponse consultaSaldoContaIdContaCorrente(Integer contaId) {
        return  repository.findBySaldoCreditoPorTipoConta(contaId, "contacorrente");
    }

    public SaldoResponse consultaSaldoContaIdContaInvestimento(Integer contaId) {
        return  repository.findBySaldoCreditoPorTipoConta(contaId, "investimento");
    }

}
