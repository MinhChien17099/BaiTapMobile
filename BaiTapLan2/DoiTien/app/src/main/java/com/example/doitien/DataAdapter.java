package com.example.doitien;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends BaseAdapter {

    private List<Currency> currencies;
    private Context context;
    private LayoutInflater inflater;

    public DataAdapter(List<Currency> currencies, Context context) {
        this.currencies = currencies;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return currencies.size();
    }

    @Override
    public Object getItem(int position) {
        return currencies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.spinner_item, null);
        TextView tvItem=(TextView)convertView.findViewById(R.id.tvItem);
        tvItem.setText(currencies.get(position).getCurrencyName());
        return convertView;
    }

}
