package com.example.studen;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog.Builder;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {


    MyData db;
    private EditText stt,name,diem;
    private Button save,delete,update;
    List<hocsinh> name1;
    private ListView l;
    Cursor cu;
    Arrayapdater array;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stt=(EditText)findViewById(R.id.txtstt);
        name=(EditText)findViewById(R.id.txtname);
        diem=(EditText)findViewById(R.id.txtdiem);
        save=(Button)findViewById(R.id.btnadd);
        delete=(Button)findViewById(R.id.btnxoa);
        update=(Button)findViewById(R.id.btnsua);
       l=(ListView)findViewById(R.id.listView);
        db=new MyData(this);
    	db.open();
    	load();
      delete.setOnClickListener(click);
      update.setOnClickListener(click);
      l.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			hocsinh hs=array.getItem(arg2);
			String stt1=hs.getStt();
			String ten1=hs.getName();
			String diem1=hs.getDiem();
			stt.setText(stt1);
			name.setText(ten1);
			diem.setText(diem1);
		}
	});
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.v("STT", stt.getText().toString());
				Log.v("NAME", name.getText().toString());
				Log.v("DIEM", diem.getText().toString());
				 	db.open();
				 	
					
			     db.Insert(stt.getText().toString(),name.getText().toString(),diem.getText().toString());
			      // Log.v("n",n+"");
				load();
			        db.close();
			}
		});
      
    }
    
    OnClickListener click=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==delete){
				db.open();
		
				db.delete(stt.getText().toString());
				load();
			}
			if(arg0==update){
				db.open();
				db.Update(stt.getText().toString(),name.getText().toString(),diem.getText().toString());
				load();
			}
		}
	};
public void load(){
	db.open();
	cu = db.getAllhs();
	// startManagingCursor(mCursor);
	// mCursor.moveToFirst();
	name1 = new ArrayList<hocsinh>();


	StringBuffer bu=new StringBuffer();
	while (cu.moveToNext()) {
		hocsinh nct = new  hocsinh();

		nct.setStt(cu.getString(cu
				.getColumnIndex("Stt")));
		nct.setName(cu.getString(cu
				.getColumnIndex("Name")));
		nct.setDiem(cu.getString(cu
				.getColumnIndex("Diem")));
		
		
		name1.add(nct);
		
		bu.append(cu.getString(0)+"\n");
		bu.append(cu.getString(1)+"\n");
		bu.append(cu.getString(2)+"\n\n");
	}
	showMessage("Student Details", bu.toString());

	cu.close();
	 array= new Arrayapdater(MainActivity.this,R.layout.item,name1);
	
   l.setAdapter(array);
}
public void showMessage(String title,String message)
{
   Builder builder=new Builder(this);
   builder.setCancelable(true);
   builder.setTitle(title);
   builder.setMessage(message);
   builder.show();
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
