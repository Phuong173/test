package com.example.thi_30_10;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTinTuc extends AsyncTask<String, Void, List<TinTuc>> {
    TrangChu mainActivity;
    List<TinTuc> list;
    DocBaoSevri_PH24891 docBaoService;

    public DownloadTinTuc(TrangChu mainActivity) {
        this.mainActivity = mainActivity;
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DocBaoSevri_PH24891.LocalBinder localBinder = (DocBaoSevri_PH24891.LocalBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected List<TinTuc> doInBackground(String... strings) {

        docBaoService = new DocBaoSevri_PH24891();
        Intent intent = new Intent(mainActivity,DocBaoSevri_PH24891.class);
        mainActivity.getBaseContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

        list = new ArrayList<TinTuc>();
        DocBaoSevri_PH24891 loader = new DocBaoSevri_PH24891();
        String urlRSS = strings[0];
        try{
            URL url = new URL(urlRSS);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                list = loader.getDocBaoList(inputStream);
            }


        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(List<TinTuc> tinTucs) {
        super.onPostExecute(tinTucs);

        ListView lv_tintuc = mainActivity.lv;
        TinTucAdapter adapter = new TinTucAdapter(lv_tintuc.getContext(), list);
        lv_tintuc.setAdapter(adapter);
//        long res =0;

//        TinTucDao dao = new TinTucDao(mainActivity);
//        if(dao.selectAll().size()<=0){
//            for (int i = tinTucs.size()-5;i<tinTucs.size();i++){
//                TinTuc tinTuc = new TinTuc(i,tinTucs.get(i).getTitle(),tinTucs.get(i).getLink(), tinTucs.get(i).getPubDate());
//                res = dao.addNew(tinTuc);
//            }
//        }
//        if(res>0){
//            Toast.makeText(mainActivity, "Thêm thành công", Toast.LENGTH_SHORT).show();
//        }
    }
}
