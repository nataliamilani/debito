package com.impacta.microservices.debito.demo.controller;

import com.impacta.microservices.debito.demo.domain.Debito;
import com.impacta.microservices.debito.demo.service.DebitoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/debito")
public class DebitoController {

    private final DebitoService debitoService;

    public DebitoController(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    /*@GetMapping
    public List<Debito> list() throws UnknownHostException {
        System.out.println("Hostname: " + InetAddress.getLocalHost().getHostName());
        List<Debito> debitoList = debitoService.list();
        return debitoList;
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Debito createDebito(@RequestBody Debito debitoRequest) {
        return debitoService.create(debitoRequest);
    }
}
