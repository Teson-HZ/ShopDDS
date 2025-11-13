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
public class Headadmin {
    private String headadmin_Id;
    private String headadmin_Name;
    private String headadmin_Pwd;
    private String headadmin_Tel;

    public Headadmin(String headadmin_Id, String headadmin_Name, String headadmin_Pwd, String headadmin_Tel) {
        this.headadmin_Id = headadmin_Id;
        this.headadmin_Name = headadmin_Name;
        this.headadmin_Pwd = headadmin_Pwd;
        this.headadmin_Tel = headadmin_Tel;
    }
    
    public Headadmin(){};

    public String getHeadadmin_Id() {
        return headadmin_Id;
    }

    public void setHeadadmin_Id(String headadmin_Id) {
        this.headadmin_Id = headadmin_Id;
    }

    public String getHeadadmin_Name() {
        return headadmin_Name;
    }

    public void setHeadadmin_Name(String headadmin_Name) {
        this.headadmin_Name = headadmin_Name;
    }

    public String getHeadadmin_Pwd() {
        return headadmin_Pwd;
    }

    public void setHeadadmin_Pwd(String headadmin_Pwd) {
        this.headadmin_Pwd = headadmin_Pwd;
    }

    public String getHeadadmin_Tel() {
        return headadmin_Tel;
    }

    public void setHeadadmin_Tel(String headadmin_Tel) {
        this.headadmin_Tel = headadmin_Tel;
    }
    
    
}
