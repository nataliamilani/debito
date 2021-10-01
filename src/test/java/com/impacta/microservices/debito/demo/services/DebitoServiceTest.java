package com.impacta.microservices.debito.demo.services;

import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.exceptions.ContaIdNotFoundException;
import com.impacta.microservices.debito.demo.exceptions.TipoContaNotFoundException;
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
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito = new Debito(idTransacao, contaId, valorDebito, clienteId, tipoConta);

        final Debito result = debitoService.criarDebito(debito);

        assertEquals(contaId, result.getContaId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(tipoConta, result.getTipoConta());
    }

    @Test
    public void ListarContas(){
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);
        debitoService.criarDebito(debito1);
        debitoService.criarDebito(debito2);

        final List<Debito> result = debitoService.listarContas();

        assertEquals(2, result.size());
    }

    @Test
    public void ConsultarTransacoesPorTipoConta(){
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);
        debitoService.criarDebito(debito1);
        debitoService.criarDebito(debito2);

        final List<Debito> result = debitoService.consultaTransacoesTipoConta(tipoConta);

        assertEquals(2, result.size());
        assertEquals("contacorrente", result.get(0).getTipoConta());
    }

    @Test(expected = TipoContaNotFoundException.class)
    public void RetornarTipoContaNotFoundExceptionQuandoConsultarTipoContaInexistente(){
        debitoService.consultaTransacoesTipoConta("teste");
    }

    @Test
    public void buscarContaIdComOTipoContaCorrente(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Debito debito = new Debito(idTransacao, contaId, valorDebito, clienteId, tipoConta);
        debitoRepository.save(debito);

        final List<Debito> result = debitoService.consultaContaIdContaCorrente(contaId);

        assertEquals(contaId, result.get(0).getContaId());
        assertEquals(clienteId, result.get(0).getClienteId());
        assertEquals(tipoConta, result.get(0).getTipoConta());
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarContaIdContaCorrenteInexistente(){
        debitoService.consultaContaIdContaCorrente(999);
    }

    @Test
    public void buscarContaIdComOTipoContaInvestimento(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Debito debito = new Debito(idTransacao, contaId, valorDebito, clienteId, tipoConta);
        debitoRepository.save(debito);

        final List<Debito> result = debitoService.consultaContaIdInvestimento(contaId);

        assertEquals(contaId, result.get(0).getContaId());
        assertEquals(clienteId, result.get(0).getClienteId());
        assertEquals(tipoConta, result.get(0).getTipoConta());
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarContaIdInvestimentoInexistente(){
        debitoService.consultaContaIdInvestimento(888);
    }

    @Test
    public void consultarSaldoContaIdComOTipoContaInvestimento(){
        final Integer contaId = 1;
        final Double valorDebito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Double saldoConta = 40.0;
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);
        debitoRepository.save(debito1);
        debitoRepository.save(debito2);

        Double result = debitoService.consultaSaldoContaIdContaInvestimento(contaId);

        assertEquals(60.0, result, Double.POSITIVE_INFINITY);
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarSaldoContaIdInvestimentoInexistente(){
        debitoService.consultaSaldoContaIdContaInvestimento(888);
    }

    @Test
    public void consultarSaldoContaIdComOTipoContaCorrente(){
        final Integer contaId = 1;
        final Double valorDebito = 40.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Double saldoConta = 80.0;
        final Debito debito1 = new Debito(1, contaId, valorDebito, clienteId, tipoConta);
        final Debito debito2 = new Debito(2, contaId, valorDebito, clienteId, tipoConta);
        debitoRepository.save(debito1);
        debitoRepository.save(debito2);

        Double result = debitoService.consultaSaldoContaIdContaCorrente(contaId);

        assertEquals(120.0, result, Double.POSITIVE_INFINITY);
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarSaldoContaIdContaCorrenteInexistente(){
        debitoService.consultaSaldoContaIdContaCorrente(999);
    }
}
