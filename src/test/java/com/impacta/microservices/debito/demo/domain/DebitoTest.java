package com.impacta.microservices.debito.demo.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DebitoTest {

    @Test
    public void CriarDebito() {
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final double valorDebito = 10.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";

        final Debito debito = new Debito(idTransacao, contaId, valorDebito, clienteId, tipoConta);

        assertEquals(idTransacao, debito.getIdTransacao());
        assertEquals(contaId, debito.getContaId());
        assertEquals(valorDebito, debito.getValorDebito());
        assertEquals(clienteId, debito.getClienteId());
        assertEquals(tipoConta, debito.getTipoConta());
    }
}
