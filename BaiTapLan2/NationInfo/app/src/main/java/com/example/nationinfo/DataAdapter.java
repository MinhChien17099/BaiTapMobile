package com.example.nationinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends BaseAdapter {

    private List<Country> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public DataAdapter(Context aContext, List<Country> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.country_item, null);
            holder = new ViewHolder();
            holder.textView_countryName = (TextView) convertView.findViewById(R.id.textView_countryName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Country country = this.listData.get(position);
        holder.textView_countryName.setText(country.getCountryName());
        holder.textView_countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CountryInfo.class);
                intent.putExtra("Country Name",country.getCountryName());
                intent.putExtra("Country Code",country.getCountryCode());
                intent.putExtra("Population",country.getPopulation());
                intent.putExtra("Area",country.getAreaInSqKm());

                context.startActivity(intent);
            }
        });

        return convertView;
    }


    static class ViewHolder {
        TextView textView_countryName;
    }

}
