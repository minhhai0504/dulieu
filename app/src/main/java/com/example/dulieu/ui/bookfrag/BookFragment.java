package com.example.dulieu.ui.bookfrag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {
    private EditText edtmasasch;
    private EditText edttheloai;
    private EditText edttensasch;
    private EditText edtsoluong;
    private EditText edttacgia;
    private EditText edtgia;





    private FloatingActionButton floatingActionButton;
    private RecyclerView rccView;
    public static BookAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NhasachDatabase bookDatabaseHelper;
    private List<BookModel> booklist;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag_book, container, false);

        booklist = new ArrayList<>();

        floatingActionButton = root.findViewById(R.id.btnAdd);

        rccView = root.findViewById(R.id.rccView);

        layoutManager = new LinearLayoutManager(getContext());
        rccView.setLayoutManager(layoutManager);

        bookDatabaseHelper = new NhasachDatabase(getActivity());

        bookDatabaseHelper.createDataBase();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                builder.setTitle("Thêm Sách");

                final View dialog = LayoutInflater.from(getContext()).inflate(R.layout.book_dialog, null);

                builder.setView(dialog);
                edtmasasch = dialog.findViewById(R.id.edtmasasch);
                edttheloai = dialog.findViewById(R.id.edttheloai);
                edttensasch = dialog.findViewById(R.id.edttensasch);
                edtsoluong = dialog.findViewById(R.id.edtsoluong);
                edttacgia = dialog.findViewById(R.id.edttacgia);
                edtgia = dialog.findViewById(R.id.edtgia);

                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addBook();

                        booklist = new ArrayList<>();
                        booklist = bookDatabaseHelper.dataSachList();
                        adapter = new BookAdapter(booklist,getContext(),bookDatabaseHelper);
                        rccView.setAdapter(adapter);
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
        booklist.clear();
        booklist = bookDatabaseHelper.dataSachList();
        adapter = new BookAdapter(booklist, getContext(),bookDatabaseHelper);
        rccView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void addBook(){
        BookModel userModel = new BookModel();

        userModel.setMasach(edtmasasch.getText().toString());
        userModel.setMatheloai(edttheloai.getText().toString());
        userModel.setTensach(edttensasch.getText().toString());
        userModel.setSoluong(edtsoluong.getText().toString());
        userModel.setTacgia(edttacgia.getText().toString());
        userModel.setGia(edtgia.getText().toString());

        NhasachDatabase nhasachDatabase = new NhasachDatabase(getContext());
        nhasachDatabase.themBook(userModel);
    }


    public void notifyAdapterBook() {
        adapter.notifyDataSetChanged();
    }


}