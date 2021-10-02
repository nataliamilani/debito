package com.impacta.microservices.debito.demo.controllers;

import com.impacta.microservices.debito.demo.controller.response.SaldoDebitoResponse;
import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.domain.TipoConta;
import com.impacta.microservices.debito.demo.service.DebitoService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DebitoControllerTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private DebitoService debitorservice;

    @Test
    public void aoCriarDebitoRetornarDebitoCriado(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Debito debito = new Debito(id_transacao, contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.criarDebito(debito)).thenReturn(debito);

        final ResponseEntity<Debito> response = template
                .postForEntity("/debito", debito, Debito.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void consultarTransacoesPorTipoTest(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Debito debito = new Debito(id_transacao, contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.consultaTransacoesTipoConta(tipoConta)).thenReturn(List.of(debito));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/contas" + "?tipoConta=" + tipoConta, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(1, result.size());
    }

    @Test
    public void listarTransacoesRealizadas(){
        final Debito debito1 = new Debito(1, 1, 20.0, 1, TipoConta.contacorrente.toString());
        final Debito debito2 = new Debito(2, 2, 80.0, 1, TipoConta.investimento.toString());

        when(debitorservice.listarContas()).thenReturn(List.of(debito1, debito2));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/listar/contas", Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarExtratoDaConta(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.consultaContaIdContaCorrente(contaId)).thenReturn(List.of(debito1, debito2));

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
        final String tipoConta = TipoConta.investimento.toString();
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.consultaContaIdInvestimento(contaId)).thenReturn(List.of(debito1, debito2));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/extrato/investimento/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoInvestimentoRetornarSaldoTotalDaConta(){
        final Integer contaId = 2;
        final double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.investimento.toString();
        final double saldoConta =  40.0;
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);
        debitorservice.criarDebito(debito1);
        debitorservice.criarDebito(debito2);

        when(debitorservice.consultaSaldoContaIdContaInvestimento(contaId)).thenReturn(saldoConta);

        final ResponseEntity<SaldoDebitoResponse> response = template
                .getForEntity("/debito/saldo/investimento/" + contaId, SaldoDebitoResponse.class);
        final SaldoDebitoResponse result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(40.0, result.getSaldoDebito());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarSaldoTotalDaConta(){
        final Integer contaId = 2;
        final double valorDebito = 30.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final double saldoConta = 40.0;
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);
        debitorservice.criarDebito(debito1);
        debitorservice.criarDebito(debito2);

        when(debitorservice.consultaSaldoContaIdContaCorrente(contaId)).thenReturn(saldoConta);

        final ResponseEntity<SaldoDebitoResponse> response = template
                .getForEntity("/debito/saldo/contacorrente/" + contaId, SaldoDebitoResponse.class);
        final SaldoDebitoResponse result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(40.0, result.getSaldoDebito());
    }
}
