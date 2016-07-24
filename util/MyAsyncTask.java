package util;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Object, Object, Object>{
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
		super.onPreExecute();
		
	}
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		publishProgress(); //传入进度值            手动调用onProgressUpdate方法
		return null;
	}
	//获取进度，更新进度条  获取doInBackground传过来的参数          知晓任务完成的进度
	@Override
	protected void onProgressUpdate(Object... values) {                            
		// TODO Auto-generated method stub
		super.onProgressUpdate(values); 
	}
	

}
