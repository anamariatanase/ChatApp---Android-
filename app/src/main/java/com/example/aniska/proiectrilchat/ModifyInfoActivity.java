package com.example.aniska.proiectrilchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aniska.proiectrilchat.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModifyInfoActivity extends AppCompatActivity {
    EditText send_currentLastName;
    EditText send_newLastName;

    EditText send_newFirstName;
    EditText send_currentFirstName;

    EditText send_currentEmail;
    EditText send_newEmail;

    EditText send_currentPassword;
    EditText send_newPassword;

    Button btn_changeFirstName;
    Button btn_changeLastName;
    Button btn_changePassword;
    Button btn_changeEmail;

    DatabaseReference reference;
    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifyInfoActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        send_currentLastName = findViewById(R.id.send_currentlastname);
        send_newLastName = findViewById(R.id.send_newlastname);
        send_currentFirstName = findViewById(R.id.send_currentfirstname);
        send_newFirstName = findViewById(R.id.send_newfirstname);
        send_currentEmail = findViewById(R.id.send_currentemail);
        send_newEmail = findViewById(R.id.send_newemail);
        send_newPassword = findViewById(R.id.send_newpassword);
        send_currentPassword = findViewById(R.id.send_currentpassword);

        btn_changeEmail = findViewById(R.id.btn_changeEmail);
        btn_changeFirstName = findViewById(R.id.btn_changeFirstName);
        btn_changeLastName = findViewById(R.id.btn_changeLastName);
        btn_changePassword = findViewById(R.id.btn_changePassword);
        btn_changeFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String currentFirstName = send_currentFirstName.getText().toString();
                        String newFirstName = send_newFirstName.getText().toString();
                        User user = dataSnapshot.getValue(User.class);
                        String ceva = user.getFirstname();
                        if (TextUtils.isEmpty(currentFirstName) || TextUtils.isEmpty(newFirstName)) {
                            Toast.makeText(ModifyInfoActivity.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (ceva.equals(currentFirstName)) {
                                reference.child("firstname").setValue(newFirstName);
                                finish();
                            } else {
                                Toast.makeText(ModifyInfoActivity.this, "Wrong current First Name", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btn_changeLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String currentLastName = send_currentLastName.getText().toString();
                        String newLastName = send_newLastName.getText().toString();
                        User user = dataSnapshot.getValue(User.class);
                        String ceva = user.getLastname();
                        if (TextUtils.isEmpty(currentLastName) || TextUtils.isEmpty(newLastName)) {
                            Toast.makeText(ModifyInfoActivity.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (ceva.equals(currentLastName)) {
                                reference.child("lastname").setValue(newLastName);
                                finish();
                            } else {
                                Toast.makeText(ModifyInfoActivity.this, "Wrong current Last Name", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String currentPassword = send_currentPassword.getText().toString();
                        String newPassword = send_newPassword.getText().toString();
                        User user = dataSnapshot.getValue(User.class);
                        String ceva = user.getPassword();
                        if (TextUtils.isEmpty(currentPassword) || TextUtils.isEmpty(newPassword)) {
                            Toast.makeText(ModifyInfoActivity.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (ceva.equals(currentPassword)) {
                                reference.child("password").setValue(newPassword);
                                user1.updatePassword(newPassword);
                                finish();
                            } else {
                                Toast.makeText(ModifyInfoActivity.this, "Wrong current Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btn_changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String currentEmail = send_currentEmail.getText().toString();
                        String newEmail = send_newEmail.getText().toString();
                        User user = dataSnapshot.getValue(User.class);
                        String ceva = user.getEmail();
                        if (TextUtils.isEmpty(currentEmail) || TextUtils.isEmpty(newEmail)) {
                            Toast.makeText(ModifyInfoActivity.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (ceva.equals(currentEmail)) {
                                reference.child("email").setValue(newEmail);
                                user1.updateEmail(newEmail);
                                finish();
                            } else {
                                Toast.makeText(ModifyInfoActivity.this, "Wrong current Email address", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private String encryptData(String talk, int k) {
        // make the string encrypted before sending to the database

        k = k % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : talk.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + k) % 26));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + k) % 26));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    private String decryptData(String m, int key) {
        // make string readable on the receiver's device
        return encryptData(m, 26 - key);
    }
}