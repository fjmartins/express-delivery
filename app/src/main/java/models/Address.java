package models;

/**
 * Created by kleber on 22/05/16. // morte aqui papai
 */

public class Address {

    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String number;
    private String complement;
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "CEP: " + zipCode +
                "\nRua: '" + street +
                "\nNúmero:'" + number;
    }

    public String detail() {
        return "CEP: " + zipCode +
                "\nRua: '" + street +
                "\nNúmero:'" + number +
                "\nComplemento:'" + complement +
                "\nBairro:'" + district +
                "\nCidade:'" + city +
                "\nEstado:'" + state;
    }
}
