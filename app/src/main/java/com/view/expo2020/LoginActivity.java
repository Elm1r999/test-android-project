package com.view.expo2020;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.view.expo2020.Model.User;

public class LoginActivity extends AppCompatActivity {

    Animation frombottom, fromtop;
    Button btnSignIn, btnSignUp;
    TextView signInText;
    EditText signinEmail, signinUsername, signinPassword; //sign in details
    EditText newEmailText, newUsernameText, newPasswordText; //sign up details

    FirebaseDatabase firebaseDatabase;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        users = firebaseDatabase.getReference().child("Users");

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        signInText = findViewById(R.id.signinTextview);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        signinEmail = findViewById(R.id.emailText);
        signinUsername = findViewById(R.id.usernameText);
        signinPassword = findViewById(R.id.passwordText);

        btnSignIn.startAnimation(frombottom);
        btnSignUp.startAnimation(frombottom);
        signInText.startAnimation(fromtop);
        signinEmail.startAnimation(fromtop);
        signinUsername.startAnimation(fromtop);
        signinPassword.startAnimation(fromtop);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignUpDialog();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(signinEmail.getText().toString(), signinPassword.getText().toString());
            }
        });
    }

    private void signIn(final String user, final String passw0rd){
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user).exists()){

                    if(!user.isEmpty()){
                        User login = dataSnapshot.child(user).getValue(User.class);

                        assert login != null;
                        if (login.getPassword().equals(passw0rd)) {
                                Toast.makeText(LoginActivity.this, "Login successful! Signing in...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(LoginActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void showSignUpDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("Sign Up");
        alertDialog.setMessage("Please fill in your details");

        LayoutInflater inflater = this.getLayoutInflater();
        View signUp_layout = inflater.inflate(R.layout.activity_sign_up,null);

        newUsernameText = signUp_layout.findViewById(R.id.newUsernameText);
        newEmailText = signUp_layout.findViewById(R.id.newEmailText);
        newPasswordText = signUp_layout.findViewById(R.id.newPasswordText);

        alertDialog.setView(signUp_layout);
        alertDialog.setIcon(R.drawable.common_google_signin_btn_icon_dark);

        alertDialog.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final User user = new User(
                        newEmailText.getText().toString(),
                        newUsernameText.getText().toString(),
                        newPasswordText.getText().toString());
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUserName()).exists())  //  || dataSnapshot.child(user.getEmail()).exists())
                            Toast.makeText(LoginActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUserName()).setValue(user);
                            Toast.makeText(LoginActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                dialogInterface.dismiss();
            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
            alertDialog.show();
        }
}