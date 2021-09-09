package com.impacta.microservices.debito.demo.services;

import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.repository.DebitoRepository;
import com.impacta.microservices.debito.demo.service.DebitoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class DebitoServiceTest {

    @Autowired
    private DebitoService debitoService;

    @Autowired
    private DebitoRepository debitoRepository;

    @Before
    public void setUp() {
        debitoRepository.deleteAll();
    }

    @Test
    public void salvarDebitoCriado(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito = new Debito(contaId, valorDebito, clienteId, tipoConta);

        final Debito result = debitoService.create(debito);

        assertEquals(contaId, result.getContaId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(tipoConta, result.getTipoConta());

    }

    @Test
    public void buscarContaIdComOTipoContaCorrente(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito = new Debito(contaId, valorDebito, clienteId, tipoConta);
        debitoRepository.save(debito);

        final List<Debito> result = debitoService.findByContaIdAndTipoContaCorrente(contaId);

        assertEquals(contaId, result.get(0).getContaId());
        assertEquals(clienteId, result.get(0).getClienteId());
        assertEquals(tipoConta, result.get(0).getTipoConta());

    }

    @Test
    public void buscarContaIdComOTipoContaInvestimento(){
        final Integer contaId = 1;
        final Double valorDebito = -20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Debito debito = new Debito(contaId, valorDebito, clienteId, tipoConta);
        debitoRepository.save(debito);

        final List<Debito> result = debitoService.findByContaIdAndTipoContaInvestimento(contaId);

        assertEquals(contaId, result.get(0).getContaId());
        assertEquals(clienteId, result.get(0).getClienteId());
        assertEquals(tipoConta, result.get(0).getTipoConta());
    }

    @Test
    public void quandoContaIdNaoForEncontradoRetornarListaVazia(){
        final Integer contaId = 1;

        final List<Debito> result = debitoService.findByContaIdAndTipoContaInvestimento(contaId);

        assertTrue(result.isEmpty());
    }
}
