package com.xu.kevin.csvtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.socks.library.KLog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String mname ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<CatalogInfo> mCatalogInfos =  new KCsvReader().ReadCSVCatalogFile(getApplicationContext().getResources().openRawResource(R.raw.catalog));
                for (CatalogInfo mca:mCatalogInfos
                     ) {

                    KLog.e("\n");
                    KLog.e("catalog: "+ mca +" \n");
                    KLog.e("\n");
                }
            }
        }).start();

    }

}
