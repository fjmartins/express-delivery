package models;

/**
 * Created by kleber on 22/05/16.
 */
public class Address {

    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String number;
    private String complement;

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

    public String toString() {
        return "{"
                +" zipcode: "+this.zipCode
                +", city: "+this.city
                +", number: "+this.number
                +", complement: "+this.complement
                +", state: "+this.state
                +", district: "+this.district+
                " }";
    }
}
