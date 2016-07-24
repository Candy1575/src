package activity;


import com.example.cchat.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import chat.MainActivity;
import main.Seek;
import net.ReceiveDataFromServer;
import net.SendDataToServer;

public class Login extends Activity implements OnClickListener {
	public TextView user_name,user_password;
	public TextView forget_password,immediately_register;
	public Button Login;
	
	public static String name,password;
	public ProgressDialog dialog=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		user_name=(TextView)findViewById(R.id.l_name);
		user_password=(TextView)findViewById(R.id.l_password);
		
		forget_password=(TextView)findViewById(R.id.l_forget);
		immediately_register=(TextView)findViewById(R.id.l_register);
		Login=(Button)findViewById(R.id.login);
		Login.setOnClickListener(this);
		
		forget_password.setClickable(true);
		forget_password.setFocusable(true);
		forget_password.setOnClickListener(this);
		
		immediately_register.setOnClickListener(this);
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.l_forget:
			Intent lf_intent=new Intent(Login.this,Forget.class);
			lf_intent.putExtra("user_name",user_name.getText().toString());
			startActivity(lf_intent);
			break;
		case R.id.l_register:
			Intent lr_intent=new Intent(Login.this,Registerone.class);
			startActivity(lr_intent);
			break;
		case R.id.login:
			validate();
			break;
		default:
				break;
		}
	}
	//验证用户名和密码在后台是否存在
	public void validate(){
		   name=user_name.getText().toString();
           password=user_password.getText().toString();
           myAsyncTask task=new myAsyncTask();
           task.execute();
//         Log.d("MainAcvity", ReceiveDataFromServer.havaData);  
	}
	class myAsyncTask extends AsyncTask<Void, Integer, Void>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			
			super.onPreExecute();
			dialog=ProgressDialog.show(Login.this,  "登陆提示","正在登陆。。", false);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			SendDataToServer server=new SendDataToServer(name,password);
			server.SendDataToServers();
			ReceiveDataFromServer RDFS=new ReceiveDataFromServer();
			RDFS.start();
			publishProgress(RDFS.confirm());
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			dialog.dismiss();
			if(values[0].equals(1)){
			Intent intent=new Intent(Login.this,MainActivity.class);
			startActivity(intent);
			}
//			else{
//				Toast.makeText(Login.this, "密码错误", Toast.LENGTH_LONG).show();
//			}
		}
		
		
	}
}
