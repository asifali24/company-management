package com.Company.CompanySystem.controllers;


import com.Company.CompanySystem.dto.CurrencyConvertResponse;
import com.Company.CompanySystem.services.CurrencyServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/currency")
@RequiredArgsConstructor
public class Currency {

    final private CurrencyServices currencyServices;

    @GetMapping("/all-currency-list")
    public ResponseEntity<Map<String,Double>> getCurrencyList(){
        return ResponseEntity.status(HttpStatus.OK).body(currencyServices.getCurrencyList());
    }

    @GetMapping("/{from}/convert-to/{toCur}/{amount}")
    public ResponseEntity<CurrencyConvertResponse> currencyConversion(@PathVariable String from, @PathVariable String toCur, @PathVariable String amount){
        return ResponseEntity.status(HttpStatus.OK).body(currencyServices.currencyConversion(from,toCur,amount));
    }
}
