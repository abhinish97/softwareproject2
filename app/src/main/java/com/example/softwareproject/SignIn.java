package com.example.softwareproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softwareproject.Common.Common;
import com.example.softwareproject.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText phone,password;
    Button btnsignin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        phone = (MaterialEditText)findViewById(R.id.edtPhone);
        password=(MaterialEditText)findViewById(R.id.edtPassword);
        btnsignin2 = (Button)findViewById(R.id.signin2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnsignin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait.....");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone.getText().toString()).exists()) {
                            mDialog.dismiss();

                            User user = dataSnapshot.child(phone.getText().toString()).getValue(User.class);

                            if (user.getPassword().equals(password.getText().toString())) {
                                Toast.makeText(SignIn.this, "SignIn successfully", Toast.LENGTH_SHORT).show();
                                Intent homeIntent= new Intent(SignIn.this,Home.class);
                                Common.current_user = user;
                                startActivity(homeIntent);
                                finish();

                            }
                            else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "SignIn fail", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"User not exist in database",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });




    }
}
