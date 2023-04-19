package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText ed_name;
    private TextView tv_show;
    private Button btn_submit;
    private EditText ed_height;
    private EditText ed_weight;
    private RadioButton girl;
    private RadioButton boy;
    private TextView demo;
    private CheckBox apple;
    private Spinner steaks;
    private final String[] choose1 = {"蘋果","香蕉","橘子","西瓜","奇異果"};
    private ListView fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        showSex();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,choose1);
        fruit.setAdapter(adapter);
        steaks.setAdapter(adapter);
        //-------------------------------------------------------------------------------------------------------------------------------------------//
        fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_show.setText(choose1[position]);
            }
        });
        fruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_show.setText(fruit.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------//
    private void showSex() {
        String msg = "";
        if(boy.isChecked()) {
            msg += boy.getText().toString();
        }
        if(girl.isChecked()) {
            msg += girl.getText().toString();
        }
        if(apple.isChecked()) {
            msg += apple.getText().toString();
        }
        demo.setText(msg);
    }

    public void showSex(View view) {
        showSex();
    }

    @SuppressLint("SetTextI18n")
    public void submit(View view) {
        String name = ed_name.getText().toString();

        ed_name.setText("");
        double height =  Double.parseDouble(ed_height.getText().toString());
        double weight =  Double.parseDouble(ed_weight.getText().toString());
        double bmi = weight / ((height /100.0) * (height /100.0));

        tv_show.setText(name+ getString(R.string.welcome) + bmi);
    }

    private void findViews() {
        ed_name = findViewById(R.id.edName);
        tv_show = findViewById(R.id.tvShow);
        btn_submit = findViewById(R.id.btnSummit);
        ed_height = findViewById(R.id.edHeight);
        ed_weight = findViewById(R.id.edWeight);
        girl = findViewById(R.id.rbFemale);
        boy = findViewById(R.id.rbMale);
        demo = findViewById(R.id.tvdemo);
        apple = findViewById(R.id.cbApple);
        steaks = findViewById(R.id.spSteak);
        fruit = findViewById(R.id.lvFruit);
    }

    public void goResult(View view) {

        String name = ed_name.getText().toString();
        double height =  Double.parseDouble(ed_height.getText().toString());
        double weight =  Double.parseDouble(ed_weight.getText().toString());
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("keyname",name);
        bundle.putDouble("keyheight",height);
        bundle.putDouble("keyweight",weight);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}