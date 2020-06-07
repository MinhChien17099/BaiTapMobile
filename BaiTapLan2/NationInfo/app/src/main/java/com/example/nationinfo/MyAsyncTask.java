package com.example.nationinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyAsyncTask extends android.os.AsyncTask<Void, Void, Void> {

    List<Country> countryList = new ArrayList<Country>();
    private String TAG = "";
    ProgressDialog progressDialog;

    ListView listView;
    Context context;

    public MyAsyncTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
        progressDialog = new ProgressDialog(this.context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setMessage("Loading .......");
        progressDialog.show();
    }

    protected Void doInBackground(Void... voids) {

        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://api.geonames.org/countryInfoJSON?formatted=true&username=caoth";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray countries = jsonObj.getJSONArray("geonames");

                // looping through All Contacts
                for (int i = 0; i < countries.length(); i++) {
                    JSONObject c = countries.getJSONObject(i);
                    String countryCode = c.getString("countryCode");
                    String countryName = c.getString("countryName");
                    String areaInSqKm = c.getString("areaInSqKm");
                    String population = c.getString("population");
                    Country country = new Country(countryCode, countryName, areaInSqKm, population);
                    // adding contact to contact list
                    countryList.add(country);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        listView.setAdapter(new DataAdapter(context, countryList));

        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }


}
