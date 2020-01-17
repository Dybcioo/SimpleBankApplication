package com.example.bankapplication.model.Credit;

import com.example.bankapplication.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "target_id")
    private Target target;
    @OneToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
    @Column(name = "start_date")
    private Date startDate;
    @Column(nullable = false, name = "loan_amount")
    private BigDecimal loanAmount;
    @Column(nullable = false)
    private BigDecimal installment;
    @Column(nullable = false, name = "repayment_amount")
    private BigDecimal repaymentAmount;

    public Credit(Date startDate, BigDecimal loanAmount, BigDecimal installment, BigDecimal repaymentAmount) {
        this.startDate = startDate;
        this.loanAmount = loanAmount;
        this.installment = installment;
        this.repaymentAmount = repaymentAmount;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", account=" + account +
                ", status=" + status.getName() +
                ", targetName=" + target.getName() +
                //", proposalId=" + proposal.getId() +
                ", startDate=" + startDate +
                ", loanAmount=" + loanAmount +
                ", installment=" + installment +
                ", repaymentAmount=" + repaymentAmount +
                '}';
    }
}
