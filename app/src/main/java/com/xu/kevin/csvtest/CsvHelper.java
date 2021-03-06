package com.xu.kevin.csvtest;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Clair
 *
 * @date 2017/8/9
 * @description
 */
public class CsvHelper {
    public static final String  TAG = CsvHelper.class.getSimpleName();
    //要在头部加BOM签名，因为BOM签名能否让excel认识这个文件时utf-8编码的。
    private static final byte headerByte[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};


    private final static String CACHE_FOLDER = "testcache"+ File.separator+"csv_cache";
    private final static String FILE_NAME = "product_cache_";
    private final static String FILE_POSTFIX = ".csv";

    private final static String SD_CACHE_PATH = Environment.getExternalStorageDirectory() +
            File.separator + CACHE_FOLDER ;// + File.separator + FILE_NAME+FILE_POSTFIX

    public final static String NEW_LINE_SYMBOL = "\r\n";
    public final static String CSV_SEPARATOR_SYMBOL = ",";


    /**
     * 将对象导入到.csv文件
     * @param filePath
     */
    public static List<ProductInfo> importCSV(String filePath) {
        List<ProductInfo> list = new ArrayList<>();
        if(filePath.isEmpty()){
            return list;
        }
        File csvFile = new File(filePath);
        if(!csvFile.exists()||csvFile.isDirectory()){
            return list;
        }
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader(csvFile));

            String line;
            ProductInfo item;
            while((line = buffer.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, CSV_SEPARATOR_SYMBOL);
                if(st.countTokens()==4){
                    item = new ProductInfo();
                    item.setName(st.nextToken());
                    item.setProperty(st.nextToken());
                    item.setArtNo(st.nextToken());
                    item.setBarcode(st.nextToken());
                    list.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 将对象导出到.csv文件
     * @param list
     */
    public static void export2CSV(List<ProductInfo> list){
        if(list.isEmpty()){
            StringBuffer buffer = new StringBuffer();
            for(ProductInfo item:list){
                if(item!=null){
                    buffer.append(item.getName());
                    buffer.append(CSV_SEPARATOR_SYMBOL);
                    buffer.append(item.getProperty());
                    buffer.append(CSV_SEPARATOR_SYMBOL);
                    buffer.append(item.getArtNo());
                    buffer.append(CSV_SEPARATOR_SYMBOL);
                    buffer.append(item.getBarcode());
                    buffer.append(NEW_LINE_SYMBOL);
                }
            }
            //export2CSV(buffer);
            export2CSV(getCacheFilePath(),buffer.toString());
        }else{
            Log.e(TAG,"传入数据为空");
        }
    }

    /**
     * 存储文件到
     * @param filePath
     * @param data
     */
    private static void export2CSV(String filePath, String data) {
        File targetFile = new File(filePath);
        File dir = targetFile;
        if(!targetFile.isDirectory()) {
            dir = targetFile.getParentFile();
        }
        boolean isSuccess;
        if(dir.exists()) {
            isSuccess = true;
        }else{
            isSuccess = dir.mkdirs();
        }

        if(isSuccess){
            OutputStreamWriter output = null;
            BufferedReader buffer = null;

            try {
                output = new OutputStreamWriter(new FileOutputStream(targetFile, false));
                buffer = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

                String line;
                while((line = buffer.readLine()) != null) {
                    output.write(line);
                    output.write(NEW_LINE_SYMBOL);
                }
                output.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(buffer != null) {
                    try {
                        buffer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Log.e(TAG,"文件创建失败!");
        }
    }

    /**
     * Get the cache file name.
     * @return
     */
    private static String getCacheFileName(){
       return getCacheFileName(String.valueOf(System.currentTimeMillis()));
    }
    public static String getCacheFileName(String date){
       return FILE_NAME + date+FILE_POSTFIX;
    }

    private static String getCacheFilePath() {
        return SD_CACHE_PATH + File.separator + getCacheFileName();
    }

    public static String getCacheFilePath(String date) {
        return SD_CACHE_PATH + File.separator + getCacheFileName(date);
    }

    //-------------------------------

    /**
     *
     * @param buffer
     */
    private static void export2CSV(StringBuffer buffer) {
        //FileUtil.writeFile();
        String data = buffer.toString();

        String path = SD_CACHE_PATH;
        String filename = getCacheFileName();
        File folderFile = new File(path);
        boolean isSuccess = false;
        if (!folderFile.exists()) {
            isSuccess = folderFile.mkdirs();
        }
        if (isSuccess) {
            OutputStream out =null;
            try {
                File file = new File(path, filename);
                out = new FileOutputStream(file);
                out.write(headerByte);//处理乱码
                out.write(data.getBytes());
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException var20) {
                       var20.printStackTrace();
                    }
                }
            }
        } else {
            Log.e(TAG,"文件创建失败!");
        }
    }
}