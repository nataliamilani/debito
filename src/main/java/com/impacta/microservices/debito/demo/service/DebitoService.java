package com.impacta.microservices.debito.demo.service;

import com.impacta.microservices.debito.demo.domain.Debito;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DebitoService {

    private static final BigDecimal minValue = new BigDecimal(BigInteger.ONE);
    private static final BigDecimal maxValue = new BigDecimal(BigInteger.TEN);

    public List<Debito> list(){
        int numberOfDebit = new Random().nextInt(10) + 1;
        List<Debito> debitoList = new ArrayList<Debito>(10);
        for (int i = 0; i < numberOfDebit; i++) {
            BigDecimal randomValue = minValue.add(new BigDecimal(Math.random()).multiply(maxValue.subtract(minValue))).setScale(1, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("-1"));
            Random randomValueInt = new Random();
            Debito debito = new Debito(randomValue, randomValueInt.nextInt(10) );
            debitoList.add(debito);
        }
        System.out.println("debitoList: " + debitoList);
        return debitoList;
    }
}
