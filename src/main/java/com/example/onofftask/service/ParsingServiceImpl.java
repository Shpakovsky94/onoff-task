package com.example.onofftask.service;

import com.example.onofftask.exception.InvalidInputException;
import com.example.onofftask.exception.InvalidNameException;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.HttpRequestResolver;
import com.example.onofftask.resolver.StringResolver;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final HttpRequestResolver httpRequestResolver;

    @Autowired
    public ParsingServiceImpl(HttpRequestResolver httpRequestResolver) {
        this.httpRequestResolver = httpRequestResolver;
    }

    @Override
    public Crypto validateParamsAndReturnCrypto(final HttpServletRequest request) throws InvalidInputException, InvalidNameException {
        String       name        = httpRequestResolver.getParam("name", request).toUpperCase();
        Double       amount      = httpRequestResolver.getDoubleParam("amount", request);
        String       wallet      = httpRequestResolver.getParam("wallet", request);
        List<String> symbolsList = parseDataFromJsonToArray(name, true);
        if (!symbolsList.contains(name.toLowerCase())) {
            throw new InvalidNameException();
        }
        return new Crypto(name, amount, wallet);
    }

    @Override
    public BigDecimal getCurrentMarketPriceFromApi(String symbolName) {
        List<String> valueList = parseDataFromJsonToArray(symbolName, false);

        return !valueList.isEmpty() ? new BigDecimal(valueList.get(2)) : BigDecimal.ZERO;
    }

    @Override
    public List<String> parseDataFromJsonToArray(
        String symbolName,
        Boolean isSymbolsList
    ) {
        List<String> valueList = null;
        try {
            String jsonResult = getJsonFromApiBySymbol(symbolName, isSymbolsList);

            jsonResult = jsonResult.replaceAll("[\\[\\]\\\\\"]", "");

            StringResolver.validateString(jsonResult);

            valueList = Arrays.asList(jsonResult.split(","));
        } catch (Exception e) {
            log.error("", e);
        }
        return valueList;
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