package com.example.dnt_qlvt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DNT_Adapter extends ArrayAdapter<DNT_VATTU> {
    private Context context;
    private int resource;
    private List<DNT_VATTU> vattuList;
    public DNT_Adapter( Context context, int resource, List<DNT_VATTU> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.vattuList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_dnt_list,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tvMa = (TextView) convertView.findViewById(R.id.tvMa);
            viewHodel.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHodel.tvLoai = (TextView) convertView.findViewById(R.id.tvLoai);
            viewHodel.tvNam = (TextView) convertView.findViewById(R.id.tvNam);
            viewHodel.tvHang = (TextView) convertView.findViewById(R.id.tvHang);
            viewHodel.tvDg = (TextView) convertView.findViewById(R.id.tvDg);
            viewHodel.tvSl = (TextView) convertView.findViewById(R.id.tvSl);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
            DNT_VATTU vattu = vattuList.get(position);

            viewHodel.tvMa.setText("Mã: "+vattu.getMaMT());
            viewHodel.tvName.setText("Tên: "+vattu.getTenMt());
            viewHodel.tvLoai.setText("Loại: "+vattu.getLoaiMt());
            viewHodel.tvNam.setText(String.valueOf("Năm: "+vattu.getNamSx()));
            viewHodel.tvHang.setText("Hãng: "+vattu.getHsx());
            viewHodel.tvDg.setText(String.valueOf("Đơn giá: "+vattu.getDg()));
            viewHodel.tvSl.setText(String.valueOf("Số lượng: "+vattu.getSl()));
        return convertView;
    }

    class ViewHodel{
        private TextView tvMa, tvName,tvLoai,tvNam,tvHang,tvDg,tvSl;
    }
}
