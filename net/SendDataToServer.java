package net;

import android.os.Handler;
import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Cand on 2016/7/8.
 */
public class SendDataToServer {
    private static String url="http://172.16.11.151:8088/CJDBC/servlets/ServletsPOST";
    public static final int SEND_SUCCESS=0x123;
    public static final int SEND_FAIL=404;
    
    
//  private Handler handler;
    private String name;
    private String pwd;
    private static final String Go="Go";
    
    private String content;
    private Integer mark;
    
    
    public SendDataToServer(String name,String pwd){
           this.name=name;
           this.pwd=pwd;
       }
    
//    public SendDataToServer(Handler handler,String name,String pwd){
//        this.handler=handler;
//        this.name=name;
//        this.pwd=pwd;
//    }
    public SendDataToServer(String name,String content,Integer mark){
    	this.name=name;
    	this.content=content;
    	this.mark=mark;
    }
    public void SendDataToServers(){
        final Map<String,String> map=new HashMap<String,String>();
        map.put("name",name);
        map.put("pwd",pwd);
        
        map.put("content", content);
        map.put("mark", mark+"");
        
        
        
        map.put("GO", Go);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                  if (
                    		sendPostRequest(map,url,"utf-8");
//                    		){
//                        handler.sendEmptyMessage(SEND_SUCCESS);
//                    }else{
//                        handler.sendEmptyMessage(SEND_FAIL);
//                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public boolean sendPostRequest(Map<String,String> param,String url,String encoding)throws Exception{
        StringBuffer sb=new StringBuffer();
        if (!url.equals("")&!param.isEmpty()){
          //  sb.append("?");
            for (Map.Entry<String,String>entry:param.entrySet()){
                sb.append(entry.getKey()+"=");
                //sb.append(URLEncoder.encode(entry.getValue(),encoding));
                sb.append(entry.getValue());
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1); 
        }
        Log.d("hellowords", sb.toString());
        byte[] data=sb.toString().getBytes();
        HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",data.length+"");
        OutputStream outputStream=conn.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        if ( conn.getResponseCode()==200){
            return true;
        }



//        HttpURLConnection conn=(HttpURLConnection)new URL(sb.toString()).openConnection();
//        conn.setConnectTimeout(5000);
//        conn.setRequestMethod("GET");
//        if (conn.getResponseCode()==200){
//            System.out.print("hhhh");
//            return true;
//        }
        return false;
    }

}
