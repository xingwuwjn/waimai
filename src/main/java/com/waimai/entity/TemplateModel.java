package com.waimai.entity;


import lombok.Data;

@Data
public class TemplateModel {
    public String touser ;     //模板openid
    public String template_id;   //模板id
    public String url; //模板详情url

    public String topcolor;  //顶部颜色

    public TemplateData data ;

    public TemplateModel(String dinner,String num) {
        this.data= new TemplateData(
                new TempItem(dinner),
                new TempItem(num)
        );

    }
}
