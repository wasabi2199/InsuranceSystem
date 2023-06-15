package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.TravelPurpose;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelInsurance extends LifeInsurance {
    private boolean inEu;

    private TravelPurpose travelPurpose;

    public TravelInsurance( LocalDate dateOfFormation, User insurer, LocalDate commencementOfInsurance,
                           LocalDate terminationOfInsurance, double insuranceIndemnity, double monthlyPayment,
                           User insured, boolean inEu, TravelPurpose travelPurpose)
    {
        super( dateOfFormation, insurer, commencementOfInsurance, terminationOfInsurance,
                insuranceIndemnity, monthlyPayment,insured);
        setInEu(inEu);
        setTravelPurpose(travelPurpose);
        setGetType(this.getType());
        setGetTypeName(this.getTypeName());
    }

    public void setTravelPurpose(TravelPurpose travelPurpose) {
        this.travelPurpose = travelPurpose;
    }
    public TravelPurpose getTravelPurpose() {
        return travelPurpose;
    }

    public void setInEu(boolean inEU) {
        this.inEu = inEU;
    }

    public boolean isInEu() {
        return inEu;
    }

    public String getTypeName(){return "Travel Insurance";}
    public String getType(){return "travel_insurance";}

    public String isInEuString() {
        if(this.isInEu()==true){return  "in EU";}
        return "Out of EU";
    }
}
