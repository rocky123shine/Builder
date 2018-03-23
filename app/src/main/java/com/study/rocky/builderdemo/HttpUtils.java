package com.study.rocky.builderdemo;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class HttpUtils {

    public static String get(String urlStr) {
        String result = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                result = new String(StreamTool.readInputStream(inputStream));
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String post(String urlStr, Map<String, Object> paramMap) throws IOException {

        StringBuilder paramBuilder = new StringBuilder();
        Set<String> keys = paramMap.keySet();
        for (String key : keys) {
            paramBuilder.append(key);
            paramBuilder.append("=");
            paramBuilder.append(paramMap.get(key));
            paramBuilder.append("&");
        }
        paramBuilder.deleteCharAt(paramBuilder.length() - 1);

        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setDoOutput(true);//允许输出
        httpConn.setDoInput(true);//允许输入
        httpConn.setUseCaches(false);//不允许缓存
        httpConn.setRequestMethod("POST");
        //请求属性
        httpConn.setRequestProperty("Charset", "UTF-8");
        // 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        //建立输入流 向指定的URL传入参数
        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
        dos.writeBytes(paramBuilder.toString());
        dos.flush();
        dos.close();
// 获得响应状态
        int resultCode = httpConn.getResponseCode();

        StringBuffer sb = new StringBuffer();
        if (HttpURLConnection.HTTP_OK == resultCode) {
            String readLine = new String();
            //解析网络请求
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine).append("\n");

            }

            responseReader.close();
            return sb.toString();
        }
        return null;
    }
    public interface OnHttpResultListener {
        public void onResult(String result);
    }
}
