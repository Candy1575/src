package activity;

import com.example.cchat.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Registerone extends Activity implements OnClickListener{
	public TextView user_name,password,confirm_password,email,identifying_code;
	public ImageView identifyingImage;
	public TextView changeImageView;
	public Button nextRegister;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerone);
		user_name=(TextView)findViewById(R.id.r_name);
		password=(TextView)findViewById(R.id.r_password);
		confirm_password=(TextView)findViewById(R.id.r_confirmpassword);
		email=(TextView)findViewById(R.id.r_email);
		identifying_code=(TextView)findViewById(R.id.r_identifying);
		changeImageView=(TextView)findViewById(R.id.r_changeImageView);
		nextRegister=(Button)findViewById(R.id.r_nextRegister);
		identifyingImage=(ImageView)findViewById(R.id.r_identifyImage);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.r_changeImageView:
			
			break;
		case R.id.r_nextRegister:
			Intent r_intent=new Intent(Registerone.this,Registertwo.class);
			startActivity(r_intent);
			break;
		default:
			break;
		}
	}
}
