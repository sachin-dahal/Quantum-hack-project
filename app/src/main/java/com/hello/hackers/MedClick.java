package com.hello.hackers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedClick {
    private String manufacturer;
    private double price;
    private String size;
    @SerializedName("components")
    private List<Component> components;

    public MedClick(String manufacturer, double price, String size, List<Component> components) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.size = size;
        this.components = components;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
