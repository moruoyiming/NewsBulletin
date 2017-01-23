package com.mrym.newsbulletion.utils;

import android.content.Context;

import com.mrym.newsbulletion.NewsApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetUtils {

    //读取的 asset 目录下面的json文件
    public static String getFromAssets(String assetFileName) {

        InputStreamReader inputReader = null;
        String line;
        String Result = "";

        Context context = NewsApplication.getContext();

        try {
            inputReader = new InputStreamReader(context.getAssets().open(assetFileName));
            BufferedReader bufReader = new BufferedReader(inputReader);

            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result;
    }

}
