package com.example.editandroid;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.editandroid.adapter.MainActivity;
import com.example.editandroid.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
        EditText edtpass,edtemail,edtAddress;
        TextView tv_login;
        Button btn_re;
        private FirebaseAuth mAuth;
        private static final String TAG="";
        private final  static  int RC_SING_IN=111;

        @Override
        public void onStart() {
            super.onStart();
            // ktra nguoi dung dang nhap hay chua
          FirebaseUser currentUser = mAuth.getCurrentUser();
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup);
            addControls();
            addEvents();
        }
            private void addEvents(){
                btn_re.setOnClickListener(new View.OnClickListener() { //lắng nghe sự kiện click vào nút dangky
                    @Override
                    public void onClick(View view) {
                        String email_1 = edtemail.getText().toString().trim(); // lay du lieu o edittext
                        String pass_1 = edtpass.getText().toString().trim();
                        String address = edtAddress.getText().toString().trim();
                        Log.d("TAG", email_1);
                        if (TextUtils.isEmpty(email_1)) {
                            edtemail.setError("Require Input Email!!");
                            return;
                        }
                        if (TextUtils.isEmpty(pass_1)) {
                            edtpass.setError("Require Input Password!!");
                            return;
                        }
                        mAuth.createUserWithEmailAndPassword(email_1, pass_1)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            User user = new User(email_1,mAuth.getCurrentUser().getUid(),address);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference ref = database.getReference("Users");
                                            ref.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(SignUp.this, "Create User", LENGTH_SHORT).show(); // hien thi thong bao
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                }
                                            });

                                        } else {
                                            Toast.makeText(SignUp.this, "Error " + task.getException(), LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        tv_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(SignUp.this, SignIn.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }

        private void addControls() {
            edtpass= findViewById(R.id.pass1);
            edtemail= findViewById(R.id.email1);
            tv_login= findViewById(R.id.tv_login);
            btn_re= findViewById(R.id.button1);
            edtAddress = findViewById(R.id.address);
            mAuth=FirebaseAuth.getInstance();
      }

   }
