package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public abstract class LifeInsurance extends Contract {
    @NotNull
    private User insured;

    public LifeInsurance( LocalDate dateOfFormation, User insurer,
                         LocalDate commencementOfInsurance, LocalDate terminationOfInsurance,
                         double insuranceIndemnity, double monthlyPayment, User insured)
    {
        super( dateOfFormation, insurer, commencementOfInsurance, terminationOfInsurance,
                insuranceIndemnity, monthlyPayment,"a","a");
        setInsured(insured);
    }

    public User getInsured() {
        return insured;
    }

    public void setInsured(User insured) {
        this.insured = insured;
    }
}
