package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo ListView.
        final ListView listView_Country = (ListView) findViewById(R.id.list_view);

        // Khởi tạo OkHttpClient để lấy dữ liệu.
        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là Country)
        Moshi moshi = new Moshi.Builder().build();
        Type countriesType = Types.newParameterizedType(List.class, Country.class);
        final JsonAdapter<List<Country>> jsonAdapter = moshi.adapter(countriesType);

        // Tạo request lên server.
        Request request = new Request.Builder()
                .url("http://api.geonames.org/countryInfoJSON?formatted=true&username=caoth")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                json = json.substring(13, json.length() - 1);
                final List<Country> countries = jsonAdapter.fromJson(json);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView_Country.setAdapter(new DataAdapter(MainActivity.this,countries));
                    }
                });
            }
        });

    }

}
