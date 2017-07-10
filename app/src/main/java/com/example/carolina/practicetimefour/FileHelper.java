package com.example.carolina.practicetimefour;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Created by carolina on 07/07/17.
 */

public class FileHelper {

    public static String readFileFromAssets(Context context, String filename) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();

        } catch (FileNotFoundException e){
            Log.e("FileHelper","File not found:" + e.getMessage());
        }
        catch (Exception e) {
            Log.e("FileHelper",e.getMessage());
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e("FileHelper",e.getMessage());
                }
            }


        }

        return null;
    }

}
