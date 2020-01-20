package com.example.bankapplication.filter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionFilter {

    private String numberAccount;
    private String kind;
    private long id;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Date minDate;
    private Date maxDate;


    public boolean isEmpty(){
        return StringUtils.isEmpty(numberAccount) && minAmount == null && maxAmount == null && minDate == null && maxDate == null && (kind ==null || kind.equals(""));
    }

    public void clear(){
        this.numberAccount = "";
        this.minAmount = null;
        this.maxAmount= null;
        this.minDate = null;
        this.maxDate = null;
        this.kind = "";
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(numberAccount)) {
            return null;
        }else{
            return "%"+numberAccount+"%";
        }
    }

}
