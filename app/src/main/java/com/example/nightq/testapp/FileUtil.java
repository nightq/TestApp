package com.example.nightq.testapp;

import java.io.File;

/**
 * Created by zhuguangwen on 15/3/3.
 * email 979343670@qq.com
 * Edited by nightq 该类负责文件的创建删除等部分
 */
public class FileUtil {

    private static final String TAG = FileUtil.class.getName();

    public static void mkdir(String dirStr) throws Exception {
        File dir = new File(dirStr);

        if(!dir.isAbsolute()){
            throw new Exception("dir is not valid -->"+dirStr);
        }

        if(dir.exists()){
            return;
        }

        if(!dir.mkdirs()){
            throw new Exception("mkdir failed");
        }
    }

    public static boolean safeMkdir(String dir){
        try {
            mkdir(dir);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void createFile(String fileStr) throws Exception {
        File file = new File(fileStr);

        if(! file.isAbsolute()){
            throw new Exception("file is not valid -->"+fileStr);
        }

        if(file.exists()){
            return;
        }

        mkdir(file.getParentFile().getAbsolutePath());

        if(! file.createNewFile()){
            throw new Exception("create file failed");
        }
    }

    public static boolean safeCreateFile(String fileStr){
        try {
            createFile(fileStr);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void deleteFile(String fileStr) throws Exception {
        File file = new File(fileStr);

        if(! file.isAbsolute()){
            throw new Exception("file is not valid -->"+fileStr);
        }

        if(! file.exists()){
            return;
        }

        if(! file.delete()){
            throw new Exception("delete file failed");
        }
    }

    public static boolean safeDeleteFile(String fileStr){
        try {
            deleteFile(fileStr);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean fileExists(String path){
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    /**
     * 遍历一个目录下的文件
     * @param folderPath  目录路径
     * @param callback  遍历到某个文件的回调
     */
    public static void listFilesInFolder(String folderPath, ListFilesCallback callback){
        File dir = new File(folderPath);
        if(! dir.isDirectory()){
            throw new RuntimeException(folderPath + "is not a folder");
        }
        File[] files = dir.listFiles();
        if (files == null) {
            throw new RuntimeException("can not list files :"+folderPath);
        }
        for (File f : files) {
            callback.onGetFile(f);
        }
    }

    public interface ListFilesCallback {
        public void onGetFile(File f);
    }



    public static void safeDeleteFile(File file){
        try{
            deleteFile(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * delete a file
     * @param file  the file to delete
     * @throws RuntimeException RuntimeException when delete or delete failed
     */
    public static void deleteFile(File file) throws RuntimeException {
        if(! file.isAbsolute()){
            throw new RuntimeException(file.getPath() + " is not a valid file path");
        }
        if(! file.exists()){
            return;
        }
        if(! file.delete()){
            throw new RuntimeException("delete file failed");
        }
    }

    /**
     * create a file
     * @param file  the file to create
     * @throws RuntimeException  file is not a valid file, or creation failed
     */
    public static void createFile(File file) throws RuntimeException {
        if(! file.isAbsolute()){
            throw new RuntimeException(file.getPath() + " is not a valid file path");
        }

        if(file.exists()){
            return;
        }

        makeDirs(file.getParentFile());

        boolean ret;
        try{
            ret = file.createNewFile();
        }catch (Exception e){
            throw new RuntimeException("");
        }
        if(! ret){
            throw new RuntimeException("create file failed -->"+file.getPath());
        }
    }

    /**
     * create a directory file
     * @param dir  the dir file to create
     * @throws RuntimeException  dir is not valid, or creation failed
     */
    public static void makeDirs(File dir) throws RuntimeException {
        if(! dir.isAbsolute()){
            throw new RuntimeException(dir.getPath() + "is not a valid directory path");
        }

        if(dir.exists()){
            return;
        }

        if(! dir.mkdirs()){
            throw new RuntimeException("mkdirs failed -->"+dir.getPath());
        }
    }

    public static boolean ensureDir(File file) {
        boolean result = false;
        if(file != null) {
            if(file.exists()) {
                result = true;
            }
            else {
                result = file.mkdirs();
            }
        }
        return result;
    }


}
