package com.example.doitien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerSource;
    Spinner spinnerDestination;
    EditText txtInput;
    EditText txtOutput;

    List<Currency> titles;

    BigDecimal exchangeRate;

    int currentSourcePosition;
    int currentDestinationPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerSource = (Spinner) findViewById(R.id.spinnerSource);
        spinnerDestination = (Spinner) findViewById(R.id.spinnerDestination);
        txtInput = (EditText) findViewById(R.id.txtInput);
        txtOutput = (EditText) findViewById(R.id.txtOutput);

        titles = new ArrayList<Currency>();

        exchangeRate = BigDecimal.ZERO;
        currentSourcePosition = 0;
        currentDestinationPosition = 0;

        new ProcessGetData().execute();

        txtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                setTextChangedEvent();
            }
        });

    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public void setTextChangedEvent() {
        try {
            BigDecimal result = new BigDecimal(txtInput.getText().toString());
            result = result.multiply(exchangeRate);
            txtOutput.setText(NumberFormat.getNumberInstance(Locale.US).format(result));
        } catch (Exception e) {
            txtOutput.setText("");
        }
    }

    public class ProcessGetData extends AsyncTask<Integer, Void, Exception> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading data......");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... integers) {

            try {

                URL url = new URL("https://usd.fxexchangerate.com/rss.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                String str = xpp.nextText();
                                titles.add(new Currency(str.substring(str.indexOf("/") + 1, str.length()), str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")")).toLowerCase()));
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }

                    eventType = xpp.next();
                }

            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            spinnerSource.setAdapter(new DataAdapter(titles, getApplicationContext()));
            spinnerDestination.setAdapter(new DataAdapter(titles, getApplicationContext()));

            for (int i = 0; i < titles.size(); i++) {
                if (titles.get(i).getCurrencyName().equals("Vietnam Dong(VND)")) {
                    currentDestinationPosition = i;
                } else if (titles.get(i).getCurrencyName().equals("United States Dollar(USD)"))
                    currentSourcePosition = i;
            }

            spinnerSource.setSelection(currentSourcePosition);
            spinnerDestination.setSelection(currentDestinationPosition);

            spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == spinnerSource.getSelectedItemPosition()) {
                        spinnerSource.setSelection(currentDestinationPosition);
                    } else
                        new ProcessCalculate().execute();
                    currentDestinationPosition = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });

            spinnerSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == spinnerDestination.getSelectedItemPosition()) {
                        spinnerDestination.setSelection(currentSourcePosition);
                    } else
                        new ProcessCalculate().execute();

                    currentSourcePosition = position;

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });

            progressDialog.dismiss();
        }
    }

    public class ProcessCalculate extends AsyncTask<Integer, Void, Exception> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading....");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... integers) {

            try {
                URL url = new URL("https://" + titles.get(currentSourcePosition).getCurrencyCode() + ".fxexchangerate.com/" + titles.get(currentDestinationPosition).getCurrencyCode() + ".xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                String str = xpp.nextText();
                                str = str.substring(0, str.indexOf("<br/>"));
                                str = str.substring(str.indexOf("=") + 2, str.lastIndexOf(" "));
                                exchangeRate = new BigDecimal(str);
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }
                    eventType = xpp.next();
                }

            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            setTextChangedEvent();

            progressDialog.dismiss();
        }
    }

}
