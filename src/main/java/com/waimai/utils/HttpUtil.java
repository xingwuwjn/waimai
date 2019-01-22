package com.waimai.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;
import java.security.Security;
import java.util.Map;


public class HttpUtil {
	/**
	 * http post请求
	 * @param url
	 * @param content
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendPost(String url, String content)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String result = null;
		try{
			HttpPost httpost = new HttpPost(url.trim());
			httpost.setEntity(new StringEntity(content, Consts.UTF_8));
			HttpResponse hresponse;
			hresponse = httpclient.execute(httpost);
			HttpEntity entity = hresponse.getEntity();
			result = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static String sendGet(String url) throws ClientProtocolException,
			IOException {

		if(url == null
				|| url.trim().length() == 0){

			return null;
		}

		if(url.startsWith("http:")){

			return sendGet(url, null);
		}

		return null;

	}

	/**
	 *
	 * @param url
	 * @param header
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static byte[] sendGetByte(String url, Map<String, String> header) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		byte[] result = null;
		ByteArrayOutputStream bo = null;
		try{
			HttpGet httpget = new HttpGet(url.trim());
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 100000);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					100000);
			if (header != null) {
				for (String key : header.keySet()) {
					String value = header.get(key);
					httpget.setHeader(key, value);
				}
			}

			HttpResponse hresponse;
			hresponse = httpclient.execute(httpget);
			HttpEntity entity = hresponse.getEntity();
			if(entity.getContentLength()<=0) return null;
			InputStream is = entity.getContent();
			bo = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int r = 0;
			while((r=is.read(buff))>0){
				bo.write(buff, 0, r);
			}
			result = bo.toByteArray();
		}finally{
			httpclient.getConnectionManager().shutdown();
			if(bo!=null) bo.close();
		}
		return result;
	}

	/**
	 *
	 * @param url
	 * @param header
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendGet(String url, Map<String, String> header)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String result = null;
		try{
			HttpGet httpget = new HttpGet(url.trim());
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 100000);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					100000);
			if (header != null) {
				for (String key : header.keySet()) {
					String value = header.get(key);
					httpget.setHeader(key, value);
				}
			}

			HttpResponse hresponse;
			hresponse = httpclient.execute(httpget);
			HttpEntity entity = hresponse.getEntity();
			result = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 *
	 * @param requestUrl
	 * @param requestMethod  GET POST
	 * @param outputStr
	 * @return
	 */
	public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
		StringBuffer buffer=null;
		try{
			//创建SSLContext
			SSLContext sslContext= SSLContext.getInstance("SSL");
			TrustManager[] tm={new MyX509TrustManager()};
			//初始化
			sslContext.init(null, tm, new java.security.SecureRandom());;
			//获取SSLSocketFactory对象
			SSLSocketFactory ssf=sslContext.getSocketFactory();
			URL url=new URL(requestUrl);
			HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			//设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();
			//往服务器端写内容
			if(null!=outputStr){
				OutputStream os=conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}

			//读取服务器端返回的内容
			InputStream is=conn.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"utf-8");
			BufferedReader br=new BufferedReader(isr);
			buffer=new StringBuffer();
			String line=null;
			while((line=br.readLine())!=null){
				buffer.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
