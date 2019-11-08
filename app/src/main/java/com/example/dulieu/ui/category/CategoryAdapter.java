package com.example.dulieu.ui.category;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.example.dulieu.ui.nguoidungfrag.UserAdapter;
import com.example.dulieu.ui.nguoidungfrag.UserFrag;
import com.example.dulieu.ui.nguoidungfrag.UserModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private EditText edtmatheloai;
    private EditText edtname;
    private EditText edtmota;
    private EditText edtvitri;



public static CategoryAdapter categoryAdapter;
    List<CategoryModel> list;
    Context context;
    private NhasachDatabase nhasachDatabase;

    public CategoryAdapter(List<CategoryModel> list, Context context, NhasachDatabase nhasachDatabase) {
        this.list = list;
        this.context = context;
        this.nhasachDatabase = nhasachDatabase;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, final int position) {
        ((CategoryAdapter.CategoryHolder) holder).Container(list.get(position));
        holder.imgdelTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Delete???");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletecategory(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });

                builder.create().show();
            }
        });
        holder.imgdeditTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Sửa Thể Loại");

                View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_editcategory, null);
                builder.setView(dialog);

                edtmatheloai = dialog.findViewById(R.id.edtmatheloai);
                edtname = dialog.findViewById(R.id.edtname);
                edtmota = dialog.findViewById(R.id.edtmota);
                edtvitri = dialog.findViewById(R.id.edtvitri);

                CategoryModel categoryModel =list.get(position);

                edtmatheloai.setText(categoryModel.getMatheloai());
                edtname.setText(categoryModel.getTentheloai());
                edtmota.setText(categoryModel.getMatheloai());
                edtvitri.setText(categoryModel.getVitri());





                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editCategory();

                        NhasachDatabase nhasachDatabase1 = new NhasachDatabase(context);

                        list = nhasachDatabase1.dataSachListBook();
                        CategoryAdapter categoryAdapter = new CategoryAdapter(list,context,nhasachDatabase1);

                        CategoryFragment qLuser_fragment = new CategoryFragment();
                        qLuser_fragment.notifyAdapter1();

                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });



                builder.create().show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class CategoryHolder extends RecyclerView.ViewHolder{
        private TextView tvtheloaisach;
        private ImageView imgdelTheloai;
        private ImageView imgdeditTheloai;







        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            tvtheloaisach = itemView.findViewById(R.id.tvtheloaisach);
            imgdelTheloai = itemView.findViewById(R.id.imgdelTheloai);
            imgdeditTheloai = itemView.findViewById(R.id.imgdeditTheloai);
        }
        void Container (CategoryModel categoryModel){
            tvtheloaisach.setText(categoryModel.getTentheloai());
        }
    }
    private void deletecategory(int position) {
        nhasachDatabase.deletecCategory(list.get(position));
        list.remove(position);
        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.notifyAdapter1();
    }
    public void editCategory() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setMatheloai(edtmatheloai.getText().toString());
        categoryModel.setTentheloai(edtname.getText().toString());
        categoryModel.setMota(edtmota.getText().toString());
        categoryModel.setVitri(edtvitri.getText().toString());
        NhasachDatabase database = new NhasachDatabase(context);
        database.updatetheloai(categoryModel);

    }
}
