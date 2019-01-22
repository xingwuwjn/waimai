package com.waimai.entity;


import lombok.Data;

@Data
public class TemplateData {
    public TempItem dinner;
    public TempItem num;

    public TemplateData(TempItem dinner, TempItem num) {
        this.dinner = dinner;
        this.num = num;
    }
}
