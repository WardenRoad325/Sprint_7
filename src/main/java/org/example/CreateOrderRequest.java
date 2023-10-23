package org.example;
import java.util.List;

public class CreateOrderRequest {
    private String firstName;
    private String lastName;
    private String address;
    private List<String> color;

    public void setColor(List<String> color) {
        this.color = color;
    }

    private String phone;
    private String comment;
    private int rentTime;
    private String deliveryDate;
    private int metroStation;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public CreateOrderRequest(String firstName, String lastName, String address, List<String> color, String phone, String comment, int rentTime, String deliveryDate, int metroStation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.color = color;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.metroStation = metroStation;

    }
}


