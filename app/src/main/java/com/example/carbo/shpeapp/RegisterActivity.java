/*
 * Created By: Carlos Fortoul, Carlos Arboleda, Piero Castillo, Sergio Perez
 *
 */
package com.example.carbo.shpeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import static com.example.carbo.shpeapp.R.id.majorSpinner;
import static com.example.carbo.shpeapp.R.id.yearSpinner;

public class RegisterActivity extends AppCompatActivity{
    ArrayAdapter<CharSequence> yearAdapter;
    ArrayAdapter<CharSequence> majorAdapter;
    String majorSelected;
    String academicSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        majorAdapter = ArrayAdapter.createFromResource(this, R.array.majors, android.R.layout.simple_spinner_item);
        yearAdapter  = ArrayAdapter.createFromResource(this, R.array.academicStandings, android.R.layout.simple_spinner_item);

        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CustomSpinnerAdapter mySpinner  = new CustomSpinnerAdapter(RegisterActivity.this, majorAdapter);
        CustomSpinnerAdapter mySpinner2 = new CustomSpinnerAdapter(RegisterActivity.this, yearAdapter);

        Spinner myMajorSpinner = (Spinner) findViewById(majorSpinner);
        Spinner myYearSpinner  = (Spinner) findViewById(yearSpinner);

        myMajorSpinner.setAdapter(mySpinner);
        myYearSpinner.setAdapter(mySpinner2);

        myMajorSpinner.setOnItemSelectedListener(myListener);
        myYearSpinner.setOnItemSelectedListener(myListener);
    }

    public void goToHomeScreen(View view) {
        Intent intent = new Intent(this, shpe.class);
        startActivity(intent);
    }
    OnItemSelectedListener myListener = new OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        // text.setText(parent.getItemAtPosition(position).toString());
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == majorSpinner) {
            majorSelected = parent.getItemAtPosition(position).toString();
            //This line is to test that is working
            Toast.makeText(parent.getContext(), "Major: " + majorSelected, Toast.LENGTH_SHORT).show();
            Log.d("major", "Major: " + majorSelected);
        } else if (spinner.getId() == yearSpinner) {
            academicSelected = parent.getItemAtPosition(position).toString();
            //This line is to test that is working
            Toast.makeText(parent.getContext(), "Year: " + academicSelected, Toast.LENGTH_SHORT).show();
            Log.d("year", "Year: " + academicSelected);
        }
    }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
};

    /*public String getText(AdapterView<?> spinner) {

        return spinner.getSelectedItem().toString();
    }*/




    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayAdapter<CharSequence> asr;

        public CustomSpinnerAdapter(Context context,ArrayAdapter<CharSequence> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.getCount();
        }

        public Object getItem(int i)
        {
            return asr.getItem(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
       public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(RegisterActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.getItem(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt =  new TextView (RegisterActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.getItem(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }
}



