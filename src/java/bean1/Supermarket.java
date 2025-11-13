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
public class Supermarket {
    private String supermarket_Id;
    private String supermarket_Name;
    private String supermarket_Adr;
    private String supermarket_Tel;
    private String employee_Id;

    public Supermarket(String supermarket_Id, String supermarket_Name, String supermarket_Adr, String supermarket_Tel, String employee_Id) {
        this.supermarket_Id = supermarket_Id;
        this.supermarket_Name = supermarket_Name;
        this.supermarket_Adr = supermarket_Adr;
        this.supermarket_Tel = supermarket_Tel;
        this.employee_Id = employee_Id;
    }
    
    public Supermarket(){};

    public String getSupermarket_Id() {
        return supermarket_Id;
    }

    public void setSupermarket_Id(String supermarket_Id) {
        this.supermarket_Id = supermarket_Id;
    }

    public String getSupermarket_Name() {
        return supermarket_Name;
    }

    public void setSupermarket_Name(String supermarket_Name) {
        this.supermarket_Name = supermarket_Name;
    }

    public String getSupermarket_Adr() {
        return supermarket_Adr;
    }

    public void setSupermarket_Adr(String supermarket_Adr) {
        this.supermarket_Adr = supermarket_Adr;
    }

    public String getSupermarket_Tel() {
        return supermarket_Tel;
    }

    public void setSupermarket_Tel(String supermarket_Tel) {
        this.supermarket_Tel = supermarket_Tel;
    }

    public String getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(String employee_Id) {
        this.employee_Id = employee_Id;
    }
    
    
    
}
