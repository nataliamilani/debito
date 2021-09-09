package com.impacta.microservices.debito.demo.controller;

import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.service.DebitoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/debito")
public class DebitoController {

    private final DebitoService debitoService;

    public DebitoController(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Debito createDebito(@RequestBody Debito debitoRequest) {
        return debitoService.create(debitoRequest);
    }

    @GetMapping("/movimentos/contacorrente/{contaId}")
    public List<Debito> listContaCorrenteByContaId(@PathVariable Integer contaId) throws UnknownHostException {
        List<Debito> movimentos = debitoService.findByContaIdAndTipoContaCorrente(contaId);
        return movimentos;
    }

    @GetMapping("/movimentos/investimento/{contaId}")
    public List<Debito> listInvestimentoByContaId(@PathVariable Integer contaId) throws UnknownHostException {
        List<Debito> movimentos = debitoService.findByContaIdAndTipoContaInvestimento(contaId);
        return movimentos;
    }
}
