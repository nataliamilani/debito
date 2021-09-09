package com.impacta.microservices.debito.demo.controllers;

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

        when(debitorservice.create(debito)).thenReturn(debito);

        final ResponseEntity<Debito> response = template
                .postForEntity("/debito", debito, Debito.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarListaDeMovimentosDaConta(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito1 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.findByContaIdAndTipoContaCorrente(contaId)).thenReturn(List.of(debito1, debito2));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/movimentos/contacorrente/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoInvestimentoRetornarListaDeMovimentosDaConta(){
        final Integer contaId = 2;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Debito debito1 = new Debito(contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(contaId, valorDebito, clienteId, tipoConta);

        when(debitorservice.findByContaIdAndTipoContaInvestimento(contaId)).thenReturn(List.of(debito1, debito2));

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/movimentos/investimento/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorUmaContaIdInexistenteRetornarListaDeMovimentosVazia(){
        final Integer contaId = 2;

        when(debitorservice.findByContaIdAndTipoContaInvestimento(contaId)).thenReturn(List.of());

        final ResponseEntity<Debito[]> response = template
                .getForEntity("/debito/movimentos/investimento/" + contaId, Debito[].class );
        final List<Debito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(result.isEmpty());
    }
}
