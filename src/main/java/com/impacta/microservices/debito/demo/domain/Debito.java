package com.impacta.microservices.debito.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "debito")
public class Debito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Integer idTransacao;

    @Column(name = "conta_id")
    private Integer contaId;

    @Column(name = "valor_debito")
    private double valorDebito;

    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(name = "tipo_conta")
    private String tipoConta;

    public Debito(){ super (); }

    @JsonCreator
    public Debito(@JsonProperty("id_transacao") Integer idTransacao,
                  @JsonProperty("conta_id") Integer contaId,
                  @JsonProperty("valor_debito") double valorDebito,
                  @JsonProperty("cliente_id") Integer clienteId,
                  @JsonProperty("tipo_conta") String tipoConta) {
        this.idTransacao = idTransacao;
        this.contaId = contaId;
        this.valorDebito = valorDebito;
        this.clienteId = clienteId;
        this.tipoConta = tipoConta;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public double getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(double valorDebito) {
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
