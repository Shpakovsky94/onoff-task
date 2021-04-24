package com.example.onofftask.service;

import com.example.onofftask.model.CryptoMarketValue;
import java.util.List;

public interface ParsingService {
    /**
     * Parse Json into List of Tweets
     */

    CryptoMarketValue parseDataFromJson(String symbolName);

    List<String> parseDataFromJsonToArray(
        String symbolName,
        Boolean isSymbolsList
    );
}