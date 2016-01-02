package com.prince.util.fileutil;

import java.io.*;
import java.util.List;

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
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));
            BufferedInputStream bis = new BufferedInputStream(in);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            bos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
