package com.waimai.controller;


import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

@Controller
@RequestMapping(value="/wx")
public class WxRegisterContrller {
     public  static  final String TOKEN = "wjnwjnwaimai";
    @RequestMapping(value="/keeper",method=RequestMethod.GET)
    public void hourseKeeper(HttpServletRequest request, HttpServletResponse response){
        try {
            // 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数
            String signature = request.getParameter("signature");// 微信加密签名（token、timestamp、nonce。）
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");// 随机字符串
            PrintWriter out = response.getWriter();
            // 将token、timestamp、nonce三个参数进行字典序排序
            String[] params = new String[] { TOKEN, timestamp, nonce };
            Arrays.sort(params);
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            String clearText = params[0] + params[1] + params[2];
            String algorithm = "SHA-1";
            String sign = new String(
                    Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
            // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if (signature.equals(sign)) {
                response.getWriter().print(echostr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
