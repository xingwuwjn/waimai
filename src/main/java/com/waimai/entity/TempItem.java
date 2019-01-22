package com.waimai.entity;


import lombok.Data;

@Data
public class TempItem {

    public String value;
    public String color="#173177";


    public TempItem(String v) {
        this.value = v;
    }

}
