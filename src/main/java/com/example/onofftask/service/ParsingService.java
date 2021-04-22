package com.example.onofftask.service;

import com.example.onofftask.model.CryptoMarketValue;

public interface ParsingService {
    /**
     * Parse Json into List of Tweets
     */
    CryptoMarketValue parseDataFromJson(String symbolName);
}