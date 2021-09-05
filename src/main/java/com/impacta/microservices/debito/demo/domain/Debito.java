package com.impacta.microservices.debito.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "debito")
public class Debito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contaId", nullable = false)
    private Integer contaId;

    @Column(name = "valorDebito", nullable = false)
    private BigDecimal valorDebito;

    public Debito() {
        super();
    }

    @JsonCreator
    public Debito(@JsonProperty("conta_id") Integer contaId, @JsonProperty("valor_debito") BigDecimal valorDebito) {
        this.contaId = contaId;
        this.valorDebito = valorDebito;
    }

    public BigDecimal getValorDebito() {return valorDebito;}

    public Integer getContaId() {return contaId;}

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public void setContaId(Integer contaId) {this.contaId = contaId;}

}
