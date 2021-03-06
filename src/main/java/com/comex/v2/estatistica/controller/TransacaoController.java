package com.comex.v2.estatistica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comex.v2.estatistica.model.StatisticsDTO2;
import com.comex.v2.estatistica.model.TransactionDTO;
import com.comex.v2.estatistica.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TransacaoController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transactions")
	public ResponseEntity<Void> transactions(@Valid @RequestBody TransactionDTO transactionDTO) {
		try {
			
			if(transactionService.addTransaction(transactionDTO) <= 60)
				return new ResponseEntity<>(null, HttpStatus.CREATED);
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (RuntimeException e) {
			log.error(e.getMessage(),e);
			throw e;
		}
	}

	@GetMapping("/statistics")
	public ResponseEntity<StatisticsDTO2> statistics() {
		try {
			StatisticsDTO2 statistics = transactionService.resultStatistics();
			return new ResponseEntity<>(statistics, HttpStatus.OK);
		} catch (RuntimeException e) {
			log.error(e.getMessage(),e);
			throw e;
		}
	}
}
