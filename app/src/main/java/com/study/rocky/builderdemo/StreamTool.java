package com.study.rocky.builderdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class StreamTool {

    public static byte[] readInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[]buffer = new byte[1024];
        int len = 0;

        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer,0,len);
        }
        byte[] data = bos.toByteArray();
        bos.close();
        is.close();
        return data;
    }
}
