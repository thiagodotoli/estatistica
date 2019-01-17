package com.comex.v2.estatistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comex.v2.estatistica.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {}
