package com.example.thi_30_10;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TinTucAdapter extends BaseAdapter {
    Context context;
    List<TinTuc> list = new ArrayList<>();

    public TinTucAdapter(Context context, List<TinTuc> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i).getTitle();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(viewGroup.getContext() ,R.layout.item_tintuc,null);
        }else {
            row = view;
        }
        TinTuc tinTuc = list.get(i);
        int index = i;
        TextView txt_tieude = row.findViewById(R.id.txt_tieude);
        TextView txt_ngay = row.findViewById(R.id.txt_ngay);
        txt_tieude.setText("PH2491 tiêu đề: " + tinTuc.getTitle());

        txt_ngay.setText("PH2491 ngày: "+ tinTuc.getPubDate());
//        ImageView img_tt = row.findViewById(R.id.img_tintuc);
//
//        Glide.with(context).load(tinTuc.getDescription()).into(img_tt);
        txt_tieude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(context, WebView.class);
              i.putExtra("linktt",list.get(index).getLink());
              context.startActivity(i);
            }
        });

        return row;
    }
}
