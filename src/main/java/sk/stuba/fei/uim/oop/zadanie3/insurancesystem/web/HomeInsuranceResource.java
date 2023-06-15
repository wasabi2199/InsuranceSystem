package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address.Address;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.HomeInsurance;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums.PropertyType;
import sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class HomeInsuranceResource {
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
    private boolean garageInsurance;

    public HomeInsuranceResource(HomeInsurance homeInsurance) {
        this.id = homeInsurance.getId();
        this.dateOfFormation = homeInsurance.getDateOfFormation();
        this.insurerId = homeInsurance.getInsurer().getId();
        this.commencementOfInsurance = homeInsurance.getCommencementOfInsurance();
        this.terminationOfInsurance = homeInsurance.getTerminationOfInsurance();
        this.insuranceIndemnity = homeInsurance.getInsuranceIndemnity();
        this.monthlyPayment = homeInsurance.getMonthlyPayment();
        this.propertyType = homeInsurance.getPropertyType();
        this.street = homeInsurance.getPropertyAddress().getStreet();
        this.number=homeInsurance.getPropertyAddress().getNumber();
        this.postcode = homeInsurance.getPropertyAddress().getPostcode();
        this.town = homeInsurance.getPropertyAddress().getTown();
        this.propertyValue = homeInsurance.getPropertyValue();
        this.garageInsurance = homeInsurance.isGarageInsurance();
    }
    public String getFullAddress(){
        return (this.getStreet() +" "+this.getNumber()+" "+this.getTown()+" "+this.getPostcode());
    }

    HomeInsurance toHomeInsurance(long id, User insurer){
        Address address=new Address(this.postcode,this.town,this.street,this.number);
       HomeInsurance homeInsurance =new HomeInsurance( dateOfFormation,  insurer,  commencementOfInsurance,
                terminationOfInsurance,  insuranceIndemnity,  monthlyPayment,
                propertyType,  address,  propertyValue);
       homeInsurance.setId(id);
        return homeInsurance;
    };
}
