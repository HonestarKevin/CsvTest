package com.xu.kevin.csvtest;

import com.socks.library.KLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class KCsvReader {


    private BufferedReader bufferedreader;
    public static final String SPLIT_STRING = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public ArrayList<CatalogInfo> ReadCSVCatalogFile(InputStream inputStream) {
        ArrayList<CatalogInfo> mCatalogInfos = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("GBK"));
            bufferedreader = new BufferedReader(inputStreamReader);

            String stemp;
            bufferedreader.readLine();//delete first line
            while ((stemp = bufferedreader.readLine()) != null) {
                String[] mtempString = stemp.split(SPLIT_STRING);
                CatalogInfo mCatalogInfo = new CatalogInfo();
//                for (String mstr:mtempString
//                     ) {
//                    KLog.e("\n");
//                    KLog.e(mstr);
//                    KLog.e("\n");
//                }
                if(mtempString.length == 7) {
                    mCatalogInfo.setmLevelOne(mtempString[0]);
                    mCatalogInfo.setmLevelTow(mtempString[1]);
                    mCatalogInfo.setmLevelThree(mtempString[2]);
                    mCatalogInfo.setmIconName(mtempString[3]);
                    mCatalogInfo.setmCount(Integer.valueOf(mtempString[4]));
                    mCatalogInfo.setmStart(Integer.valueOf(mtempString[5]));
                    mCatalogInfo.setmEnd(Integer.valueOf(mtempString[6]));
                    mCatalogInfos.add(mCatalogInfo);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mCatalogInfos;
    }

    
}
