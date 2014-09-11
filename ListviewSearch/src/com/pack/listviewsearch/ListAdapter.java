package com.pack.listviewsearch;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter implements Filterable {

	private LayoutInflater inflater;
	private ValueFilter valueFilter;
	private ArrayList<String> array;
	private ArrayList<String> list ;
	
	public ListAdapter(LayoutInflater inflater,ArrayList<String> array) {
		super();
		this.inflater=inflater;
		this.array=array;
		this.list = new ArrayList<String>(array);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return array.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return array.get(position).toString();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return list.indexOf(getItem(position));
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView==null) {
			convertView=(View)inflater.inflate(android.R.layout.simple_list_item_1, null);
		}
		
		TextView text = (TextView)convertView.findViewById(android.R.id.text1);
		text.setText(array.get(position).toString());
		
		return convertView;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if(valueFilter==null)
		{
			valueFilter =new ValueFilter();
		}
		return valueFilter;
	}
	
	private class ValueFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
		FilterResults filterResults = new FilterResults();
		if(constraint==null||constraint.length()==0) {
			
			filterResults.values=list;
			filterResults.count=list.size();
		}
		else{
			ArrayList<String> newValues=new ArrayList<String>();
			for(int i=0; i<array.size();i++) {
			
				String item = array.get(i);
                if(item.contains(constraint)) {
                    newValues.add(item);  
                }
                
            }
            filterResults.values = newValues;
            filterResults.count = newValues.size();
				
			}
			
			return filterResults;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			array =(ArrayList<String>) results.values;
			notifyDataSetChanged();
			
		}
		
	}

}
