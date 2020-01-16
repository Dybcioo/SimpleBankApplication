package com.example.bankapplication.model.Credit;

import com.example.bankapplication.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit")
    private Long id;
    @OneToOne(mappedBy = "credit")
    private Account account;
    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "target_id")
    private Target target;
    @OneToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
    @Column(name = "start_date")
    private Calendar startDate;
    @Column(nullable = false, name = "loan_amount")
    private BigDecimal loanAmount;
    @Column(nullable = false)
    private BigDecimal installment;
    @Column(nullable = false, name = "repayment_amount")
    private BigDecimal repaymentAmount;

    public Credit(Calendar startDate, BigDecimal loanAmount, BigDecimal installment, BigDecimal repaymentAmount) {
        this.startDate = startDate;
        this.loanAmount = loanAmount;
        this.installment = installment;
        this.repaymentAmount = repaymentAmount;
    }
}
