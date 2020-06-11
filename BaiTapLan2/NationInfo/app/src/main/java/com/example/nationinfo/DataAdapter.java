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

    private List<Country> countries;
    private LayoutInflater layoutInflater;
    private Context context;

    public DataAdapter(Context aContext, List<Country> countries) {
        this.context = aContext;
        this.countries = countries;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.country_item, null);
        TextView textView_countryName = convertView.findViewById(R.id.textView_countryName);

        final Country country = this.countries.get(position);
        textView_countryName.setText(country.getCountryName());

        textView_countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryInfo.class);
                intent.putExtra("Country Name", country.getCountryName());
                intent.putExtra("Country Code", country.getCountryCode());
                intent.putExtra("Population", country.getPopulation());
                intent.putExtra("Area", country.getAreaInSqKm());

                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
