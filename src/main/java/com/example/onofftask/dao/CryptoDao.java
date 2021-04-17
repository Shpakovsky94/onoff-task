package com.example.onofftask.dao;

import com.example.onofftask.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoDao extends JpaRepository<Crypto,Long> {

}