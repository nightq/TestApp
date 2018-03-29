package com.example.nightq.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadJar();
    }

    String assertDir = "protobuf";
    String jarOutputPath = FileHelper.BASE_FOLDER + assertDir + "/protobuf-lite-3.0.1";
    private void loadJar () {
        FileHelper.copyJarToSD(this, assertDir);
        String jarPath = FileHelper.BASE_FOLDER + assertDir + "/protobuf-lite-3.0.1.jar";
        if (!new File(jarPath).exists()){
            Log.e("nightq", "没有加载好jar包");
            return;
        }
        useDexClassLoader(jarPath);
    }

    @OnClick(R.id.image)
    public void onViewClicked() {
        loadJar();
    }


    private void useDexClassLoader(String path){
        File codeDir=getDir("protobuf_jar", Context.MODE_PRIVATE);
//        File codeDir=new File(jarOutputPath);
        //创建类加载器，把dex加载到虚拟机中
        DexClassLoader calssLoader = new DexClassLoader(path, codeDir.getAbsolutePath(), null,
                this.getClass().getClassLoader());
        DexClassLoader calssLoader2 = new DexClassLoader(path, codeDir.getAbsolutePath(), null,
                this.getClass().getClassLoader());
        DexClassLoader calssLoader3 = new DexClassLoader(path, codeDir.getAbsolutePath(), null,
                this.getClass().getClassLoader());
        //利用反射调用插件包内的类的方法
        try {
            com.google.protobuf.AbstractMessageLite abstractMessageLite = null;
            Log.e("nightq", "2 no e = : " + abstractMessageLite);
        } catch (Exception e) {
            Log.e("nightq", "2 e = : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
