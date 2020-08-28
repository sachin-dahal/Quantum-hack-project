package com.hello.hackers;

public class Medicine {
    private String name;
    private String form;
    private int standardUnits;
    private String packageForm;
    private double price;
    private String Manufacturer;
    private String medicine_id;

    public Medicine(String name, String form, int standardUnits, String packageForm, double price, String manufacturer, String medicine_id) {
        this.name = name;
        this.form = form;
        this.standardUnits = standardUnits;
        this.packageForm = packageForm;
        this.price = price;
        Manufacturer = manufacturer;
        this.medicine_id = medicine_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getStandardUnits() {
        return standardUnits;
    }

    public void setStandardUnits(int standardUnits) {
        this.standardUnits = standardUnits;
    }

    public String getPackageForm() {
        return packageForm;
    }

    public void setPackageForm(String packageForm) {
        this.packageForm = packageForm;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }
}
