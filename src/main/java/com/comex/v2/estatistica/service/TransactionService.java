package com.comex.v2.estatistica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comex.v2.estatistica.model.Transaction;
import com.comex.v2.estatistica.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	public void add(Transaction lancamento) {}
}
