package com.example.onofftask.service;

import com.example.onofftask.model.CryptoMarketValue;
import com.example.onofftask.validator.DataValidator;
import java.math.BigDecimal;
import java.util.Arrays;
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

    @Value("${app.bitfine.url}")
    private String bitfineUrl;

    @Override
    public CryptoMarketValue parseDataFromJson(String symbolName) {
        CryptoMarketValue dataFromJson = new CryptoMarketValue();

        try {
            String jsonResult = getJsonFromApiBySymbol(symbolName);

            jsonResult = jsonResult.replaceAll("[\\[\\]\\\\\"]", "");

            DataValidator.validateString(jsonResult);

            String[] value = jsonResult.split(",");
            dataFromJson.setName(value[0]);
            dataFromJson.setValueInEuros(new BigDecimal(value[2]));
        } catch (Exception e) {
            log.error("", e);
        }
        return dataFromJson;
    }

    private String getJsonFromApiBySymbol(String symbolName) {
        String resp = "";
        try {
            DataValidator.validateString(symbolName);

            String       url          = bitfineUrl + "?symbols=" + symbolName;
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