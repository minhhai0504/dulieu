package com.example.dulieu.ui.nguoidungfrag;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.example.dulieu.ui.category.CategoryFragment;
import com.example.dulieu.ui.category.CategoryModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private TextInputEditText edtUser3;
    private TextInputEditText edtpass;
    private TextInputEditText fullName2;
    private NhasachDatabase nhasachDatabase;
    private TextInputEditText numberPhone;

public static UserAdapter userAdapter;
    List<UserModel> list;
    Context context;


    public UserAdapter(List<UserModel> list, Context context, NhasachDatabase nhasachDatabase) {
        this.list = list;
        this.context = context;
        this.nhasachDatabase = nhasachDatabase;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {
        holder.Container(list.get(position));
        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Delete???");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteuser(position);
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
        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Sửa Người Dùng");

                View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edituser, null);
                builder.setView(dialog);
                edtUser3 = dialog.findViewById(R.id.edtUser3);
                edtpass = dialog.findViewById(R.id.edtpass);
                fullName2 = dialog.findViewById(R.id.fullName2);
                numberPhone = dialog.findViewById(R.id.number_phone);

                UserModel userModel = list.get(position);

                edtUser3.setText(userModel.getUsername());
                edtpass.setText(userModel.getPassword());
                fullName2.setText(userModel.getHoten());
                numberPhone.setText(userModel.getSdt());


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edituser();
                        NhasachDatabase nhasachDatabase1 = new NhasachDatabase(context);

                        list = nhasachDatabase1.accountList();
                        UserAdapter userAdapter = new UserAdapter(list, context, nhasachDatabase1);

                        UserFrag qLuser_fragment = new UserFrag();
                        qLuser_fragment.notifyAdapter();

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

    public class UserHolder extends RecyclerView.ViewHolder {
        public TextView tvname;
        public TextView tvcount;
        public ImageView icEdit;
        public ImageView icDelete;




        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvcount = itemView.findViewById(R.id.tvcount);
            icEdit = itemView.findViewById(R.id.ic_edit);
            icDelete = itemView.findViewById(R.id.ic_delete);
        }
        void Container (UserModel userModel){
            tvname.setText(userModel.getHoten());
            tvcount.setText(userModel.getSdt());
        }
    }
    private void deleteuser(int position) {
        nhasachDatabase.deletecAcount(list.get(position));
        list.remove(position);
        UserFrag bookFragment = new UserFrag();
        bookFragment.notifyAdapter();
    }
    public void edituser() {
        UserModel user = new UserModel();

        user.setUsername(edtUser3.getText().toString());
        user.setPassword(edtpass.getText().toString());
        user.setHoten(fullName2.getText().toString());
        user.setSdt(numberPhone.getText().toString());
        NhasachDatabase database = new NhasachDatabase(context);
        database.updateAccount(user);

    }

}
