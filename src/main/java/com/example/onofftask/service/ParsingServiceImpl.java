package com.example.onofftask.service;

import com.example.onofftask.model.CryptoMarketValue;
import com.example.onofftask.resolver.StringResolver;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParsingServiceImpl implements ParsingService {

    private static final Logger log = LoggerFactory.getLogger(ParsingServiceImpl.class);

    @Value("${app.bitfine.url.tickers}")
    private String bitfineUrlTickers;

    @Value("${app.bitfine.url.symbols}")
    private String bitfineUrlSymbols;

    @Override
    public CryptoMarketValue parseDataFromJson(String symbolName) {
        CryptoMarketValue cryptoMarketValue = new CryptoMarketValue();
        List<String> valueList = parseDataFromJsonToArray(symbolName, false);

        try {
            cryptoMarketValue.setName(valueList.get(0));
            cryptoMarketValue.setValueInEuros(new BigDecimal(valueList.get(2)));
        } catch (Exception e) {
            log.error("", e);
        }
        return cryptoMarketValue;
    }

    @Override
    public List<String> parseDataFromJsonToArray(String symbolName, Boolean isSymbolsList) {
        List<String> value = null;
        try {
            String jsonResult = getJsonFromApiBySymbol(symbolName, isSymbolsList);

            jsonResult = jsonResult.replaceAll("[\\[\\]\\\\\"]", "");

            StringResolver.validateString(jsonResult);

             value = Arrays.asList(jsonResult.split(","));

        } catch (Exception e) {
            log.error("", e);
        }
        return value;
    }

    private String getJsonFromApiBySymbol(String symbolName, Boolean isSymbolsList) {
        String resp = "";
        String       url;
        try {
            StringResolver.validateString(symbolName);
            if (isSymbolsList) {
                url = bitfineUrlSymbols;
            }else {
                url = bitfineUrlTickers + "?symbols=t" + symbolName;
            }

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders  headers      = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "PostmanRuntime/7.26.8");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            resp = responseEntity.getBody();
        } catch (Exception e) {
            log.error("", e);
        }
        return resp;
    }
}