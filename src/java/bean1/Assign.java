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
public class Assign {
    private String supermarket_Id;
    private String commodity_Id;
    private int assign_Amount;

    public Assign(String supermarket_Id, String commodity_Id, int assign_Amount) {
        this.supermarket_Id = supermarket_Id;
        this.commodity_Id = commodity_Id;
        this.assign_Amount = assign_Amount;
    }
    
    public Assign(){};

    public String getSupermarket_Id() {
        return supermarket_Id;
    }

    public void setSupermarket_Id(String supermarket_Id) {
        this.supermarket_Id = supermarket_Id;
    }

    public String getCommodity_Id() {
        return commodity_Id;
    }

    public void setCommodity_Id(String commodity_Id) {
        this.commodity_Id = commodity_Id;
    }

    public int getAssign_Amount() {
        return assign_Amount;
    }

    public void setAssign_Amount(int assign_Amount) {
        this.assign_Amount = assign_Amount;
    }
    
    
    
}
