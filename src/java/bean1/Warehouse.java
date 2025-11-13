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
public class Warehouse {
    private String warehouse_Id;
    private String warehouse_Name;
    private String warehouse_Adr;
    private String warehouse_Tel;
    private String employee_Id;

    public Warehouse(String warehouse_Id, String warehouse_Name, String warehouse_Adr, String warehouse_Tel, String employee_Id) {
        this.warehouse_Id = warehouse_Id;
        this.warehouse_Name = warehouse_Name;
        this.warehouse_Adr = warehouse_Adr;
        this.warehouse_Tel = warehouse_Tel;
        this.employee_Id = employee_Id;
    }
    
    public Warehouse(){};

    public String getWarehouse_Id() {
        return warehouse_Id;
    }

    public void setWarehouse_Id(String warehouse_Id) {
        this.warehouse_Id = warehouse_Id;
    }

    public String getWarehouse_Name() {
        return warehouse_Name;
    }

    public void setWarehouse_Name(String warehouse_Name) {
        this.warehouse_Name = warehouse_Name;
    }

    public String getWarehouse_Adr() {
        return warehouse_Adr;
    }

    public void setWarehouse_Adr(String warehouse_Adr) {
        this.warehouse_Adr = warehouse_Adr;
    }

    public String getWarehouse_Tel() {
        return warehouse_Tel;
    }

    public void setWarehouse_Tel(String warehouse_Tel) {
        this.warehouse_Tel = warehouse_Tel;
    }

    public String getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(String employee_Id) {
        this.employee_Id = employee_Id;
    }
    
}
