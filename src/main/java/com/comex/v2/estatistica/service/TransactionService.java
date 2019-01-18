package com.comex.v2.estatistica.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.comex.v2.estatistica.model.StatisticsDTO2;
import com.comex.v2.estatistica.model.TransactionDTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class TransactionService {
    private final ConcurrentSkipListMap<Instant , Double> transactions
      = new ConcurrentSkipListMap<>(Comparator.comparingLong(value -> value.toEpochMilli()));

    public Long addTransaction(TransactionDTO transactionDTO) {
    	log.info("add transaction {} => {}",transactionDTO.getTimestamp(), transactionDTO.getAmount());

//    	Instant timestamp = Instant.ofEpochMilli(transactionDTO.getTimestamp());
    	transactions.put(Instant.ofEpochMilli(transactionDTO.getTimestamp()), transactionDTO.getAmount());

    		System.out.println(TimeUnit.MILLISECONDS.toMinutes(Instant.now().toEpochMilli() - transactionDTO.getTimestamp()));
    	return TimeUnit.MILLISECONDS.toMinutes(Instant.now().toEpochMilli() - transactionDTO.getTimestamp());
    }

    public ConcurrentNavigableMap<Instant, Double> getTransactionsFromLastMinute() {
        return transactions.tailMap(Instant
          .now()
          .minus(1, ChronoUnit.MINUTES));
    }
    
    public StatisticsDTO2 resultStatistics() {
    	ConcurrentNavigableMap<Instant, Double> transactions = getTransactionsFromLastMinute();
    	StatisticsDTO2 statisticsDTO2 = new StatisticsDTO2();
    	
    	statisticsDTO2.setSum(transactions.entrySet().stream().parallel().mapToDouble(v ->v.getValue().doubleValue()).sum());
    	statisticsDTO2.setCount(transactions.entrySet().stream().parallel().count());
    	
    	return statisticsDTO2;
    }

}