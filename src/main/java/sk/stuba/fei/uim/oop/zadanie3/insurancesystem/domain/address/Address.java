package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.address;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Address {
@NotNull
    private String postcode;
    @NotNull
    private String town;
    @NotNull
    private String street;
    @NotNull
    private String number;

    public Address(String postcode, String town, String street, String number) {
        setPostcode(postcode);
        setTown(town);
        setStreet(street);
        setNumber(number);
    }

    public String getFullAdress(){
        return (this.getStreet() +" "+this.getNumber()+" "+this.getTown()+" "+this.getPostcode());
    }
}
