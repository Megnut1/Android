package com.example.dnt_qlvt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtSearch;
    private Button btnSearch;
    private ListView listView;

    private DNT_Database database;
    private List<DNT_VATTU> vattuList;
    private DNT_Adapter adapterVT;
    private String idVattu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch= findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        listView = findViewById(R.id.lvQLVT);

        database = new DNT_Database(this);

        vattuList = database.getAll();


        setAdapter();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearch.getText().toString().equals("")){
                    vattuList.clear();
                    vattuList.addAll(database.getAll());
                    setAdapter();
                }
                else{
                    DNT_VATTU vattu = database.findById(edtSearch.getText().toString());
                    if(vattu !=null){
                        vattuList.clear();
                        vattuList.addAll(Arrays.asList(vattu));
                        setAdapter();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Không tìm thấy", Toast.LENGTH_LONG).show();
                }

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DNT_VATTU tp = vattuList.get(i);
                idVattu = tp.getMaMT();
            }
        });
        registerForContextMenu(listView);
    }

    private void setAdapter() {
        if(adapterVT ==null){
            adapterVT = new DNT_Adapter (this,R.layout.activity_dnt_list, vattuList );
            listView.setAdapter(adapterVT);
        }
        else{
            adapterVT.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuInsert:
                Intent intent= new Intent(MainActivity.this, DNT_Insert.class);
                startActivity(intent);
                break;
            case R.id.menuExit:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==listView){
            getMenuInflater().inflate(R.menu.menu_context,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuContextDelete:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                database.delete(idVattu);
                                vattuList.clear();
                                vattuList.addAll(database.getAll());
                                setAdapter();
                                Toast.makeText(MainActivity.this,"Xoá thành công",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case R.id.menuContextUpdate:
                Intent intent= new Intent(MainActivity.this, DNT_Update.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",idVattu);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}