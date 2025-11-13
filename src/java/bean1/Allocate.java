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
public class Allocate {
    private String supermarket_Id;
    private String warehouse_Id;
    private String commodity_Id;
    private int allocate_Amount;
    
    public Allocate(){};

    public Allocate(String supermarket_Id, String warehouse_Id, String commodity_Id, int allocate_Amount) {
        this.supermarket_Id = supermarket_Id;
        this.warehouse_Id = warehouse_Id;
        this.commodity_Id = commodity_Id;
        this.allocate_Amount = allocate_Amount;
    }

    public Allocate(String warehouse_Id, String commodity_Id, int allocate_Amount) {
        this.warehouse_Id = warehouse_Id;
        this.commodity_Id = commodity_Id;
        this.allocate_Amount = allocate_Amount;
    }
    
    public String getSupermarket_Id() {
        return supermarket_Id;
    }

    public void setSupermarket_Id(String supermarket_Id) {
        this.supermarket_Id = supermarket_Id;
    }

    public String getWarehouse_Id() {
        return warehouse_Id;
    }

    public void setWarehouse_Id(String warehouse_Id) {
        this.warehouse_Id = warehouse_Id;
    }

    public String getCommodity_Id() {
        return commodity_Id;
    }

    public void setCommodity_Id(String commodity_Id) {
        this.commodity_Id = commodity_Id;
    }

    public int getAllocate_Amount() {
        return allocate_Amount;
    }

    public void setAllocate_Amount(int allocate_Amount) {
        this.allocate_Amount = allocate_Amount;
    }
    
    
}
