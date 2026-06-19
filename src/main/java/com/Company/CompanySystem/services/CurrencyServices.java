package com.Company.CompanySystem.services;


import com.Company.CompanySystem.dto.CurrencyConvertResponse;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServices {

    @Qualifier("freeCurrencyApiClient")
    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(CurrencyServices.class);

    public Map<String,Double> getCurrencyList() {
        log.trace("Trying to retrieve all currency list");
        Map<String,Object> apiResponse =  restClient.get()
                .uri("/v1/latest?apikey=fca_live_8KBRRsJSqVF3ehclgzFRKGTIcJeBfiRvB2aijflr")
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                });
        log.debug("List of currency :{}",apiResponse);
        return (Map<String,Double>) apiResponse.get("data");
    }

    public CurrencyConvertResponse currencyConversion(String from, String toCur, String amount) {
        System.out.println("********"+ "/v1/latest?apikey=fca_live_8KBRRsJSqVF3ehclgzFRKGTIcJeBfiRvB2aijflr&currencies="+toCur+"&base_currency="+from);
        Map<String,Object> apiResponse =  restClient.get()
                .uri("/v1/latest?apikey=fca_live_8KBRRsJSqVF3ehclgzFRKGTIcJeBfiRvB2aijflr&currencies="+toCur+"&base_currency="+from)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                });
        Map<String,Double> mapVal =(Map<String, Double>) apiResponse.get("data");
        return new CurrencyConvertResponse("1 "+from +" is equal to " +mapVal.get(toCur) + " "+ toCur);
    }
}
