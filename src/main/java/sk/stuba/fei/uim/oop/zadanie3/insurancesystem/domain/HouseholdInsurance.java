package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;

import javax.validation.constraints.Min;
import java.time.LocalDate;

public class HouseholdInsurance extends GeneralInsurance {

    @Min(0)
    private double equipmentValue;

    public HouseholdInsurance( LocalDate dateOfFormation, User insurer, LocalDate commencementOfInsurance,
                              LocalDate terminationOfInsurance, double insuranceIndemnity, double monthlyPayment,
                              PropertyType propertyType, Address propertyAddress, double propertyValue, double equipmentValue)
    {
        super(dateOfFormation, insurer, commencementOfInsurance, terminationOfInsurance,
                insuranceIndemnity, monthlyPayment, propertyType, propertyAddress, propertyValue);
        setEquipmentValue(equipmentValue);
        setGetType(this.getType());
        setGetTypeName(this.getTypeName());
    }


    public double getEquipmentValue() {
        return equipmentValue;
    }

    public void setEquipmentValue(double equipmentValue) {
        if(equipmentValue<0){
            throw  new IllegalArgumentException("Invalid value of equipment");
        }this.equipmentValue = equipmentValue;
    }
    public String getTypeName(){return "Household Insurance";}
    public String getType(){return "household_insurance";}
}
