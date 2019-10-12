package com.example.owner_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.owner_app.General.Storage.SharedPrefranceManager;
import com.example.owner_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Verify_phone  extends AppCompatActivity {


    Button verify;
    private FirebaseAuth mAuth;
    private String verificationID;
    EditText verifyCode;
    Intent i;
    private ProgressBar progressBar;
    Context context;
    DatabaseReference reference;
    FirebaseDatabase database;
    String userid;
    String Ml_3b_name,Owner_number,Owner_name,El_3nwan,Owner_city;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_phone);


        verify=(Button)findViewById(R.id.buverify);
        verifyCode=(EditText)findViewById(R.id.verifycode);
        progressBar=(ProgressBar)findViewById(R.id.progbar);

        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();

        i=getIntent();
        Ml_3b_name=i.getStringExtra("Ml_3b_name");
        Owner_number=i.getStringExtra("Owner_number");
        Owner_name=i.getStringExtra("Owner_name");
        El_3nwan=i.getStringExtra("El_3nwan");
        Owner_city=i.getStringExtra("Owner_city");

        final String phone=getIntent().getStringExtra("phone");
        sendVerificationCode(phone);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ccode=verifyCode.getText().toString();
                if (ccode.isEmpty() || ccode.length()<6){
                    verifyCode.setError("أدخل الكود ...");
                    verifyCode.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                verifycode(ccode,Owner_name,phone);

            }
        });

    }

    private void verifycode(String code,String Onwer_name,String phone){
        try { PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationID,code);
            signWithCredential(credential,Onwer_name,phone);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }
    private void signWithCredential(PhoneAuthCredential credential,final String Onwer_name, final String phone) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Owners_Verification").child(userid);
                            HashMap<String, String> hashMap = new HashMap<>();

                            hashMap.put("user_name", Onwer_name);
                            hashMap.put("phone_no",phone);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {


                                        i = new Intent(Verify_phone.this, Add_information.class);
                                        i.putExtra("Ml_3b_name",Ml_3b_name);
                                        i.putExtra("Owner_number",Owner_number);
                                        i.putExtra("Owner_name",Owner_name);
                                        i.putExtra("El_3nwan",El_3nwan);
                                        i.putExtra("Owner_city",Owner_city);
                                        i.putExtra("phone",Owner_number);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();

                                        SharedPrefranceManager.getInastance(context).saveUser(Onwer_name,Owner_number,Ml_3b_name,Owner_city);
                                    }
                                }

                            });

                        } else {
                            Toast.makeText(Verify_phone.this, "You can not register with this email & password !!", Toast.LENGTH_SHORT).show();
                        }


                    }

                });
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationID=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String codesms=phoneAuthCredential.getSmsCode();
            if (codesms !=null){
                progressBar.setVisibility(View.VISIBLE);
                verifycode(codesms,null,null);
            }


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verify_phone.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
