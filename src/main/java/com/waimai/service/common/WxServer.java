package com.waimai.service.common;

public interface WxServer {
    String CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";


    //拼接获取code的url
    String oauth2buildAuthorizationUrl(String var1, String var2, String var3);
}
