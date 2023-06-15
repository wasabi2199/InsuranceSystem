package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.AccidentInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AccidentInsuranceResource {
    private long id;
    @NotNull
    @PastOrPresent
    private LocalDate dateOfFormation;
    @NotNull
    private long insurerId;
    @NotNull
    private LocalDate commencementOfInsurance;
    @NotNull
    private LocalDate terminationOfInsurance;
    @Min(0)
    private double insuranceIndemnity;
    @Min(0)
    private double monthlyPayment;
    @NotNull
    private long insuredId;
    @Min(0)
    private double permanentDisabilityIndemnity;
    @Min(0)
    private double deathIndemnity;
    @Min(0)
    private double hospitalizationIndemnity;
    @NotNull
    private Territory territory;

    public AccidentInsuranceResource(AccidentInsurance accidentInsurance) {
        this.id = accidentInsurance.getId();
        this.dateOfFormation = accidentInsurance.getDateOfFormation();
        this.insurerId = accidentInsurance.getInsurer().getId();
        this.commencementOfInsurance = accidentInsurance.getCommencementOfInsurance();
        this.terminationOfInsurance = accidentInsurance.getTerminationOfInsurance();
        this.insuranceIndemnity = accidentInsurance.getInsuranceIndemnity();
        this.monthlyPayment = accidentInsurance.getMonthlyPayment();
        this.permanentDisabilityIndemnity = accidentInsurance.getPermanentDisabilityIndemnity();
        this.deathIndemnity = accidentInsurance.getDeathIndemnity();
        this.hospitalizationIndemnity = accidentInsurance.getHospitalizationIndemnity();
        this.territory = accidentInsurance.getTerritory();
        this.insuredId=accidentInsurance.getInsured().getId();
    }


    AccidentInsurance toAccidentInsurance(long id, User insured, User insurer){
       AccidentInsurance accidentInsurance= new AccidentInsurance(  dateOfFormation,  insurer,  commencementOfInsurance,
                 terminationOfInsurance,  insuranceIndemnity,  monthlyPayment,  insured,
         permanentDisabilityIndemnity,  deathIndemnity,  hospitalizationIndemnity, territory);
        if(id!=0){
            accidentInsurance.setId(id);
        }

        return accidentInsurance;
    }

}
