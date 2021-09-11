package com.impacta.microservices.debito.demo.controller.response;

public class SaldoResponse {

    private Double saldo;

    public SaldoResponse(Double saldo){
        this.saldo = saldo;
    }

    public Double getSaldo() {return saldo;}

    public void setSaldo(Double saldo) {this.saldo = saldo;}
}
