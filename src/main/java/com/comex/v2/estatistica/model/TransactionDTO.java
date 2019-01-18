package com.comex.v2.estatistica.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import lombok.Data;

@Data
public class TransactionDTO {
	
	private Long timestamp;
	private double amount;

	public TransactionDTO() {};
	
	TransactionDTO(Long timestamp, double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }
    
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}	
	
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!TransactionDTO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final TransactionDTO other = (TransactionDTO) obj;
        if ((this.timestamp == null) ? (other.timestamp != null) : !this.timestamp.equals(other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 23;
        hash = 3 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        return hash;
    }
}
