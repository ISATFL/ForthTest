package com.example.forthtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit=findViewById(R.id.submit);
        Button donload=findViewById(R.id.donload);
        final EditText name=findViewById(R.id.name);
        final EditText number=findViewById(R.id.number);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try{
                    FileOutputStream fileOutputStream=openFileOutput("TestFile",MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String name_1=name.getText().toString();
                    String number_1=number.getText().toString();
                    try{
                        out.write((name_1+"##"+number_1).getBytes());
                        Toast.makeText(MainActivity.this,"提交成功",Toast.LENGTH_LONG).show();
                    }finally {
                        if(out!=null){
                            out.close();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        donload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try{
                    FileInputStream fileInputStream=openFileInput("TestFile");
                    in=new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while((c=in.read())!=-1){
                            stringBuilder.append((char)c);
                        }
                        String text=stringBuilder.toString();
                        String [] t=text.split("##");
                        name.setText(t[0]);
                        number.setText(t[1]);
                    }finally {
                        if(in!=null){
                            in.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
