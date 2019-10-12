package com.example.owner_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.owner_app.General.Storage.SharedPrefranceManager;
import com.example.owner_app.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup  extends AppCompatActivity {
    Spinner city;
    Button b_register;
    Intent i;
    EditText owner_name, owner_number, el_3nwan, ml_3b_name;
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseDatabase database;
    String Owner_city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        owner_name = (EditText) findViewById(R.id.owner_name);
        owner_number = (EditText) findViewById(R.id.owner_number);
        el_3nwan = (EditText) findViewById(R.id.el_3nwan);
        ml_3b_name = (EditText) findViewById(R.id.ml_3b_name);

        FirebaseApp.initializeApp(getBaseContext());
        auth = FirebaseAuth.getInstance();
        if(SharedPrefranceManager.getInastance(this).isLoggedIn()){
            startActivity(new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }

        //counter of duplictaion ml3b

        city = (Spinner) findViewById(R.id.cities);
        city.setSelection(0);

        final String str[] = new String[]{"اختر مدينتك", "الزقازيق", "العصلوجي", "بلبيس", "القنايات", "فاقوس", "هيهيا", "منيا القمح", "أبو حماد", "ميت غمر", "مدينة نصر", "سموحة"
                , "المرج", "الجيزة", "اسماعيلية", "حي الازبكية", "أسوان", "أسيوط"
        };

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str);
        city.setAdapter(adapter);

        b_register = (Button) findViewById(R.id.bu_reg);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Owner_city = str[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //كدا تمام .؟؟
        b_register.setOnClickListener(v -> {

            String Owner_name = owner_name.getText().toString();
            String Owner_number = owner_number.getText().toString();
            String El_3nwan = el_3nwan.getText().toString();
            String Ml_3b_name = ml_3b_name.getText().toString();


            if (Owner_name.isEmpty() || Owner_number.isEmpty() || El_3nwan.isEmpty() || Ml_3b_name.isEmpty()) {
                owner_name.setError("أدخل الإسم ...");
                owner_number.setError("أدخل رقم الهاتف ...");
                el_3nwan.setError("أدخل العنوان ...");
                ml_3b_name.setError("أدخل اسم الملعب ...");
            } else {
                i = new Intent(Signup.this, Verify_phone.class);
                i.putExtra("Ml_3b_name",Ml_3b_name);
                i.putExtra("Owner_number",Owner_number);
                i.putExtra("Owner_name",Owner_name);
                i.putExtra("El_3nwan",El_3nwan);
                i.putExtra("Owner_city",Owner_city);
                i.putExtra("phone",Owner_number);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }

        });

    }
}
