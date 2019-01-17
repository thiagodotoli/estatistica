package com.comex.v2.estatistica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comex.v2.estatistica.model.Transaction;
import com.comex.v2.estatistica.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TransacaoController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transactions")
	public ResponseEntity<Void> transactions(@Valid @RequestBody Transaction transaction) {
		try {
			transactionService.add(transaction);
			return ResponseEntity.ok(null);
		} catch (RuntimeException e) {
			log.error(e.getMessage(),e);
			throw e;
		}
	}
}
