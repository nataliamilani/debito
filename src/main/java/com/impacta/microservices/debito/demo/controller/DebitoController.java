package com.impacta.microservices.debito.demo.controller;

import com.impacta.microservices.debito.demo.controller.response.SaldoResponse;
import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.service.DebitoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@Tag(name="Debito endpoint")
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
        return debitoService.criarDebito(debitoRequest);
    }

    @GetMapping("/extrato/contacorrente/{contaId}")
    public List<Debito> consultaExtratoContaCorrenteByContaId(@PathVariable Integer contaId) throws UnknownHostException {
        List<Debito> movimentos = debitoService.findByContaIdAndTipoContaCorrente(contaId);
        return movimentos;
    }

    @GetMapping("/extrato/investimento/{contaId}")
    public List<Debito> consultaExtratoContaInvestimentoByContaId(@PathVariable Integer contaId) throws UnknownHostException {
        List<Debito> movimentos = debitoService.findByContaIdAndTipoContaInvestimento(contaId);
        return movimentos;
    }

    @GetMapping("/saldo/contacorrente/{contaId}")
    public SaldoResponse consultaSaldoConsolidadoContaCorrente(@PathVariable("contaId") Integer contaId) {
        SaldoResponse saldoResponse = new SaldoResponse(debitoService.consultaSaldoContaIdContaCorrente(contaId));
        return saldoResponse;
    }

    @GetMapping("/saldo/investimento/{contaId}")
    public SaldoResponse consultaSaldoConsolidadoContaInvestimento(@PathVariable("contaId") Integer contaId) {
        SaldoResponse saldoResponse = new SaldoResponse(debitoService.consultaSaldoContaIdContaInvestimento(contaId));
        return saldoResponse;
    }

}
