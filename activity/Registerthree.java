package activity;



import java.io.File;

import com.example.cchat.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Registerthree extends Activity implements OnClickListener{
	private Button submit;
	private ImageView choose;
	private Uri imageUri;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerthree);
		submit=(Button)findViewById(R.id.submit);
		
		//设置控件的大小
		choose=(ImageView)findViewById(R.id.add);
		RelativeLayout.LayoutParams relativeLayout=(RelativeLayout.LayoutParams)choose.getLayoutParams();
		relativeLayout.height=500;
		relativeLayout.width=500;
		choose.setLayoutParams(relativeLayout);
		choose.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add:
			File outputImage =new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
			try {
				if(outputImage.exists()){
					outputImage.delete();
				}
				outputImage.createNewFile();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			imageUri=Uri.fromFile(outputImage);
			Intent intent=new Intent("android.intent.action.GET_CONTENT");
			intent.setType("image/*");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(intent,1);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			if(resultCode==RESULT_OK){
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));	
					choose.setImageBitmap(bitmap);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
	}
}
