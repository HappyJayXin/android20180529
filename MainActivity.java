package com.stust.demojay0529;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private View button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("");

    DatabaseReference oMap = database.getReference("map");

    private View button2;
    private EditText editText1;
    private EditText editText2,editText3,editText4,editText5;
    private TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);//修改新增
        button2=findViewById(R.id.button2);//刪除
        editText1=(EditText)findViewById(R.id.editText1);//key
        editText2=(EditText)findViewById(R.id.editText2);//名稱
        editText3=(EditText)findViewById(R.id.editText3);//說明
        editText4=(EditText)findViewById(R.id.editText4);//經度
        editText5=(EditText)findViewById(R.id.editText5);//緯度
        showData=(TextView)findViewById(R.id.showData);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key= editText1.getText().toString();
                Map map = new Map();
                map.name=editText2.getText().toString();
                map.detail=editText3.getText().toString();
                map.lat=parseDouble(editText4.getText().toString());
                map.lon=parseDouble(editText5.getText().toString());

                oMap.child(key).setValue(map);

//                String k =  oMap.push().getKey();
//                myStudent.child(k).setValue(s5);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new Map();
                map.key = editText1.getText().toString();
                oMap.child(map.key).removeValue();
            }
        });


        oMap.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                showData.setText("");

                for(DataSnapshot studSnapshot : dataSnapshot.getChildren()){
                    String key = studSnapshot.getKey(); //如何取得Key
                    Map m = studSnapshot.getValue(Map.class); //取得資料
                    String str = m.key + ", " + m.name + ", " +m.detail+", "+m.lat+", "+m.lon+ "\n";
                    showData.append(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
