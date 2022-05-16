package com.example.dnt_qlvt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DNT_Insert extends AppCompatActivity {
    private EditText edtMa,edtName, edtLoai,edtNam,edtHang, edtDonGia,edtSl;
    private Button btnAdd, btnExit;
    private DNT_Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnt_insert);

        database = new DNT_Database(this);
        edtMa = findViewById(R.id.edtMa);
        edtName = findViewById(R.id.edtTen);
        edtLoai = findViewById(R.id.edtLoai);
        edtNam = findViewById(R.id.edtNam);
        edtHang = findViewById(R.id.edtHsx);
        edtDonGia = findViewById(R.id.edtDg);
        edtSl = findViewById(R.id.edtSl);
        btnAdd = findViewById(R.id.btnAdd);
        btnExit = findViewById(R.id.btnExit);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DNT_VATTU vatTu = new DNT_VATTU(
                        edtMa.getText().toString(),
                        edtName.getText().toString(),
                        edtLoai.getText().toString(),

                        edtHang.getText().toString(),
                        Integer.parseInt(edtNam.getText().toString()),
                        Integer.parseInt(edtDonGia.getText().toString()),
                        Integer.parseInt(edtSl.getText().toString()));
                if(vatTu !=null) database.insert(vatTu);
                Intent intent = new Intent(DNT_Insert.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}