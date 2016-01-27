package com.knox.chaz.tempcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import java.text.NumberFormat;

public class TempCalculaterActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private TextView headerTextView;
    private TextView fTextView;
    private TextView cTextView;
    private TextView resultTextView;
    private EditText inputEditText;

    private String inputText = "";
    private String fInfo = "";
    private String cInfo = "";

    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_calculater);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App created by Chaz Knox", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        // get refs to widgets
        inputEditText = (EditText) findViewById(R.id.inputEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        //create action listeners
        inputEditText.setOnEditorActionListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        calculateAndDisplay();
        return false;
    }

    private void calculateAndDisplay() {

        // do the math
        inputText = inputEditText.getText().toString();
        float input = 0;
        input = Float.parseFloat(inputText);
        float result = (input - 32) * 5/9;

        // send to display widgets
        NumberFormat degree = NumberFormat.getIntegerInstance();
        resultTextView.setText(degree.format(result));

        Context context = getApplicationContext();
        CharSequence text = "Celcuis is " + resultTextView.getText() + " degrees";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        // formula for conversion of fahrenheit to celcius c = (f - 32) * 5/9


    }
/*
    @Override
    protected void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("fInfo", fInfo);
        editor.putString("cInfo", cInfo);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        fInfo = savedValues.getString("fInfo", "");
        cInfo= savedValues.getString("cInfo", "");
        inputEditText.setText(fInfo);
        resultTextView.setText(cInfo);
        calculateAndDisplay();
    }
*/
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp_calculater, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
