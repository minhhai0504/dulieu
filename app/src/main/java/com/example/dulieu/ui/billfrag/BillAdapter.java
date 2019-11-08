package com.example.dulieu.ui.billfrag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.dulieu.ui.billfrag.billct.XemBillCT;
import com.example.dulieu.ui.bookfrag.BookAdapter;
import com.example.dulieu.ui.bookfrag.BookFragment;
import com.example.dulieu.ui.bookfrag.BookModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillHolder> {
    private EditText edttenbill;
    private EditText edtngaymua;
    private EditText edtmasach;
    private EditText edtsoluong;


    private List<BillModel> billModelList;
    private Context context;
    private NhasachDatabase nhasachDatabase;

    public BillAdapter(List<BillModel> billModelList, Context context, NhasachDatabase nhasachDatabase) {
        this.billModelList = billModelList;
        this.context = context;
        this.nhasachDatabase = nhasachDatabase;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill, parent, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, final int position) {
        ((BillAdapter.BillHolder) holder).Container(billModelList.get(position));
        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Delete???");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletebill(position);
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


        //bắt sự kiện chuyển màn xem ct
        holder.imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, XemBillCT.class);
                intent.putExtra("bill",billModelList.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return billModelList.size();
    }

    public class BillHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private ImageView icUser;
        private ImageView icCall;
        private TextView tvcodebill;
        private TextView tvbillday;
        private ImageView icDelete;


        public BillHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            icUser = itemView.findViewById(R.id.ic_user);
            icCall = itemView.findViewById(R.id.ic_call);
            tvcodebill = itemView.findViewById(R.id.tvcodebill);
            tvbillday = itemView.findViewById(R.id.tvbillday);
            icDelete = itemView.findViewById(R.id.ic_delete);
        }

        void Container(BillModel billModel) {
            tvcodebill.setText(billModel.getMahoadon());
            tvbillday.setText(convertDate(Long.parseLong(billModel.getNgay())));
        }
    }

    private void deletebill(int position) {
        nhasachDatabase.deletecBill(billModelList.get(position));
        billModelList.remove(position);
        BillFragment bookFragment = new BillFragment();
        bookFragment.notifyAdapter();
    }

    public void editbill() {
        BillModel billModel = new BillModel();

        billModel.setMahoadon(edttenbill.getText().toString());
        billModel.setNgay(edtngaymua.getText().toString());
        NhasachDatabase database = new NhasachDatabase(context);
        database.updateBill(billModel);

    }

    private String convertDate(long time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(time);
    }
}
