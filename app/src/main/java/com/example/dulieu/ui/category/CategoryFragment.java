package com.example.dulieu.ui.category;

import android.annotation.SuppressLint;
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

public class CategoryFragment extends Fragment {
    private EditText edtmatheloai;
    private EditText edtname;
    private EditText edtmota;
    private EditText edtvitri;





    private RecyclerView recyclerView2;
    private FloatingActionButton floatingActionButton2;
    private List<CategoryModel> categoryModelList;
    public static CategoryAdapter categoryAdapter;
    private NhasachDatabase nhasachDatabase;
    private RecyclerView.LayoutManager layoutManager;






    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag_category, container, false);
        recyclerView2 = root.findViewById(R.id.recyclerView2);
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryModelList,getContext(),nhasachDatabase);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(categoryAdapter);
        nhasachDatabase = new NhasachDatabase(getContext());
        nhasachDatabase.createDataBase();

        floatingActionButton2 = root.findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                builder.setTitle("Thêm thể loại");

                final View dialog = LayoutInflater.from(getContext()).inflate(R.layout.addcategory_dialog, null);

                builder.setView(dialog);
                edtmatheloai = dialog.findViewById(R.id.edtmatheloai);
                edtname = dialog.findViewById(R.id.edtname);
                edtmota = dialog.findViewById(R.id.edtmota);
                edtvitri = dialog.findViewById(R.id.edtvitri);

                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addcategory();
                        categoryModelList = new ArrayList<>();
                        categoryModelList = nhasachDatabase.dataSachListBook();
                        categoryAdapter = new CategoryAdapter(categoryModelList,getContext(),nhasachDatabase);
                        recyclerView2.setAdapter(categoryAdapter);
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
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
        categoryModelList.clear();
        categoryModelList = nhasachDatabase.dataSachListBook();
        categoryAdapter = new CategoryAdapter(categoryModelList,getContext(),nhasachDatabase);
        recyclerView2.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }
    public void addcategory(){
        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setMatheloai(edtmatheloai.getText().toString());
        categoryModel.setTentheloai(edtname.getText().toString());
        categoryModel.setMota(edtmota.getText().toString());
        categoryModel.setVitri(edtvitri.getText().toString());

        NhasachDatabase nhasachDatabase = new NhasachDatabase(getContext());
        nhasachDatabase.themtheloai(categoryModel);
    }


    public void notifyAdapter1() {
        categoryAdapter.notifyDataSetChanged();
    }
}