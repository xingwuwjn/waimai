package com.waimai.service;



import com.alibaba.fastjson.JSONObject;
import com.waimai.service.common.WxServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URLEncoder;


@Service
public class WxAuthorizeServer implements WxServer {

    @Value("#{prop.mpAppId}")
    private String mpAppId;

    @Value("#{prop.mpAppSecret}")
    String mpAppSecret;

    /**
     *
     * @param redirectURI   授权回掉地址
     * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     * @param state   参数
     * @return
     */
    @Override
    public String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                mpAppId,
                URLEncoder.encode(redirectURI),
                scope, state);
    }

    /**
     *  获取基础的access_token
     *  @return
     */
    public String getAccess_token(){
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String fiurl=String.format(url,mpAppId,mpAppSecret);
        RestTemplate restTemplate=new RestTemplate();
        String wxAccess_token=restTemplate.getForObject(fiurl,String.class);
        JSONObject object = JSONObject.parseObject(wxAccess_token);
        String access_token= (String) object.get("access_token");
        return access_token;
    }

    public void setAppId(String appId) {
        this.mpAppId = appId;
    }


}
