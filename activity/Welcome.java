package activity;


import com.example.cchat.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Welcome extends Activity implements OnClickListener{
	public Button register,login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		register=(Button)findViewById(R.id.w_register);
		login=(Button)findViewById(R.id.w_login);
		register.setOnClickListener(this);
		login.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.w_login:
			Intent L_intent=new Intent(Welcome.this,Login.class);
			startActivity(L_intent);
			break;
		case R.id.w_register:
			Intent R_intent=new Intent(Welcome.this,Registerone.class);
			startActivity(R_intent);
			break;
		default:
			break;
		}
	}
	
}
