package com.impacta.microservices.debito.demo.controllers;

import com.impacta.microservices.debito.demo.controller.response.SaldoResponse;
import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.service.DebitoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
public class DebitoControllerTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private DebitoService debitorservice;

    @Test
    public void aoCriarDebitoRetornarDebitoCriado(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito = new Debito(contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.criarDebito(debito)).thenReturn(debito);

        final ResponseEntity<Debito> response = template
                .postForEntity("/debito", debito, Debito.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarExtratoDaConta(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito1 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.findByContaIdAndTipoContaCorrente(contaId)).thenReturn(List.of(debito1, debito2));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/extrato/contacorrente/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoInvestimentoRetornarExtratoDaConta(){
        final Integer contaId = 2;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Debito debito1 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.findByContaIdAndTipoContaInvestimento(contaId)).thenReturn(List.of(debito1, debito2));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/extrato/investimento/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorUmaContaIdInexistenteRetornarExtratoVazio(){
        final Integer contaId = 2;

        when(debitorservice.findByContaIdAndTipoContaInvestimento(contaId)).thenReturn(List.of());

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/extrato/investimento/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(result.isEmpty());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoInvestimentoRetornarSaldoTotalDaConta(){
        final Integer contaId = 2;
        final double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Double saldoConta =  -40.0;
        final Debito debito1 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        debitorservice.criarDebito(debito1);
        debitorservice.criarDebito(debito2);

        when(debitorservice.consultaSaldoContaIdContaInvestimento(contaId)).thenReturn(saldoConta);

        final ResponseEntity<SaldoResponse> response = template
                .getForEntity("/debito/saldo/investimento/" + contaId, SaldoResponse.class);
        final SaldoResponse result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saldoConta, result.getSaldoDebito());

    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarSaldoTotalDaConta(){
        final Integer contaId = 2;
        final double valorDebito = -30.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Double saldoConta = -40.0;
        final Debito debito1 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        debitorservice.criarDebito(debito1);
        debitorservice.criarDebito(debito2);

        when(debitorservice.consultaSaldoContaIdContaCorrente(contaId)).thenReturn(saldoConta);

        final ResponseEntity<SaldoResponse> response = template
                .getForEntity("/debito/saldo/contacorrente/" + contaId, SaldoResponse.class);
        final SaldoResponse result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saldoConta, result.getSaldoDebito());
    }
}
