package com.pack.listviewsearch;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	
	private ListView list;
	private EditText searchview;
	private ArrayList<String> array=
			new ArrayList<String>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"));
	private LayoutInflater inflater;
	private ListAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        list =(ListView)findViewById(R.id.list);
        searchview =(EditText)findViewById(R.id.search);
        inflater =LayoutInflater.from(this);
        adapter = new ListAdapter(inflater, array);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        
        searchview.addTextChangedListener(new TextWatcher() {

        	@Override
        	public void onTextChanged(CharSequence s, int start, int before, int count) {		
        		adapter.getFilter().filter(s);
        			 
        	}

        	@Override
        	public void beforeTextChanged(CharSequence s, int start, int count,
        			int after) {
        		
        	}

        	@Override
        	public void afterTextChanged(Editable s) {
        		adapter.getFilter().filter(s);
        	}
        	});
     
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		Toast.makeText(this, ""+adapter.getItemId(position), Toast.LENGTH_LONG).show();
		
	}
}
