package com.example.dulieu.ui.billfrag.billct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulieu.NhasachDatabase;
import com.example.dulieu.R;

import java.util.List;

public class BillCtAdapter extends RecyclerView.Adapter<BillCtAdapter.BillCTHolder> {
    private Context context;
    private List<BillCTModel> billCTModels;
    private NhasachDatabase nhasachDatabase;

    public BillCtAdapter(Context context, List<BillCTModel> billCTModels) {
        this.context = context;
        this.billCTModels = billCTModels;
        nhasachDatabase=new NhasachDatabase(context);
        nhasachDatabase.createDataBase();
    }

    @NonNull
    @Override
    public BillCTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_billct, parent, false);

        return new BillCTHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillCTHolder holder, final int position) {
        holder.tvMaBillCT.setText(billCTModels.get(position).getMaHoaDonChiTiet());
        holder.tvSoLuong.setText(billCTModels.get(position).getSoLuongMua());
        holder.tvTieuDe.setText(billCTModels.get(position).getTieuDe());
        holder.tvTongTien.setText(billCTModels.get(position).getTongTien() + " VNĐ");

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhasachDatabase.deleteBillCT(billCTModels.get(position) );
                billCTModels.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return billCTModels.size();
    }

    public class BillCTHolder extends RecyclerView.ViewHolder {
        private TextView tvMaBillCT;
        private TextView tvTieuDe;
        private TextView tvSoLuong;
        private ImageView xoa;

        private TextView tvTongTien;


        public BillCTHolder(@NonNull View itemView) {
            super(itemView);
            tvMaBillCT = (TextView) itemView.findViewById(R.id.tvMaBillCT);
            tvTieuDe = (TextView) itemView.findViewById(R.id.tvTieuDe);
            xoa =   itemView.findViewById(R.id.xoa);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tvSoLuong);
            tvTongTien = (TextView) itemView.findViewById(R.id.tvTongTien);

        }
    }
}
