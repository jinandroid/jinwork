package com.deftsoft.datalisting;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.deftsoft.database.Bean;
import com.deftsoft.database.DatabaseHelper;

public class ShowData extends Activity implements OnClickListener {

	Context context;
	CheckBox all_cb;
	ListView listView;
	Button allDel_btn, specificDel_btn;
	DatabaseHelper dbHelper;

	public ArrayList<Bean> hashMapsArrayList;
	MyAdapter mAdp;
	ArrayList<Bean> mylist;
	public boolean selectAll = false;
	public static int callbak = 0;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_data);

		FindIds();
		setlisteners();

		dbHelper = new DatabaseHelper(this);
		mylist = new ArrayList<Bean>();

		context = ShowData.this;

		mylist = dbHelper.getAllEmployee();
		mAdp = new MyAdapter(ShowData.this, mylist);
		listView.setAdapter(mAdp);

	}

	private void FindIds() {
		listView = (ListView) findViewById(R.id.list);
		allDel_btn = (Button) findViewById(R.id.allDel_btn);
		specificDel_btn = (Button) findViewById(R.id.specificDel_btn);
		all_cb = (CheckBox) findViewById(R.id.all_cb);
	}

	private void setlisteners() {
		allDel_btn.setOnClickListener(this);
		specificDel_btn.setOnClickListener(this);
		all_cb.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

		mylist = dbHelper.getAllEmployee();
		mAdp = new MyAdapter(ShowData.this, mylist);
		listView.setAdapter(mAdp);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.allDel_btn:
			
			if (selectAll == true) {
				dbHelper.deleteAll(mylist);

				mylist = dbHelper.getAllEmployee();
				mAdp = new MyAdapter(ShowData.this, mylist);
				listView.setAdapter(mAdp);
			}
			
			else {
				Toast.makeText(context, "Please tick all checkbox",Toast.LENGTH_SHORT).show();
			}
		
			break;

		case R.id.specificDel_btn:	
				/*for (int j=0; j< mylist.size(); j++)
				{
					
					if (mylist.get(j).getStatus().equalsIgnoreCase("1"))
				     {
					         dbHelper.deleteParticularEmployee(mylist.get(j).getId());	
					         
					            mylist = dbHelper.getAllEmployee();
								mAdp = new MyAdapter(ShowData.this, mylist);
								listView.setAdapter(mAdp); 
				     }
				}
      		*/
			
			
			
			
			
			
	/*		SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
            int itemCount = listView.getCount();

            for(int i=itemCount-1; i >= 0; i++){
                if(checkedItemPositions.get(i))
                {
                	 dbHelper.deleteParticularEmployee(mylist.get(i).getId());	
                     mylist.remove(i);
                }
            }
            checkedItemPositions.clear();
            mAdp.notifyDataSetChanged();*/
                
           
			break;

		case R.id.all_cb:
			callbak = 5;

			if (selectAll) {
				for (Bean mobj : mylist) 
				{
					mobj.setStatus("0");
					mAdp.notifyData(mylist);
				}
				selectAll = false;
			} else
			{
				for (Bean mobj : mylist) 
				{
					mobj.setStatus("1");
					dbHelper.insertRecord(mobj);
					mAdp.notifyData(mylist);
				}
				selectAll = true;
			}
			break;

		default:
			break;
		}
	}
	
	public boolean getSelectAllvalue()
	{
		return selectAll;
	}
	
	public void checkSelecAll()
	{
		all_cb.setChecked(true);
		selectAll=true;
	}
	
	public void uncheckSelecAll()
	{
		all_cb.setChecked(false);
		selectAll=false;
	}

	
	@Override
	public void onBackPressed() {
		ShowData.this.finish();
	}

}