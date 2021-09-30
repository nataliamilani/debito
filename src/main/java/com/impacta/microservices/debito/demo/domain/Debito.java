package com.impacta.microservices.debito.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "debito")
public class Debito implements Serializable {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Integer id;
/*
    @Transient
    private String porta;
*/
    @Column(name = "conta_id")
    private Integer contaId;

    @Column(name = "valor_debito")
    private Double valorDebito;

    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(name = "tipo_conta")
    private String tipoConta;

    public Debito(){ super (); }

    @JsonCreator
    public Debito(@JsonProperty("conta_id") Integer contaId,
                  @JsonProperty("valor_debito") Double valorDebito,
                  @JsonProperty("cliente_id") Integer clienteId,
                  @JsonProperty("tipo_conta") String tipoConta) {
        this.contaId = contaId;
        this.valorDebito = valorDebito;
        this.clienteId = clienteId;
        this.tipoConta = tipoConta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public Double getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(Double valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

}
