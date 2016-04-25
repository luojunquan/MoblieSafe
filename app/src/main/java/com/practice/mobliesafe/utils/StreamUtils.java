package com.practice.mobliesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取流的工具
 * Created by 赖上罗小贱 on 2016/4/22.
 */
public class StreamUtils {
    /*
     将输入流读取成String后返回
     */
    public static String readFromStream(InputStream in) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        String result = out.toString();
        in.close();
        out.close();
        return result;
    }
}
