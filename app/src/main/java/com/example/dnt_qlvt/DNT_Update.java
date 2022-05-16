package com.example.dnt_qlvt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DNT_Update extends AppCompatActivity {
    private EditText edtMa,edtName, edtLoai,edtNam,edtHang, edtDonGia,edtSl;
    private Button btnEdit, btnExit;
    private DNT_Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnt_update);
        database = new DNT_Database(this);
        edtMa = findViewById(R.id.edtMa);
        edtName = findViewById(R.id.edtTen);
        edtLoai = findViewById(R.id.edtLoai);
        edtNam = findViewById(R.id.edtNam);
        edtHang = findViewById(R.id.edtHsx);
        edtDonGia = findViewById(R.id.edtDg);
        edtSl = findViewById(R.id.edtSl);
        btnEdit = findViewById(R.id.btnEdit);
        btnExit = findViewById(R.id.btnExit);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        DNT_VATTU vattu = database.findById(id);
        if(vattu !=null){
            edtMa.setText(vattu.getMaMT());
            edtName.setText(vattu.getTenMt());
            edtLoai.setText(vattu.getLoaiMt());
            edtNam.setText(String.valueOf(vattu.getNamSx()));
            edtHang.setText(vattu.getHsx());
            edtDonGia.setText(String.valueOf(vattu.getDg()));
            edtSl.setText(String.valueOf(vattu.getSl()));
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DNT_VATTU vatTu = new DNT_VATTU(
                        id,
                        edtName.getText().toString(),
                        edtLoai.getText().toString(),

                        edtHang.getText().toString(),
                        Integer.parseInt(edtNam.getText().toString()),
                        Integer.parseInt(edtDonGia.getText().toString()),
                        Integer.parseInt(edtSl.getText().toString()));
                if(vatTu !=null) database.update(vatTu);
                Intent intent = new Intent(DNT_Update.this,MainActivity.class);
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