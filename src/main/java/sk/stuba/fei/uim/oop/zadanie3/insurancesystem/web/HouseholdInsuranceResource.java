package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.*;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class HouseholdInsuranceResource {
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
    private PropertyType propertyType;
    @NotNull
    @Size(min=1)
    private String street;
    @NotNull
    @Size(min=1)
    private String number;
    @NotNull
    @Size(min=1)
    private String town;
    @NotNull
    @Size(min=1)
    private String postcode;
    @Min(0)
    private double propertyValue;
    @Min(0)
    private double equipmentValue;

    public HouseholdInsuranceResource(HouseholdInsurance householdInsurance) {
        this.id=householdInsurance.getId();
        this.dateOfFormation = householdInsurance.getDateOfFormation();
        this.insurerId = householdInsurance.getInsurer().getId();
        this.commencementOfInsurance = householdInsurance.getCommencementOfInsurance();
        this.terminationOfInsurance = householdInsurance.getTerminationOfInsurance();
        this.insuranceIndemnity = householdInsurance.getInsuranceIndemnity();
        this.monthlyPayment = householdInsurance.getMonthlyPayment();
        this.propertyType = householdInsurance.getPropertyType();
        this.street = householdInsurance.getPropertyAddress().getStreet();
        this.number=householdInsurance.getPropertyAddress().getNumber();
        this.postcode = householdInsurance.getPropertyAddress().getPostcode();
        this.town = householdInsurance.getPropertyAddress().getTown();
        this.propertyValue = householdInsurance.getPropertyValue();
        this.equipmentValue=householdInsurance.getEquipmentValue();
    }
    public String getFullAdress(){
        return (this.getStreet() +" "+this.getNumber()+" "+this.getTown()+" "+this.getPostcode());
    }
    HouseholdInsurance toHouseholdInsurance(long id, User insurer){
        Address address=new Address(this.postcode,this.town,this.street,this.number);

        HouseholdInsurance householdInsurance=new HouseholdInsurance(  dateOfFormation,  insurer,  commencementOfInsurance,
                terminationOfInsurance,  insuranceIndemnity,  monthlyPayment,
                propertyType,  address,  propertyValue, equipmentValue);
        if(id!=0){
            householdInsurance.setId(id);
        }

        return householdInsurance;
    };
}
