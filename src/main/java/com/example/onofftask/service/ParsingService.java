package com.example.onofftask.service;

import com.example.onofftask.exception.InvalidInputException;
import com.example.onofftask.exception.InvalidNameException;
import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface ParsingService {
    Crypto validateParamsAndReturnCrypto(HttpServletRequest request) throws InvalidInputException, InvalidNameException;

    /**
     * Parse Json into List of Tweets
     */

    BigDecimal getCurrentMarketPriceFromApi(String symbolName);

    List<String> parseDataFromJsonToArray(
        String symbolName,
        Boolean isSymbolsList
    );
}