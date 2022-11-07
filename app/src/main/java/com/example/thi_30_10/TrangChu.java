package com.example.thi_30_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TrangChu extends AppCompatActivity {
    Button save, show;
    public ListView lv;
    List<TinTuc> list = new ArrayList<>();
    TinTucDao dao = new TinTucDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        save = findViewById(R.id.btn_save);
        show = findViewById(R.id.btn_show);
        lv = findViewById(R.id.lv_tintuc);
        String urlRss = "https://vnexpress.net/rss/so-hoa.rss";
        DownloadTinTuc downloadTinTuc = new DownloadTinTuc(TrangChu.this);
        downloadTinTuc.execute(urlRss);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),DocBaoSevri_PH24891.class);
                startService(intent);
                list = dao.selectAll();
                long res =0;

                TinTucDao dao = new TinTucDao(getBaseContext());
                if(dao.selectAll().size()<=0){
                    for (int i = list.size()-5;i<list.size();i++){
                        TinTuc tinTuc = new TinTuc(i,list.get(i).getTitle(),list.get(i).getLink(), list.get(i).getPubDate());
                        res = dao.addNew(tinTuc);
                    }
                }
                if(res>0){
                    Toast.makeText(getBaseContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
//                downloadTinTuc.onPostExecute(list);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TrangChu.this, "số lượng bài viết "+list.size(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(),DocBaoSevri_PH24891.class);
                stopService(intent);
            }
        });
    }
}