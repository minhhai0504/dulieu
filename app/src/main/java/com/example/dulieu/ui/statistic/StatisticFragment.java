package com.example.dulieu.ui.statistic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StatisticFragment extends Fragment {
    private TextView tvhomnay;
    private TextView tvtuannay;
    private TextView tvthangnay;
    private NhasachDatabase nhasachDatabase;







    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag_statistic, container, false);
        tvhomnay = (TextView) root.findViewById(R.id.edttenbill);
        tvtuannay = (TextView) root.findViewById(R.id.edtngaymua);
        tvthangnay = (TextView) root.findViewById(R.id.edtmasach);
        nhasachDatabase=new NhasachDatabase(getContext());
        nhasachDatabase.createDataBase();

        tvhomnay.setText(nhasachDatabase.thongKeNgay(layNgayHn()));
        tvthangnay.setText(nhasachDatabase.thongKeTuan(layThangNay(),layNgayHn()));
        tvtuannay.setText(nhasachDatabase.thongKeTuan(layTuanNay(),layNgayHn()));


        return root;
    }

    private long layNgayHn( ){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        long now=System.currentTimeMillis();
        String ngayhn=simpleDateFormat.format(now);
        try {
            return simpleDateFormat.parse(ngayhn).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private long layTuanNay( ){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("w/yyyy");
        long now=System.currentTimeMillis();
        String ngayhn=simpleDateFormat.format(now);
        try {
            return simpleDateFormat.parse(ngayhn).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private long layThangNay( ){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/yyyy");
        long now=System.currentTimeMillis();
        String ngayhn=simpleDateFormat.format(now);
        try {
            return simpleDateFormat.parse(ngayhn).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}