package com.prince.util.fileutil;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by zidong.wang on 2015/12/15.
 */
public class FileUtil {
    private static FileUtil fileUtil;
    public static FileUtil getInstance(){
        if (fileUtil==null){
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    public File createFile(File pathFile){
        if(!pathFile.exists()){
            try {
                String parentPath = pathFile.getParent();
                File parentFile = new File(parentPath);
                if(!parentFile.isDirectory()){
                    parentFile.mkdirs();
                }
                pathFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathFile;
    }

    public void createPathFile(String path){
        File pathFile = new File(path);
        if(!pathFile.isDirectory()&&!pathFile.exists()){
            pathFile.mkdirs();
        }
    }

    public File createFile(String path){
        File pathFile = new File(path);
        return createFile(pathFile);
    }

    public void deleteFile(File pathFile){
        if(!pathFile.isDirectory()&&pathFile.exists()){
            pathFile.delete();
        }else if(pathFile.isDirectory()){
            File[] files = pathFile.listFiles();
            for(File f:files){
                deleteFile(f);
            }
            pathFile.delete();
        }
    }

    public void deleteFile(String path){
        File pathFile = new File(path);
        deleteFile(pathFile);
    }

    public void saveInputStreamInFile(InputStream in ,String path){
        File des = createFile(path);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(des));
            BufferedInputStream bis = new BufferedInputStream(in);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            bos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getContentFromFile(String path){
        File f = new File(path);
        return getContentFromFile(f);
    }
    public String getContentFromFile(File file){
        StringBuffer sb = new StringBuffer("");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line = null;
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public void saveContentInFile(String path,String content){
        File f = new File(path);
        saveContentInFile(f,content);
    }

    public void saveContentInFile(File f,String content){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"utf-8"));
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String unicodeToGB(String s)   {
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(s,"\\\\u");
        while (st.hasMoreTokens())   {
            sb.append((char)Integer.parseInt(st.nextToken(),16));
        }
        return sb.toString();
    }

    public String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    public String String2Unicode(String str){
        int lenth = str.length();
        StringBuffer sb = new StringBuffer();

        for(int i=0;i<lenth;i++){
            int code = str.charAt(i);
            String s = Integer.toHexString(code);
            sb.append("\\u");
            for(int j=s.length();j<4;j++){
                sb.append(0);
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
