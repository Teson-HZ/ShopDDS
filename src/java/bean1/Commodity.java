/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean1;

/**
 *
 * @author WHZ2002
 */
public class Commodity {
    private String commodity_Id;
    private String commodity_Barcode;
    private String commodity_Name;
    private String commodity_Class;
    private String commodity_Unit;
    private String commodity_Price;
    
    public Commodity(){};

    public Commodity(String commodity_Id, String commodity_Barcode, String commodity_Name, String commodity_Class, String commodity_Unit, String commodity_Price) {
        this.commodity_Id = commodity_Id;
        this.commodity_Barcode = commodity_Barcode;
        this.commodity_Name = commodity_Name;
        this.commodity_Class = commodity_Class;
        this.commodity_Unit = commodity_Unit;
        this.commodity_Price = commodity_Price;
    }

    public String getCommodity_Id() {
        return commodity_Id;
    }

    public void setCommodity_Id(String commodity_Id) {
        this.commodity_Id = commodity_Id;
    }

    public String getCommodity_Barcode() {
        return commodity_Barcode;
    }

    public void setCommodity_Barcode(String commodity_Barcode) {
        this.commodity_Barcode = commodity_Barcode;
    }

    public String getCommodity_Name() {
        return commodity_Name;
    }

    public void setCommodity_Name(String commodity_Name) {
        this.commodity_Name = commodity_Name;
    }

    public String getCommodity_Class() {
        return commodity_Class;
    }

    public void setCommodity_Class(String commodity_Class) {
        this.commodity_Class = commodity_Class;
    }

    public String getCommodity_Unit() {
        return commodity_Unit;
    }

    public void setCommodity_Unit(String commodity_Unit) {
        this.commodity_Unit = commodity_Unit;
    }

    public String getCommodity_Price() {
        return commodity_Price;
    }

    public void setCommodity_Price(String commodity_Price) {
        this.commodity_Price = commodity_Price;
    }
    
}
