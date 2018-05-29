package com.stust.demojay0529;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private View button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("member/m1/name");
    DatabaseReference myStudent = database.getReference("students");
    private View button2;
    private EditText editText1;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue("王金平");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student s3 = new student();
                s3.name="陳曉東3";
                s3.age=35;
                myStudent.child("s3").setValue(s3);

                student s4 = new student();
                s4.name="陳柏彰4";
                s4.age=55;
                myStudent.child("s4").setValue(s4);

                student s5 = new student();
                s5.name = editText1.getText().toString();
                s5.age =Integer.parseInt( editText2.getText().toString());

                String k =  myStudent.push().getKey();
                myStudent.child(k).setValue(s5);
            }
        });
    }
}
