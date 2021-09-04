package com.impacta.microservices.debito.demo.domain;

import java.math.BigDecimal;


public class Debito {

    private Integer contaId;
    private BigDecimal debito;

    public Debito() {super();}

    public Debito(BigDecimal debito, Integer contaId) {
        this.debito = debito;
        this.contaId = contaId;
    }

    public BigDecimal getDebito() {return debito;}

    public Integer getContaId() {return contaId;}

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public void setContaId(Integer contaId) {this.contaId = contaId;}

}
