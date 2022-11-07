package com.example.thi_30_10;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DocBaoSevri_PH24891 extends Service {
    List<TinTuc> listDocBao = new ArrayList<TinTuc>();
    TinTuc docBao;
    String textContent;


    public static final String CHANNEL_ID = "exampleServiceChannel";

    MediaPlayer player;

    IBinder iBinder = new LocalBinder();
    public DocBaoSevri_PH24891() {
    }
    public class LocalBinder extends Binder {
        LocalBinder getLocalBinder(){
            return LocalBinder.this;// phương thức khởi tạo khi client gọi tới các phương thức của service
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    public List<TinTuc> getDocBaoList(InputStream inputStream) {
        // nội dung tự viết , tham khảo ví dụ product
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // truyền nguồn dữ liệu
            parser.setInput(inputStream, null);
            // xác định event type
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // viết code xử lý ở đây
                String tagName = parser.getName();
                Log.d("zzzzz", "Tag name =  " + tagName +
                        ", Độ sâu của thẻ = " + parser.getDepth() + ", event = " + eventType);


                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        // bắt đầu vào 1 thẻ
                        if (tagName.equalsIgnoreCase("item")) {
                            docBao = new TinTuc();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textContent = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(docBao != null){
                            //
                            if(tagName.equalsIgnoreCase("item")){
                                listDocBao.add(docBao);
                            }


                            if (tagName.equalsIgnoreCase("title")){
                                docBao.setTitle( textContent );
                            }
                            if (tagName.equalsIgnoreCase("link")){
                                docBao.setLink( textContent );
                            }
                            if (tagName.equalsIgnoreCase("pubDate")){
                                docBao.setPubDate( textContent );
                            }
                            if (tagName.equalsIgnoreCase("description"))
                            {
                                docBao.setDescription( textContent );
                                }


                            }

                        }


                        break;

                }


                // viết lệnh chuyển event type để vòng lặp không bị treo
                // để ở cuối cùng của lệnh while
                eventType = parser.next();
            } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        return listDocBao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Kết thúc", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(serviceChannel);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.cccc);
        player.setLooping(true);
        player.start();


        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DocBaoSevri_PH24891.this, "hết nhạc rùi", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        },3000);

        return START_NOT_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Hủy service", Toast.LENGTH_SHORT).show();
        player.stop();
    }
}
