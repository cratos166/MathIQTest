package com.nbird.mathiqtest.DATA;

import android.content.Context;
import android.content.SharedPreferences;

import com.nbird.mathiqtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocalData {


    Context context;
    List<Integer> avaList=new ArrayList<>();
    List<Integer> avaShadowList=new ArrayList<>();



    public LocalData(Context context) {
        this.context = context;

        avaList.add(R.drawable.ava1);avaList.add(R.drawable.ava2);avaList.add(R.drawable.ava3);avaList.add(R.drawable.ava4);avaList.add(R.drawable.ava5);
        avaList.add(R.drawable.ava6);avaList.add(R.drawable.ava7);avaList.add(R.drawable.ava8);avaList.add(R.drawable.ava9);avaList.add(R.drawable.ava10);
        avaList.add(R.drawable.ava11);avaList.add(R.drawable.ava12);avaList.add(R.drawable.ava13);avaList.add(R.drawable.ava14);avaList.add(R.drawable.ava15);
        avaList.add(R.drawable.ava16);avaList.add(R.drawable.ava17);avaList.add(R.drawable.ava18);avaList.add(R.drawable.ava19);avaList.add(R.drawable.ava20);
        avaList.add(R.drawable.ava21);avaList.add(R.drawable.ava22);avaList.add(R.drawable.ava23);avaList.add(R.drawable.ava24);avaList.add(R.drawable.ava25);
        avaList.add(R.drawable.ava26);avaList.add(R.drawable.ava27);avaList.add(R.drawable.ava28);avaList.add(R.drawable.ava29);avaList.add(R.drawable.ava30);
        avaList.add(R.drawable.ava31);avaList.add(R.drawable.ava32);avaList.add(R.drawable.ava33);avaList.add(R.drawable.ava34);avaList.add(R.drawable.ava35);
        avaList.add(R.drawable.ava36);avaList.add(R.drawable.ava37);avaList.add(R.drawable.ava38);avaList.add(R.drawable.ava39);avaList.add(R.drawable.ava40);
        avaList.add(R.drawable.ava41);avaList.add(R.drawable.ava42);avaList.add(R.drawable.ava43);avaList.add(R.drawable.ava44);avaList.add(R.drawable.ava45);
        avaList.add(R.drawable.ava46);avaList.add(R.drawable.ava47);avaList.add(R.drawable.ava48);avaList.add(R.drawable.ava49);avaList.add(R.drawable.ava50);



        avaShadowList.add(R.drawable.a1);avaShadowList.add(R.drawable.a2);avaShadowList.add(R.drawable.a3);avaShadowList.add(R.drawable.a4);avaShadowList.add(R.drawable.a5);
        avaShadowList.add(R.drawable.a6);avaShadowList.add(R.drawable.a7);avaShadowList.add(R.drawable.a8);avaShadowList.add(R.drawable.a9);avaShadowList.add(R.drawable.a10);
        avaShadowList.add(R.drawable.a11);avaShadowList.add(R.drawable.a12);avaShadowList.add(R.drawable.a13);avaShadowList.add(R.drawable.a14);avaShadowList.add(R.drawable.a15);
        avaShadowList.add(R.drawable.a16);avaShadowList.add(R.drawable.a17);avaShadowList.add(R.drawable.a18);avaShadowList.add(R.drawable.a19);avaShadowList.add(R.drawable.a20);
        avaShadowList.add(R.drawable.a21);avaShadowList.add(R.drawable.a22);avaShadowList.add(R.drawable.a23);avaShadowList.add(R.drawable.a4);avaShadowList.add(R.drawable.a25);
        avaShadowList.add(R.drawable.a26);avaShadowList.add(R.drawable.a27);avaShadowList.add(R.drawable.a28);avaShadowList.add(R.drawable.a29);avaShadowList.add(R.drawable.a30);
        avaShadowList.add(R.drawable.a31);avaShadowList.add(R.drawable.a32);avaShadowList.add(R.drawable.a33);avaShadowList.add(R.drawable.a34);avaShadowList.add(R.drawable.a35);
        avaShadowList.add(R.drawable.a36);avaShadowList.add(R.drawable.a37);avaShadowList.add(R.drawable.a38);avaShadowList.add(R.drawable.a39);avaShadowList.add(R.drawable.a40);
        avaShadowList.add(R.drawable.a41);avaShadowList.add(R.drawable.a42);avaShadowList.add(R.drawable.a43);avaShadowList.add(R.drawable.a44);avaShadowList.add(R.drawable.a45);
        avaShadowList.add(R.drawable.a46);avaShadowList.add(R.drawable.a47);avaShadowList.add(R.drawable.a48);avaShadowList.add(R.drawable.a49);avaShadowList.add(R.drawable.a50);



    }




    public void setMyName(String value){
        final SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", value);
        editor.commit();
    }

    public void setImage(int value){
        final SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("IMAGE", value);
        editor.commit();
    }




    public void setAvaInt(int value){
        final SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("AVA_INT", value);
        editor.commit();
    }


    public void setMyImageURL(String value){
        final SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IMAGE_URL", value);
        editor.commit();
    }


    private void setMyUID(){

        String uid= UUID.randomUUID().toString();

        final SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UID", uid);
        editor.commit();
    }


    public void setMyScore(int score){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int value = sharedPreferences.getInt("SCORE",0);
        value=value+score;
        editor.putInt("SCORE", value);
        editor.commit();
    }

    public int getMyScore(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        int value = sharedPreferences.getInt("SCORE",0);
        return value;
    }



    public String getMyUID(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        String value = sharedPreferences.getString("UID","");
        return value;
    }

    public String getMyImageURL(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        String value = sharedPreferences.getString("IMAGE_URL","");
        return value;
    }


    public int getAvaInt(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        int value = sharedPreferences.getInt("AVA_INT",100);
        return value;
    }


    public String getMyName(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        String value = sharedPreferences.getString("NAME","");
        return value;
    }



    public List<Boolean> getMyLevelStatus(){
        List<Boolean> levelList=new ArrayList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        boolean value1 = sharedPreferences.getBoolean("LEVEL1",true);
        levelList.add(value1);
        for(int i=1;i<=5;i++){
            boolean value = sharedPreferences.getBoolean("LEVEL"+i,false);
            levelList.add(value);
        }

        return levelList;

    }


    public int getImage(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        int value = sharedPreferences.getInt("IMAGE",R.drawable.default_pic);
        return value;
    }







    public Boolean isFirstTime(){

        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_DATA", 0);
        Boolean value = sharedPreferences.getBoolean("IS_FIRST_TIME", true);

        if(value){
            setMyUID();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("IS_FIRST_TIME", false);
            editor.apply();
            return true;
        }

        return false;

    }


    public List<Integer> getAvaList(){




        return avaList;
    }



    public int getAvaFromList(int pos){
        return avaList.get(pos);
    }

    public int getAvaShadowFromList(int pos){
        return avaShadowList.get(pos);
    }


}
