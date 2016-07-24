package activity;



import com.example.cchat.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import net.SendDataToServer;

public class Forget extends Activity implements OnClickListener{
	public Button obtainidentify ,confirm;
	public TextView email,identify,newPassword,confirmPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forget);
		obtainidentify=(Button)findViewById(R.id.f_obtainidentify);
		obtainidentify.setOnClickListener(this);
		confirm=(Button)findViewById(R.id.f_confirm);
		confirm.setOnClickListener(this);
		email=(TextView)findViewById(R.id.f_email);
		identify=(TextView)findViewById(R.id.f_identify);
		newPassword=(TextView)findViewById(R.id.f_newPassword);
		confirmPassword=(TextView)findViewById(R.id.f_confirmpassword);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.f_obtainidentify:
			break;
		case R.id.f_confirm:
			if(newPassword.getText().toString()==confirmPassword.getText().toString()){
				Intent f_intent=new Intent(Forget.this,Login.class);
				startActivity(f_intent);
				change();
			}
			break;
			default:
				break;
		}
	}
	//改变密码
	public void change(){
		Intent intent=getIntent();
		String user_name=intent.getStringExtra("user_name");
		String password=newPassword.getText().toString();
        SendDataToServer server=new SendDataToServer(user_name,password);
        server.SendDataToServers();
	}
}
