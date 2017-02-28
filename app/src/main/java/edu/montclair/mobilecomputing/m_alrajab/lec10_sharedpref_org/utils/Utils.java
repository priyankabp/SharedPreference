package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by m_alrajab on 2/22/17.
 */

public class Utils {

    public static final String SHARED_PREF_FILENAME="edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.SHAREDFILE1";
    public static final String KEY_TITLE="Title_";
    public static final String KEY_BODY="Body_";


    public static String[] getListFromSP(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_FILENAME,
                Context.MODE_PRIVATE);
        Map<String, ?> map=sharedPreferences.getAll();
        ArrayList<String> lst= new ArrayList<>();
        for(String str:map.keySet()){
            if(str.startsWith(key))
                lst.add((String)map.get(str));
        }
        return lst.toArray(new String[lst.size()]);
    }

    //Using Files
    //Gets the list of added note titles
    public static String[] getListFromFiles(Context context){
        ArrayList<String> lstOfFilesInMemeory = new ArrayList<>();
        try{
            File filesDir = context.getFilesDir();
            File[] files = filesDir.listFiles();
            for(int i=1;i<files.length;i++)
                lstOfFilesInMemeory.add(files[i].getName().toString());
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        String[] list = lstOfFilesInMemeory.toArray(new String[lstOfFilesInMemeory.size()]);
        return list;
    }

    //Using Files
    //Gets the body of the notes.
    public static String getFileByName(Context context, String filename){
        String tempStr = "";
        try{
            FileInputStream inputtStream = context.openFileInput(filename.replace(" ", ""));
            int c;
            while((c=inputtStream.read())!=-1){
                tempStr+=Character.toString((char) c);
            }
            inputtStream.close();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        return tempStr;
    }
}
