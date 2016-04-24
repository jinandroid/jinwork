package com.deftsoft.datalisting;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deftsoft.database.Bean;
import com.deftsoft.database.DatabaseHelper;

public class MainActivity extends Activity implements OnClickListener {

	EditText name_et, age_et;
	Button save_btn, show_btn;
	Context _context;
	DatabaseHelper dbHelper;
	Bean bean;
	String nameValue, ageValue;
	Activity activity;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_context = MainActivity.this;
		bean= new Bean();
		dbHelper = new DatabaseHelper(_context);

		findIds();
		setListener();
	}

	private void findIds() {
		name_et = (EditText) findViewById(R.id.name_et);
		age_et = (EditText) findViewById(R.id.age_et);
		save_btn = (Button) findViewById(R.id.save_btn);
		show_btn = (Button) findViewById(R.id.show_btn);
	}

	private void setListener() {
		save_btn.setOnClickListener(this);
		show_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save_btn:
			Checkvalidation();
			Toast.makeText(_context, "Saving data", Toast.LENGTH_SHORT).show();
						
			bean.setName(((EditText) findViewById(R.id.name_et)).getText().toString().trim());
			bean.setAge(((EditText) findViewById(R.id.age_et)).getText().toString().trim());
			dbHelper.insertRecord(bean);

			break;

		case R.id.show_btn:
		//	Toast.makeText(_context, "Show All Saved data", Toast.LENGTH_SHORT).show();
			Intent in = new Intent(_context, ShowData.class);
			startActivity(in);

			break;

		default:
			break;
		}

	}

	private void Checkvalidation() {
		String sName = name_et.getText().toString();
		String sAge = age_et.getText().toString().toString();
		
		if (sName.matches("")) {
			Toast.makeText(_context, "Field cannot be empty", Toast.LENGTH_SHORT).show();		
		    return;
		}
		
		if (sAge.matches("")) {
			Toast.makeText(_context, "Field cannot be empty", Toast.LENGTH_SHORT).show();
		    return;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	@Override
	public void onBackPressed() 
	{
		new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_logo)
				.setTitle("")
				.setMessage("Are your sure want to close this Application ?")
				.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() 
				{
							@Override
							public void onClick(DialogInterface dialog, int which) 
							{
								MainActivity.this.finish();
							}
				}).setNegativeButton("Cancel", null).show();
	  }
}