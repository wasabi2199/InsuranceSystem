package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public abstract class Contract extends Entity {
    @NotNull
    @PastOrPresent
    private LocalDate dateOfFormation;
    @NotNull
    private User insurer;
    @NotNull
    private LocalDate commencementOfInsurance;
    @NotNull
    private LocalDate terminationOfInsurance;
    @Min(0)
    private double insuranceIndemnity;
    @Min(0)
    private double monthlyPayment;
    public String getTypeName;
    public String getType;

    public Contract( LocalDate dateOfFormation, User insurer, LocalDate commencementOfInsurance,
                    LocalDate terminationOfInsurance, double insuranceIndemnity,
                     double monthlyPayment,String getTypeName,String getType) {
        setDateOfFormation(dateOfFormation);
        this.insurer=insurer;
        setCommencementOfInsurance(commencementOfInsurance);
        setTerminationOfInsurance(terminationOfInsurance);
        setInsuranceIndemnity(insuranceIndemnity);
        setMonthlyPayment(monthlyPayment);
        setGetType(getType);
        setGetTypeName(getTypeName);
    }


    public void setInsuranceIndemnity(double insuranceIndemnity) {
        if(insuranceIndemnity<0){
            throw new IllegalArgumentException("Invalid value of insurance indemntiy") ;}
        this.insuranceIndemnity = insuranceIndemnity;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        if(monthlyPayment<0){
            throw new IllegalArgumentException("Invalid value of monthly payment") ;}
        this.monthlyPayment = monthlyPayment;
    }

    public LocalDate getDateOfFormation() {
        return dateOfFormation;
    }

    public String getTypeName() {
        return getTypeName;
    }

    public String getType() {
        return getType;
    }
}
