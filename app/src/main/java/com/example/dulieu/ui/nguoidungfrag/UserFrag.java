package com.example.dulieu.ui.nguoidungfrag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.example.dulieu.ui.category.CategoryAdapter;
import com.example.dulieu.ui.category.CategoryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class UserFrag extends Fragment {
    private RecyclerView rccView;
    private FloatingActionButton fab;
    private RecyclerView.LayoutManager layoutManager;
    private NhasachDatabase bookDatabaseHelper;
    private List<UserModel> accountList;
    public static UserAdapter accountAdapter;

    private TextInputEditText TIedtuser;
    private TextInputEditText TIedtpass;
    private TextInputEditText TIedthoten;
    private TextInputEditText TIedtsdt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_user, container, false);
        accountList = new ArrayList<>();
        rccView = root.findViewById(R.id.rccView);
        fab = root.findViewById(R.id.fab);
        layoutManager = new LinearLayoutManager(getContext());
        rccView.setLayoutManager(layoutManager);
        bookDatabaseHelper = new NhasachDatabase(getActivity());
        bookDatabaseHelper.createDataBase();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thêm Người Dùng");

                final View dialog = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, null);

                builder.setView(dialog);

                TIedtuser = dialog.findViewById(R.id.TIedtuser);
                TIedtpass = dialog.findViewById(R.id.TIedtpass);
                TIedthoten = dialog.findViewById(R.id.TIedthoten);
                TIedtsdt = dialog.findViewById(R.id.TIedtsdt);


                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addUser();

                        accountList = new ArrayList<>();
                        accountList = bookDatabaseHelper.accountList();
                        accountAdapter = new UserAdapter(accountList,getContext(),bookDatabaseHelper);
                        rccView.setAdapter(accountAdapter);
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
        loaddatauser();
        return root;
    }
    private void loaddatauser() {
        accountList.clear();
        accountList = bookDatabaseHelper.accountList();
        accountAdapter = new UserAdapter(accountList,getContext(),bookDatabaseHelper);
        rccView.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
    }
    public void addUser(){
        UserModel userModel = new UserModel();

        userModel.setUsername(TIedtuser.getText().toString());
        userModel.setPassword(TIedtpass.getText().toString());
        userModel.setHoten(TIedthoten.getText().toString());
        userModel.setSdt(TIedtsdt.getText().toString());

        NhasachDatabase nhasachDatabase = new NhasachDatabase(getContext());
        nhasachDatabase.themUser(userModel);
    }


    public void notifyAdapter() {
        accountAdapter.notifyDataSetChanged();
    }


}
