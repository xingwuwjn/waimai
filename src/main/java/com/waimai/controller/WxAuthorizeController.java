package com.waimai.controller;


import com.alibaba.fastjson.JSONObject;
import com.waimai.constants.WxConsts;
import com.waimai.service.WxAuthorizeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

@Controller
@RequestMapping(value = "/wxAuthorize")
public class WxAuthorizeController {

    @Value("#{prop.wechatMpAuthorize}")
    String wechatMpAuthorize;

    @Value("#{prop.mpAppId}")
    String mpAppId;

    @Value("#{prop.mpAppSecret}")
    String mpAppSecret;

    @Autowired
    WxAuthorizeServer wxAuthorizeServer;

    /**
     * 构造获取code的url,此url用来配置到微信端
     * @param returnUrl   state参数，调用完毕后的回调函数
     * @return
     */
    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    public String  getCode(@RequestParam("returnUrl") String returnUrl) {
        String url =wechatMpAuthorize+"/wxAuthorize/authorize";//获取code后的回调地址
        //获取code请求的url
        String redirectUrl = wxAuthorizeServer.oauth2buildAuthorizationUrl(url, WxConsts.AUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        System.out.println(redirectUrl);
        return "redirect:"+redirectUrl;
    }


    /**
     * 获取code后的回调地址
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value = "/authorize",method = RequestMethod.GET)
    public String authorize(HttpServletRequest request, @RequestParam("code") String code, @RequestParam("state") String state){
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        String fiurl=String.format(url, mpAppId,mpAppSecret,code);
        RestTemplate restTemplate=new RestTemplate();
        String wxAccess_token=restTemplate.getForObject(fiurl,String.class);
        JSONObject object = JSONObject.parseObject(wxAccess_token);
        String openid= (String) object.get("openid");
        String mainurl=state+"?openid="+openid;
        return "redirect:"+mainurl;
    }

    public String getWechatMpAuthorize() {
        return wechatMpAuthorize;
    }

    public void setWechatMpAuthorize(String wechatMpAuthorize) {
        this.wechatMpAuthorize = wechatMpAuthorize;
    }

    public String getMpAppId() {
        return mpAppId;
    }

    public void setMpAppId(String mpAppId) {
        this.mpAppId = mpAppId;
    }

    public String getMpAppSecret() {
        return mpAppSecret;
    }

    public void setMpAppSecret(String mpAppSecret) {
        this.mpAppSecret = mpAppSecret;
    }
}
