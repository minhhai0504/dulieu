package com.example.dulieu.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dulieu.R;

public class SettingFragment extends Fragment {
    private ImageView imageInfor;
    private ImageView imageChange;
    private ImageView imageLogout;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag_setting, container, false);

        imageInfor = root.findViewById(R.id.img_set_User);
        imageChange = root.findViewById(R.id.imgsetPass);
        imageLogout = root.findViewById(R.id.imgdangxuat);

        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        imageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
                builder.setTitle("Đổi mật khẩu");

                View dialog = LayoutInflater.from(getContext()).inflate(R.layout.change_pass,null);

                builder.setView(dialog);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });

                builder.create().show();
            }
        });
        return root;
    }
}