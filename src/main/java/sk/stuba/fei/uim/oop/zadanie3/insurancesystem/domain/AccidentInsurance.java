package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.Territory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AccidentInsurance extends LifeInsurance {
    @Min(0)
    private double permanentDisabilityIndemnity;
    @Min(0)
    private double deathIndemnity;
    @Min(0)
    private double hospitalizationIndemnity;
    @NotNull
    private Territory territory;

    public AccidentInsurance( LocalDate dateOfFormation, User insurer, LocalDate commencementOfInsurance,
                             LocalDate terminationOfInsurance, double insuranceIndemnity, double monthlyPayment, User insured,
                             double permanentDisabilityIndemnity, double deathIndemnity, double hospitalizationIndemnity, Territory territory)
    {
        super( dateOfFormation, insurer, commencementOfInsurance, terminationOfInsurance,
                insuranceIndemnity, monthlyPayment,insured);
        setDeathIndemnity(deathIndemnity);
        setHospitalizationIndemnity(hospitalizationIndemnity);
        setPermanentDisabilityIndemnity(permanentDisabilityIndemnity);
        setTerritory(territory);
        setGetType(this.getType());
        setGetTypeName(this.getTypeName());
    }

    public void setPermanentDisabilityIndemnity(double permanentDisabilityIndemnity) {
        if(permanentDisabilityIndemnity<0){
            throw  new IllegalArgumentException("Invalid value of indemntity");
        }
        this.permanentDisabilityIndemnity = permanentDisabilityIndemnity;
    }

    public void setDeathIndemnity(double deathIndemnity) {
        if(deathIndemnity<0){
            throw  new IllegalArgumentException("Invalid value of indemntity");
        }
        this.deathIndemnity = deathIndemnity;
    }

    public void setHospitalizationIndemnity(double hospitalizationIndemnity) {
        if(hospitalizationIndemnity<0){
            throw  new IllegalArgumentException("Invalid value of indemntity");
        }
        this.hospitalizationIndemnity = hospitalizationIndemnity;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return territory;
    }

    public String getTypeName(){return "Accident Insurance";}

    public String getType(){return "accident_insurance";}

    public double getPermanentDisabilityIndemnity() {
        return permanentDisabilityIndemnity;
    }

    public double getDeathIndemnity() {
        return deathIndemnity;
    }

    public double getHospitalizationIndemnity() {
        return hospitalizationIndemnity;
    }

}
