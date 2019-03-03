package com.company.transfers.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
@NoArgsConstructor
public class Transfer {

    private Long id;
    private Long originId;
    private Long receiverId;
    private BigDecimal amount;
    private Date date;

    @ConstructorProperties({"id", "originId", "receiverId", "amount", "date"})
    public Transfer(Long id, Long originId, Long receiverId, BigDecimal amount, Timestamp date) {
        this.id = id;
        this.originId = originId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.date = date;
    }
}
