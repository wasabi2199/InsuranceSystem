package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.TravelInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.TravelPurpose;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class TravelInsuranceResource {
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
    boolean inEu;
    @NotNull
    TravelPurpose travelPurpose;

    public TravelInsuranceResource(TravelInsurance travelInsurance) {
        this.id = travelInsurance.getId();
        this.dateOfFormation = travelInsurance.getDateOfFormation();
        this.insurerId = travelInsurance.getInsurer().getId();
        this.commencementOfInsurance = travelInsurance.getCommencementOfInsurance();
        this.terminationOfInsurance = travelInsurance.getTerminationOfInsurance();
        this.insuranceIndemnity = travelInsurance.getInsuranceIndemnity();
        this.monthlyPayment = travelInsurance.getMonthlyPayment();
        this.insuredId = travelInsurance.getInsured().getId();
        this.travelPurpose = travelInsurance.getTravelPurpose();
        this.inEu = travelInsurance.isInEu();
    }

    TravelInsurance toTravelInsurance(long id,User insured, User insurer){
        TravelInsurance travelInsurance=new TravelInsurance( dateOfFormation,  insurer,  commencementOfInsurance,
                 terminationOfInsurance,  insuranceIndemnity,  monthlyPayment,
         insured,  inEu,  travelPurpose);
        if(id!=0){
            travelInsurance.setId(id);
        }

        return travelInsurance;
    }
    public String isInEuString() {
        if(this.isInEu()==true){return  "in EU";}
        return "Out of EU";
    }
}
