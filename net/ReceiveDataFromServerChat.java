package net;

import java.io.IOException;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import activity.Login;
import android.util.Log;
public class ReceiveDataFromServerChat extends Thread{
	public static String havaData;
		@Override
		public void run() {
			// TODO Auto-generated method stub
				try {
						Thread.sleep(5000); 
						HttpClient httpClient=new DefaultHttpClient();
						HttpGet httpGet=new HttpGet("http://172.16.11.151:8088/CJDBC/"+Login.name+".xml");
						HttpResponse httpResponse=httpClient.execute(httpGet);
						if(httpResponse.getStatusLine().getStatusCode()==200){
							HttpEntity entity=httpResponse.getEntity();
							String response=EntityUtils.toString(entity,"utf-8");
							parseXMLWithPull(response);
						}
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}	
		private void parseXMLWithPull(String xmlData){
			try{
				XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
				XmlPullParser xmlPullParser=factory.newPullParser();
				xmlPullParser.setInput(new StringReader(xmlData));
				int eventType=xmlPullParser.getEventType();
				String user_name="";
				String sex="";
				String height="";
				String weight="";
				while(eventType!=XmlPullParser.END_DOCUMENT){
					String nodeName=xmlPullParser.getName();
					switch(eventType){
					case XmlPullParser.START_TAG:
						if("user_name".equals(nodeName)){
							user_name=xmlPullParser.nextText();
						}else if("sex".equals(nodeName)){
							sex=xmlPullParser.nextText();
						}else if("weight".equals(nodeName)){
							weight=xmlPullParser.nextText();
						}else if("height".equals(nodeName)){
							height=xmlPullParser.nextText();
						}
						break;
					case XmlPullParser.END_TAG:
						if("perinformation".equals(nodeName)){
							Log.d("MainAcvity", user_name);
							Log.d("MainAcvity", sex);
							Log.d("MainAcvity", height);
							Log.d("MainAcvity", weight);
						}
						break;
					default:
							break;	
					}
					eventType=xmlPullParser.next();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public int confirm(){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;

		}
}
