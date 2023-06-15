package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public abstract class GeneralInsurance extends Contract {
    @NotNull
    private PropertyType propertyType;
    @NotNull
    private Address propertyAddress;
    @Min(0)
    private double propertyValue;

    public GeneralInsurance( LocalDate dateOfFormation, User insurer, LocalDate commencementOfInsurance,
                            LocalDate terminationOfInsurance, double insuranceIndemnity, double monthlyPayment,
                            PropertyType propertyType, Address propertyAddress, double propertyValue)
    {
        super(dateOfFormation, insurer, commencementOfInsurance, terminationOfInsurance,
                insuranceIndemnity, monthlyPayment," "," ");
        setPropertyAddress(propertyAddress);
        setPropertyType(propertyType);
        setPropertyValue(propertyValue);
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyAddress(Address propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public void setPropertyValue(double propertyValue) {
       if(propertyValue <0){throw new IllegalArgumentException("Invalid property value");}
        this.propertyValue = propertyValue;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public Address getPropertyAddress() {
        return propertyAddress;
    }

    public double getPropertyValue() {
        return propertyValue;
    }
    public String getFullAddress(){
        return (this.propertyAddress.getStreet() +" "+this.propertyAddress.getNumber()+" "+this.propertyAddress.getTown()+" "+this.propertyAddress.getPostcode());
    }
}
