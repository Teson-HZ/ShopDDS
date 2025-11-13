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
public class Employee {
    private String employee_Id;
    private String employee_Name;
    private String employee_Pwd;
    private String employee_Ide;
    private String employee_Tel;

    public Employee(String employee_Id, String employee_Name, String employee_Pwd, String employee_Ide, String employee_Tel) {
        this.employee_Id = employee_Id;
        this.employee_Name = employee_Name;
        this.employee_Pwd = employee_Pwd;
        this.employee_Ide = employee_Ide;
        this.employee_Tel = employee_Tel;
    }
    
    public Employee(){};

    public String getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(String employee_Id) {
        this.employee_Id = employee_Id;
    }

    public String getEmployee_Name() {
        return employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        this.employee_Name = employee_Name;
    }

    public String getEmployee_Pwd() {
        return employee_Pwd;
    }

    public void setEmployee_Pwd(String employee_Pwd) {
        this.employee_Pwd = employee_Pwd;
    }

    public String getEmployee_Ide() {
        return employee_Ide;
    }

    public void setEmployee_Ide(String employee_Ide) {
        this.employee_Ide = employee_Ide;
    }

    public String getEmployee_Tel() {
        return employee_Tel;
    }

    public void setEmployee_Tel(String employee_Tel) {
        this.employee_Tel = employee_Tel;
    }
    
    
    
}
