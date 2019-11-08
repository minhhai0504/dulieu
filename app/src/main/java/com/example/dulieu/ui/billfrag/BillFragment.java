package com.example.dulieu.ui.billfrag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BillFragment extends Fragment {
    private EditText edttenbill;
    private EditText edtngaymua;
    private EditText edtmasach;
    private EditText edtsoluong;



    private List<BillModel>  billModelList;
    public static BillAdapter billAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private NhasachDatabase nhasachDatabase;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag_bill, container, false);
        billModelList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);
        billAdapter = new BillAdapter(billModelList,getContext(),nhasachDatabase);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(billAdapter);
        nhasachDatabase = new NhasachDatabase(getActivity());
        nhasachDatabase.createDataBase();
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thêm Hóa Đơn");

                final View dialog = LayoutInflater.from(getContext()).inflate(R.layout.addbill_dialog, null);

                builder.setView(dialog);
                edttenbill = dialog.findViewById(R.id.edttenbill);
                edtngaymua = dialog.findViewById(R.id.edtngaymua);
                edtmasach = dialog.findViewById(R.id.edtmasach);
                edtsoluong = dialog.findViewById(R.id.edtsoluong);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addBill();

                        billModelList = new ArrayList<>();
                        billModelList = nhasachDatabase.dataSachListHoaDon();
                        billAdapter = new BillAdapter(billModelList,getContext(),nhasachDatabase);
                        recyclerView.setAdapter(billAdapter);
                        Toast.makeText(getContext(), "Add thanh cong", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });

                builder.create().show();
            }
        });
        loaddatasach();


        return root;
    }
    private void loaddatasach() {
        billModelList.clear();
        billModelList = nhasachDatabase.dataSachListHoaDon();
        billAdapter = new BillAdapter(billModelList,getContext(),nhasachDatabase);
        recyclerView.setAdapter(billAdapter);
        billAdapter.notifyDataSetChanged();
    }
    public void addBill(){
        BillModel billModel = new BillModel();

        billModel.setMahoadon(edttenbill.getText().toString());
        billModel.setNgay(edtngaymua.getText().toString());



        NhasachDatabase nhasachDatabase = new NhasachDatabase(getContext());
        nhasachDatabase.themBill(billModel);
    }

    public void notifyAdapter() {
        billAdapter.notifyDataSetChanged();
    }
}