package com.pxxpbmicalc.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText height;
    EditText weight;

    TextView bmi;
    TextView result1, result2, result3, result4, result5;
    Button calculate;
    Spinner  spinner1,spinner2;
    Double weightv,heightv,bmiv;
    ImageView img;
    int mode=1; //mode1=cm,kg mode2=cm,lb mode3=ft,cm mode4=ft,lb
    int spinner1menu=0,spinner2menu=0; //spinner1menu1=cm, spinner1menu2=ft, spinner2menu1=kg, spinner2menu2=lb
    int bmigraphimg[]={R.drawable.underweight,R.drawable.normal,R.drawable.overweight,R.drawable.obese,R.drawable.extremelyobese};
    int myblack, mygreen, mypurple;
    //MyApplication fontapply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //fontapply=(MyApplication)getApplicationContext();


        myblack = getResources().getColor(R.color.black1);
        mygreen = getResources().getColor(R.color.green);
        mypurple = getResources().getColor(R.color.purple);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        bmi = (TextView) findViewById(R.id.bmi);
        result1 = (TextView) findViewById(R.id.result1);
        result2 = (TextView) findViewById(R.id.result2);
        result3 = (TextView) findViewById(R.id.result3);
        result4 = (TextView) findViewById(R.id.result4);
        result5 = (TextView) findViewById(R.id.result5);
        calculate = (Button) findViewById(R.id.calculate);
        img = (ImageView)findViewById(R.id.bmigraph);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("cm");
        adapter.add("ft");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("kg");
        adapter2.add("lb");

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);


        weight.setText(""+"");
        height.setText(""+"");


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                ((TextView)parent.getChildAt(0)).setTextColor(myblack);
                parent.getBackground().setColorFilter(myblack, PorterDuff.Mode.SRC_ATOP);
                String item = (String) parent.getSelectedItem();
                Toast.makeText(MainActivity.this, item + " has been selected.", Toast.LENGTH_SHORT).show();
                if(item=="cm"){
                    spinner1menu=1;
                }else if(item=="ft"){
                    spinner1menu=2;
                }
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                ((TextView)parent.getChildAt(0)).setTextColor(myblack);
                parent.getBackground().setColorFilter(myblack, PorterDuff.Mode.SRC_ATOP);
                String item = (String) parent.getSelectedItem();
                Toast.makeText(MainActivity.this, item + " has been selected.", Toast.LENGTH_SHORT).show();
                if(item=="kg"){
                    spinner2menu=1;
                }else if(item=="lb"){
                    spinner2menu=2;
                }
            }
        });



        calculate.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {

                result1.setTextColor(mypurple);
                result2.setTextColor(mypurple);
                result3.setTextColor(mypurple);
                result4.setTextColor(mypurple);
                result5.setTextColor(mypurple);


                if(spinner1menu==1 && spinner2menu==1){
                    mode=1;
                }else if(spinner1menu==1 && spinner2menu==2){
                    mode=2;
                }else if(spinner1menu==2 && spinner2menu==1){
                    mode=3;
                }else if(spinner1menu==2 && spinner2menu==2){
                    mode=4;
                }
                //conversion cm,kg
                if(mode==1){
                    weightv=Double.parseDouble(weight.getText().toString());
                    heightv=Double.parseDouble(height.getText().toString());
                    bmiv=(weightv/((0.01*heightv)*(0.01*heightv)));
                    bmi.setText(String.format("%.1f",bmiv).toString());
                    bmicheck();
                }else if(mode==2){
                    weightv=Double.parseDouble(weight.getText().toString());
                    heightv=Double.parseDouble(height.getText().toString());
                    bmiv=((weightv*0.453592)/((0.01*heightv)*(0.01*heightv)));
                    bmi.setText(String.format("%.1f",bmiv).toString());
                    bmicheck();
                }else if(mode==3){
                    weightv=Double.parseDouble(weight.getText().toString());
                    heightv=Double.parseDouble(height.getText().toString());
                    bmiv=((weightv)/((0.01*heightv*30.48)*(0.01*heightv*30.48)));
                    bmi.setText(String.format("%.1f",bmiv).toString());
                    bmicheck();
                }else if(mode==4){
                    weightv=Double.parseDouble(weight.getText().toString());
                    heightv=Double.parseDouble(height.getText().toString());
                    bmiv=((weightv*0.453592)/((0.01*heightv*30.48)*(0.01*heightv*30.48)));
                    bmi.setText(String.format("%.1f",bmiv).toString());
                    bmicheck();
                }
            }
        });
    }
    public void bmicheck(){
        if(bmiv<18.5)
        {
            img.setImageResource(bmigraphimg[0]);
            result2.setTextColor(mypurple);
            result3.setTextColor(mypurple);
            result4.setTextColor(mypurple);
            result1.setTextColor(mygreen);
            result5.setTextColor(mypurple);
        }
        else if(bmiv>=18.5&&bmiv<=22.9)
        {
            img.setImageResource(bmigraphimg[1]);
            result2.setTextColor(mygreen);
            result1.setTextColor(mypurple);
            result3.setTextColor(mypurple);
            result4.setTextColor(mypurple);
            result5.setTextColor(mypurple);
        }
        else if(bmiv>=23&&bmiv<=24.9)
        {
            img.setImageResource(bmigraphimg[2]);
            result3.setTextColor(mygreen);
            result1.setTextColor(mypurple);
            result2.setTextColor(mypurple);
            result4.setTextColor(mypurple);
            result5.setTextColor(mypurple);
        }
        else if(bmiv>=25&&bmiv<=29.9)
        {
            img.setImageResource(bmigraphimg[3]);
            result4.setTextColor(mygreen);
            result1.setTextColor(mypurple);
            result2.setTextColor(mypurple);
            result3.setTextColor(mypurple);
            result5.setTextColor(mypurple);
        }
        else if(bmiv>30)
        {
            img.setImageResource(bmigraphimg[4]);
            result5.setTextColor(mygreen);
            result1.setTextColor(mypurple);
            result2.setTextColor(mypurple);
            result3.setTextColor(mypurple);
            result4.setTextColor(mypurple);
        }
    }
}
