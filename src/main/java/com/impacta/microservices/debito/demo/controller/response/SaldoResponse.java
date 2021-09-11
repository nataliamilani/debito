package com.impacta.microservices.debito.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaldoResponse {

    private Double saldoDebito;

    @JsonCreator
    public SaldoResponse(@JsonProperty("saldo_debito") Double saldo){
        this.saldoDebito = saldo;
    }

    public Double getSaldoDebito() {return saldoDebito;}

    public void setSaldoDebito(Double saldoDebito) {this.saldoDebito = saldoDebito;}
}
