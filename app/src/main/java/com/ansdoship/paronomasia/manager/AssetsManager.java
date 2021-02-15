package com.ansdoship.paronomasia.manager;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsManager {

    private static AssetsManager instance = null;
    private Context context;
    private String path;

    public AssetsManager(Context context){
        init(context);
    }

    public static AssetsManager getInstance(Context context) {
        if(instance==null){
            instance = new AssetsManager(context);
        }
        return instance;
    }

    private void init(Context context) {
        this.context = context;
        this.path = context.getExternalFilesDir("main").getPath();
        PathManager.add("d_main_parent",path);
        PathManager.add("f_main_script",path+"/main.yml");
        PathManager.add("f_ui_config",path+"/ui.yml");
    }

    public void release(){
        copyFilesFromAssets(context,"",path);
    }

    public String getPath() {
        return path;
    }

    private void copyFilesFromAssets(Context context, String srcPath, String dstPath) {
        try {
            String fileNames[] = context.getAssets().list(srcPath);
            if (fileNames.length > 0) {
                File file = new File(dstPath);
                if (!file.exists()) file.mkdirs();
                for (String fileName : fileNames) {
                    if (!srcPath.equals("")) { // assets 文件夹下的目录
                        copyFilesFromAssets(context, srcPath + File.separator + fileName, dstPath + File.separator + fileName);
                    } else { // assets 文件夹
                        copyFilesFromAssets(context, fileName, dstPath + File.separator + fileName);
                    }
                }
            } else {
                File outFile = new File(dstPath);
                InputStream is = context.getAssets().open(srcPath);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
