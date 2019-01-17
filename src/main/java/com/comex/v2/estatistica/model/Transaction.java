package com.comex.v2.estatistica.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity
public class Transaction implements Serializable {
	
	// TODO: Converter para Date @Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Data/Hora da transação obrigatória")
	private Long timestamp;

	@NotNull(message = "Valor é obrigatório")
	private Double amount;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}	
	
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Transaction.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Transaction other = (Transaction) obj;
        if ((this.timestamp == null) ? (other.timestamp != null) : !this.timestamp.equals(other.timestamp)) {
            return false;
        }
        if (!this.amount.equals(other.amount)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 23;
        hash = 3 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        hash = 3 * hash + this.amount.intValue();
        return hash;
    }
}
