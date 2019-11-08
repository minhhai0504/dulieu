package com.example.dulieu.ui.billfrag.billct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.example.dulieu.ui.billfrag.BillAdapter;
import com.example.dulieu.ui.billfrag.BillModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class XemBillCT extends AppCompatActivity {
    private FloatingActionButton fab;

    private BillModel billModel;
    private AlertDialog alertDialog;
    private RecyclerView recyclerView;
    private List<BillCTModel> billCTModels;
    private BillCtAdapter billCtAdapter;
    private NhasachDatabase nhasachDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_bill_ct);
        recyclerView = findViewById(R.id.rclvBillCT);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        billModel = (BillModel) getIntent().getSerializableExtra("bill");

        Log.e("AA",billModel.getMahoadon());

        nhasachDatabase = new NhasachDatabase(this);
        nhasachDatabase.createDataBase();
        billCTModels = nhasachDatabase.dataSachListHoaDonCT(billModel.getMahoadon());
        Log.e("AAAAAAAAAA",billCTModels.size()+"");
        billCtAdapter = new BillCtAdapter(this, billCTModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(billCtAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(XemBillCT.this);
                builder.setTitle("Thêm Hóa Đơn Chi tiết");

                final View dialog = LayoutInflater.from(XemBillCT.this).inflate(R.layout.aldidid, null);

                builder.setView(dialog);

                final EditText masach;
                final EditText sl;

                masach = (EditText) dialog.findViewById(R.id.masach);
                sl = (EditText) dialog.findViewById(R.id.sl);


                builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nhasachDatabase.themBillCT(new BillCTModel(masach.getText().toString()+"", "", sl.getText().toString()+"", "", billModel.getMahoadon(), ""));
                        reloadAC();
                    }
                });

                builder.create();
                alertDialog = builder.show();
                builder.setCancelable(false);
            }
        });

    }

    private void reloadAC() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
