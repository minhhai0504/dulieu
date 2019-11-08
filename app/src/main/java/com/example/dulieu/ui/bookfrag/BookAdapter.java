package com.example.dulieu.ui.bookfrag;

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
import com.example.dulieu.ui.nguoidungfrag.UserAdapter;
import com.example.dulieu.ui.nguoidungfrag.UserFrag;
import com.example.dulieu.ui.nguoidungfrag.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.Itembook> {
    private TextInputEditText edtupdateUser;
    private TextInputEditText edtupdatePass;
    private TextInputEditText edtupdateTensach;
    private TextInputEditText edtupdateSoluong;
    private TextInputEditText edtupdateTacgia;
    private TextInputEditText edtupdategia;

public  static BookAdapter bookAdapter;
    List<BookModel> list;
    Context context;
    private NhasachDatabase nhasachDatabase;

    public BookAdapter(List<BookModel> list, Context context, NhasachDatabase nhasachDatabase) {
        this.list = list;
        this.context = context;
        this.nhasachDatabase = nhasachDatabase;
    }

    @NonNull
    @Override
    public Itembook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new Itembook(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Itembook holder, final int position) {
        ((BookAdapter.Itembook) holder).Container(list.get(position));
        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Delete???");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletebook(position);
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
                builder.setTitle("Sửa Sách");

                View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_editbook, null);
                builder.setView(dialog);

                edtupdateUser = dialog.findViewById(R.id.edtupdateUser);
                edtupdatePass = dialog.findViewById(R.id.edtupdatePass);
                edtupdateTensach = dialog.findViewById(R.id.edtupdateTensach);
                edtupdateSoluong = dialog.findViewById(R.id.edtupdateSoluong);
                edtupdateTacgia = dialog.findViewById(R.id.edtupdateTacgia);
                edtupdategia = dialog.findViewById(R.id.edtupdategia);

                BookModel bookModel = list.get(position);

                edtupdateUser.setText(bookModel.getMasach());
                edtupdatePass.setText(bookModel.getMatheloai());
                edtupdateTensach.setText(bookModel.getTensach());
                edtupdateSoluong.setText(bookModel.getSoluong());
                edtupdateTacgia.setText(bookModel.getTacgia());
                edtupdategia.setText(bookModel.getGia());

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editbook();

                        NhasachDatabase nhasachDatabase1 = new NhasachDatabase(context);

                        list = nhasachDatabase1.dataSachList();
                        BookAdapter userAdapter = new BookAdapter(list, context, nhasachDatabase1);

                        BookFragment qLuser_fragment = new BookFragment();
                        qLuser_fragment.notifyAdapterBook();

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

    public class Itembook extends RecyclerView.ViewHolder {
        private ImageView icEdit;
        private ImageView icDelete;
        private TextView tv_name;
        private TextView tv_phone;
        private TextView tvtacgia;
        private TextView tvgia;

        public Itembook(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tvname);
            tv_phone = itemView.findViewById(R.id.tvcount);
            tvtacgia = itemView.findViewById(R.id.tvtacgia);
            tvgia = itemView.findViewById(R.id.tvgia);
            icEdit = itemView.findViewById(R.id.ic_edit);
            icDelete = itemView.findViewById(R.id.ic_delete);

        }

        void Container(BookModel modelBook) {
            tv_name.setText(modelBook.getTensach());
            tv_phone.setText(modelBook.getSoluong());
            tvtacgia.setText(modelBook.getTacgia());
            tvgia.setText(modelBook.getGia());
        }
    }

    private void deletebook(int position) {
        nhasachDatabase.deletecBook(list.get(position));
        list.remove(position);
        BookFragment bookFragment = new BookFragment();
        bookFragment.notifyAdapterBook();
    }

    public void editbook() {
        BookModel bookModel = new BookModel();

        bookModel.setMasach(edtupdateUser.getText().toString());
        bookModel.setMatheloai(edtupdatePass.getText().toString());
        bookModel.setTensach(edtupdateTensach.getText().toString());
        bookModel.setSoluong(edtupdateSoluong.getText().toString());
        bookModel.setTacgia(edtupdateTacgia.getText().toString());
        bookModel.setGia(edtupdategia.getText().toString());
        NhasachDatabase database = new NhasachDatabase(context);
        database.updateBook(bookModel);

    }

}
