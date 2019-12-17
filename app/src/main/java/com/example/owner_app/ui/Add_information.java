package com.example.owner_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.owner_app.General.FirebaseKeys;
import com.example.owner_app.Notification.Exampleservice;
import com.example.owner_app.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class Add_information extends AppCompatActivity {
    CheckBox one,two,three,four,five,six,seven,eight,nine,ten,tewelve,twenteen,one_plus,two_plus;
    Button ok;
    String one_st,two_st,three_st,four_st,five_st,six_st,seven_st,eight_st,nine_st,ten_st,tewelve_st,twenteen_st,one_plus_st,two_plus_st;
    EditText etd1,etd2,etd3,etd4,etd5,etd6,etd7,etd8,etd9,etd10,etd11,etd12,etd13,etd14;
    String etd1_st,etd2_st,etd3_st,etd4_st,etd5_st,etd6_st,etd7_st,etd8_st,etd9_st,etd10_st,etd11_st,etd12_st,etd13_st,etd14_st;
    FirebaseAuth auth;
    private DatabaseReference reference,df;
    FirebaseDatabase database;
    int count = -1;
    String Ml_3b_name,Owner_number,Owner_name,El_3nwan,Owner_city,ownerDeviceToken;
    Intent i;
    int counter=0;
    String[] arr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_information);


        ok=(Button)findViewById(R.id.bu_ok);

        one=(CheckBox)findViewById(R.id.checkBox1);
        two=(CheckBox)findViewById(R.id.checkBox2);
        three=(CheckBox)findViewById(R.id.checkBox3);
        four=(CheckBox)findViewById(R.id.checkBox4);
        five=(CheckBox)findViewById(R.id.checkBox5);
        six=(CheckBox)findViewById(R.id.checkBox6);
        seven=(CheckBox)findViewById(R.id.checkBox7);
        eight=(CheckBox)findViewById(R.id.checkBox8);
        nine=(CheckBox)findViewById(R.id.checkBox9);
        ten=(CheckBox)findViewById(R.id.checkBox10);
        tewelve=(CheckBox)findViewById(R.id.checkBox11);
        twenteen=(CheckBox)findViewById(R.id.checkBox12);
        one_plus=(CheckBox)findViewById(R.id.checkBox13);
        two_plus=(CheckBox)findViewById(R.id.checkBox14);

        etd1=(EditText)findViewById(R.id.etd1);
        etd2=(EditText)findViewById(R.id.etd2);
        etd3=(EditText)findViewById(R.id.etd3);
        etd4=(EditText)findViewById(R.id.etd4);
        etd5 =(EditText)findViewById(R.id.etd5);
        etd6=(EditText)findViewById(R.id.etd6);
        etd7=(EditText)findViewById(R.id.etd7);
        etd8=(EditText)findViewById(R.id.etd8);
        etd9=(EditText)findViewById(R.id.etd9);
        etd10=(EditText)findViewById(R.id.etd10);
        etd11=(EditText)findViewById(R.id.etd11);
        etd12=(EditText)findViewById(R.id.etd12);
        etd13=(EditText)findViewById(R.id.etd13);
        etd14=(EditText)findViewById(R.id.etd14);


        etd1_st=etd1.getText().toString();
        etd2_st=etd2.getText().toString();
        etd3_st=etd3.getText().toString();
        etd4_st=etd4.getText().toString();
        etd5_st=etd5.getText().toString();
        etd6_st=etd6.getText().toString();
        etd7_st=etd7.getText().toString();
        etd8_st=etd8.getText().toString();
        etd9_st=etd9.getText().toString();
        etd10_st=etd10.getText().toString();
        etd11_st=etd11.getText().toString();
        etd12_st=etd12.getText().toString();
        etd13_st=etd13.getText().toString();
        etd14_st=etd14.getText().toString();

        FirebaseApp.initializeApp(getBaseContext());
        auth = FirebaseAuth.getInstance();

        i=getIntent();
        Ml_3b_name=i.getStringExtra("Ml_3b_name");
        Owner_number=i.getStringExtra("Owner_number");
        Owner_name=i.getStringExtra("Owner_name");
        El_3nwan=i.getStringExtra("El_3nwan");
        Owner_city=i.getStringExtra("Owner_city");


        arr=new String [15];
        onclicks();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                database = FirebaseDatabase.getInstance();
                reference = database.getReference().child(FirebaseKeys.areas.toString()).child(Owner_number);

                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put(FirebaseKeys.area_name.toString(), Ml_3b_name);
                hashMap2.put(FirebaseKeys.owner_phone.toString(), Owner_number);
                hashMap2.put(FirebaseKeys.owner_name.toString(), Owner_name);
                hashMap2.put(FirebaseKeys.area_address.toString(), El_3nwan);
                hashMap2.put(FirebaseKeys.area_city.toString(), Owner_city);
                hashMap2.put("hours_available", String.valueOf(counter));

                etd1_st=etd1.getText().toString();
                etd2_st=etd2.getText().toString();
                etd3_st=etd3.getText().toString();
                etd4_st=etd4.getText().toString();
                etd5_st=etd5.getText().toString();
                etd6_st=etd6.getText().toString();
                etd7_st=etd7.getText().toString();
                etd8_st=etd8.getText().toString();
                etd9_st=etd9.getText().toString();
                etd10_st=etd10.getText().toString();
                etd11_st=etd11.getText().toString();
                etd12_st=etd12.getText().toString();
                etd13_st=etd13.getText().toString();
                etd14_st=etd14.getText().toString();

                df=FirebaseDatabase.getInstance().getReference();


                for (int x=1;x<=arr.length;x++){
                    if (x==1){one_st=arr[1];
                        hashMap2.put("first_hour",one_st);
                        hashMap2.put("price_1",etd1_st);
                       }
                    if (x==2){two_st=arr[2];
                        hashMap2.put("second_hour",two_st);
                        hashMap2.put("price_2",etd2_st);
                        }
                    if (x==3){three_st=arr[3];
                        hashMap2.put("third_hour",three_st);
                        hashMap2.put("price_3",etd3_st);
                        }
                    if (x==4){four_st=arr[4];
                        hashMap2.put("fourth",four_st);
                        hashMap2.put("price_4",etd4_st);
                        }
                    if (x==5){five_st=arr[5];
                        hashMap2.put("fifth",five_st);
                        hashMap2.put("price_5",etd5_st);
                        }
                    if (x==6){six_st=arr[6];
                        hashMap2.put("six",six_st);
                        hashMap2.put("price_6",etd6_st);
                        }
                    if (x==7){seven_st=arr[7];
                        hashMap2.put("seven",seven_st);
                        hashMap2.put("price_7",etd7_st);
                        }
                    if (x==8){eight_st=arr[8];
                        hashMap2.put("eight",eight_st);
                        hashMap2.put("price_8",etd8_st);
                        }
                    if (x==9){nine_st=arr[9];
                        hashMap2.put("nine",nine_st);
                        hashMap2.put("price_9",etd9_st);
                        }
                    if (x == 10) {ten_st=arr[10];
                        hashMap2.put("ten",ten_st);
                        hashMap2.put("price_10",etd10_st);
                       }
                    if (x==11){tewelve_st=arr[11];
                        hashMap2.put("tewelve",tewelve_st);
                        hashMap2.put("price_11",etd11_st);
                        }
                    if (x==12){twenteen_st=arr[12];
                        hashMap2.put("twenteen",twenteen_st);
                        hashMap2.put("price_12",etd12_st);
                        }
                    if (x==13){one_plus_st=arr[13];
                        hashMap2.put("one_Am",one_plus_st);
                        hashMap2.put("price_13",etd13_st);
                        }
                    if (x==14){two_plus_st=arr[14];
                        hashMap2.put("two_Am",two_plus_st);
                        hashMap2.put("price_14",etd14_st);
                        mm(two_plus_st);
                        }


                    if (one.isChecked()){
                        hashMap2.put("price_1",etd1_st);
                        hashMap2.put(one_st,"false");
                        mm(one_st);
                    }
                    if (two.isChecked()){
                        hashMap2.put("price_2",etd2_st);
                        hashMap2.put(two_st,"false");
                        mm(two_st);
                    }
                    if (three.isChecked()){
                        hashMap2.put("price_3",etd3_st);
                        hashMap2.put(three_st,"false");
                        mm(three_st);
                    }
                    if (four.isChecked()){
                        hashMap2.put("price_4",etd4_st);
                        hashMap2.put(four_st,"false");
                        mm(four_st);
                    }
                    if (five.isChecked()){
                        hashMap2.put("price_5",etd5_st);
                        hashMap2.put(five_st,"false");
                        mm(five_st);
                    }
                    if (six.isChecked()){
                        hashMap2.put("price_6",etd6_st);
                        hashMap2.put(six_st,"false");
                        mm(six_st);
                    }
                    if (seven.isChecked()){
                        hashMap2.put("price_7",etd7_st);
                        hashMap2.put(seven_st,"false");
                        mm(seven_st);
                    }
                    if (eight.isChecked()){
                        hashMap2.put("price_8",etd8_st);
                        hashMap2.put(eight_st,"false");
                        mm(eight_st);
                    }
                    if (nine.isChecked()){
                        hashMap2.put("price_9",etd9_st);
                        hashMap2.put(nine_st,"false");
                        mm(nine_st);
                    }
                    if (ten.isChecked()){
                        hashMap2.put("price_10",etd10_st);
                        hashMap2.put(ten_st,"false");
                        mm(ten_st);
                    }
                    if (tewelve.isChecked()){
                        hashMap2.put("price_11",etd11_st);
                        hashMap2.put(tewelve_st,"false");
                        mm(tewelve_st);
                    }
                    if (twenteen.isChecked()){
                        hashMap2.put("price_12",etd12_st);
                        hashMap2.put(twenteen_st,"false");
                        mm(twenteen_st);
                    }
                    if (one_plus.isChecked()){
                        hashMap2.put("price_13",etd13_st);
                        hashMap2.put(one_plus_st,"false");
                        mm(one_plus_st);
                    }
                    if (two_plus.isChecked()){
                        hashMap2.put("price_14",etd14_st);
                        hashMap2.put(two_plus_st,"false");
                        mm(two_plus_st);
                    }
                }
                ownerDeviceToken= FirebaseInstanceId.getInstance().getToken();
                hashMap2.put("ownerDeviceToken",ownerDeviceToken);
                reference.setValue(hashMap2);

                Log.e("Token",ownerDeviceToken);
                Intent intent=new Intent(Add_information.this,MainActivity.class);
                intent.putExtra("area_name",Ml_3b_name);
                intent.putExtra("Owner_city",Owner_city);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
public void mm(String time){
        df=FirebaseDatabase.getInstance().getReference();
        df.child(FirebaseKeys.areas.toString()).child(Owner_number).child(time);
        df.setValue("false");
}

public void Time_to_ch(){
        DatabaseReference rf;
        FirebaseDatabase db;
        FirebaseApp.initializeApp(getBaseContext());
        rf=FirebaseDatabase.getInstance().getReference().child("areas_check_time").child(Ml_3b_name);
        HashMap<String,String> hs=new HashMap<>();
        hs.put(one_st,"false");
        hs.put(two_st,"false");
        hs.put(three_st,"false");
        hs.put(four_st,"false");
        hs.put(five_st,"false");
        hs.put(six_st,"false");
        hs.put(seven_st,"false");
        hs.put(eight_st,"false");
        hs.put(nine_st,"false");
        hs.put(ten_st,"false");
        hs.put(tewelve_st,"false");
        hs.put(twenteen_st,"false");
        hs.put(one_plus_st,"false");
        hs.put(two_plus_st,"false");
        rf.setValue(hs);
}

    public void onclicks(){
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (one.isChecked()){
                    one_st="01:00 pm";
                    counter=counter+1;
                    arr[1]=one_st;
                }else
                {counter=counter-1;}
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (two.isChecked()){
                    two_st="02:00 pm";
                    counter=counter+1;
                    arr[2]=two_st;

                }else
                {counter=counter-1;}
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (three.isChecked()){
                    three_st="03:00 pm";
                    counter=counter+1;
                    arr[3]=three_st;
                }else
                {counter=counter-1;}
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (four.isChecked()){
                    four_st="04:00 pm";
                    counter=counter+1;
                    arr[4]=four_st;
                }else
                {counter=counter-1;}
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (five.isChecked()){
                    five_st="05:00 pm";
                    counter=counter+1;
                    arr[5]=five_st;
                }else
                {counter=counter-1;}
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (six.isChecked()){
                    six_st="06:00 pm";
                    counter=counter+1;
                    arr[6]=six_st;
                }else
                {counter=counter-1;}
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seven.isChecked()){
                    seven_st="07:00 pm";
                    counter=counter+1;
                    arr[7]=seven_st;
                }else
                {counter=counter-1;}
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eight.isChecked()){
                    eight_st="08:00 pm";
                    counter=counter+1;
                    arr[8]=eight_st;
                }else
                {counter=counter-1;}
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nine.isChecked()){
                    nine_st="09:00 pm";
                    counter=counter+1;
                    arr[9]=nine_st;
                }else
                {counter=counter-1;}
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ten.isChecked()){
                    ten_st="10:00 pm";
                    counter=counter+1;
                    arr[10]=ten_st;
                }else
                {counter=counter-1;}
            }
        });

        tewelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tewelve.isChecked()){
                    tewelve_st="11:00 pm";
                    counter=counter+1;
                    arr[11]=tewelve_st;
                }else
                {counter=counter-1;}
            }
        });

        twenteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twenteen.isChecked()){
                    twenteen_st="12:00 am";
                    counter=counter+1;
                    arr[12]=twenteen_st;
                }else
                {counter=counter-1;}
            }
        });

        one_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (one_plus.isChecked()){
                    one_plus_st="01:00 am";
                    counter=counter+1;
                    arr[13]=one_plus_st;
                }else
                {counter=counter-1;}
            }
        });

        two_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (two_plus.isChecked()) {
                    two_plus_st = "02:00 am";
                    counter=counter+1;
                    arr[14]=two_plus_st;
                }else
                {counter=counter-1;}
            }
        });

    }

}

