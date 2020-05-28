package com.example.adminapp;

public class MenuData {

    public String usermenu;

    public MenuData(String usermenu){
        this.usermenu=usermenu;
    }

    public void setUsermenu(String usermenu) {
        this.usermenu = usermenu;
    }
    public String getUsermenu(){
        return usermenu;
    }
}
class MenuData2{
    public String result;
    public MenuData2(){
    }
    public MenuData2(String usermenu){
        this.result=usermenu;
    }
    public void setUsermenu(String usermenu) {
        this.result = usermenu;
    }
    public String getUsermenu(){
        return result;
    }
}
