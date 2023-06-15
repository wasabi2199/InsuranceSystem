package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;

import java.time.LocalDate;

public class HomeInsurance extends GeneralInsurance {

    private boolean garageInsurance;

    public HomeInsurance( LocalDate dateOfFormation, User insurer, LocalDate commencementOfInsurance,
                         LocalDate terminationOfInsurance, double insuranceIndemnity, double monthlyPayment,
                         PropertyType propertyType, Address propertyAddress, double propertyValue)
    {
        super( dateOfFormation, insurer, commencementOfInsurance, terminationOfInsurance,
                insuranceIndemnity, monthlyPayment, propertyType, propertyAddress, propertyValue);
        setGarageInsurance(garageInsurance);
        setGetType(this.getType());
        setGetTypeName(this.getTypeName());
    }


    public String isGarageInsured() {
        if(garageInsurance){return "garage insured";}
        return "garage not insured";
    }

    public void setGarageInsurance(boolean garageInsurance) {
            this.garageInsurance=garageInsurance;
    }

    public boolean isGarageInsurance() {
        return garageInsurance;
    }
    public String getTypeName(){return "Home Insurance";}
    public String getType(){return "home_insurance";}

}
