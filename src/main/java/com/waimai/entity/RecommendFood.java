package com.waimai.entity;


import lombok.Data;


@Data
public class RecommendFood extends BaseEntity implements Comparable<RecommendFood>{
    //食品字段
    private String name;//名称
    private String food_id;//食物id
    private String shop_id;//店铺id
    private int month_sales;//月售出
    private Float rating;//评价
    private String rating_count;//评价人数
    private int price;//价格
    private int packing_fee;//打包费
    //店铺字段
    private String tips_102;  //优惠信息
    private int status;   //店铺url
    private String shopname;//店铺名称
    private String close_hour;//关门时间
    private String open_hour;//开门时间
    private int minsendprice;//起送价
    private Float delivery_fee;//配送费
    private String address;//地址

    @Override
    public int compareTo(RecommendFood o) {
        // TODO Auto-generated method stub
        if(this.rating>o.rating)
            return -1;//由高到底排序
        else if(this.rating<o.rating)
            return 1;
        else{
            if(this.rating>o.rating)
                return 1;//由底到高排序
            else if(this.rating<o.rating)
                return -1;
            else
                return 0;
        }
    }

}
