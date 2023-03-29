package com.nbird.mathiqtest.DATA;

import android.content.Context;
import android.content.SharedPreferences;

import com.nbird.mathiqtest.LEVEL.MODEL.LevelModel;
import com.nbird.mathiqtest.R;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    public Context context;
    public List<String> levelArray=new ArrayList<>();

    public GameData(Context context) {
        this.context = context;
    }


    public List<LevelModel> getLevelData1(){
        List<LevelModel> levelModelList=new ArrayList<>();

        levelModelList.add(new LevelModel("Spin Junkie", R.drawable.m_1_1,1));
        levelModelList.add(new LevelModel("Fire of Limits", R.drawable.m_1_2,2));
        levelModelList.add(new LevelModel("Painted Butterfly", R.drawable.m_1_3,3));
        levelModelList.add(new LevelModel("Cruiser Seat", R.drawable.m_1_4,4));
        levelModelList.add(new LevelModel("Clash", R.drawable.m_1_5,5));
        levelModelList.add(new LevelModel("Strength", R.drawable.m_1_6,6));
        levelModelList.add(new LevelModel("Expression", R.drawable.m_1_7,7));
        levelModelList.add(new LevelModel("Slant Triangles", R.drawable.m_1_8,8));
        levelModelList.add(new LevelModel("Futurity", R.drawable.m_1_9,9));
        levelModelList.add(new LevelModel("Glide Master", R.drawable.m_1_10,10));
        levelModelList.add(new LevelModel("Cryptography", R.drawable.m_1_11,11));
        levelModelList.add(new LevelModel("Nebula Layout", R.drawable.m_1_12,12));
        levelModelList.add(new LevelModel("Royal Court", R.drawable.m_1_13,13));
        levelModelList.add(new LevelModel("Blade Edge", R.drawable.m_1_14,14));
        levelModelList.add(new LevelModel("Tally", R.drawable.m_1_15,15));
        levelModelList.add(new LevelModel("Triangular Titan", R.drawable.m_1_16,16));
        levelModelList.add(new LevelModel("Topsy-Turvy", R.drawable.m_1_17,17));
        levelModelList.add(new LevelModel("Visual Blueprint", R.drawable.m_1_18,18));
        levelModelList.add(new LevelModel("Bifocals", R.drawable.m_1_19,19));
        levelModelList.add(new LevelModel("Indian Banyan", R.drawable.m_1_20,20));
        levelModelList.add(new LevelModel("Sub-Zero Sip", R.drawable.m_1_21,21));
        levelModelList.add(new LevelModel("Repeat Tumbler", R.drawable.m_1_22,22));



        return levelModelList;


    }



    public List<LevelModel> getLevelData2(){
        List<LevelModel> levelModelList=new ArrayList<>();

        levelModelList.add(new LevelModel("Spin-a-Rama", R.drawable.m_2_1,23));
        levelModelList.add(new LevelModel("Bubblz", R.drawable.m_2_2,24));
        levelModelList.add(new LevelModel("Neon", R.drawable.m_2_3,25));
        levelModelList.add(new LevelModel("Jade Jewel", R.drawable.m_2_4,26));
        levelModelList.add(new LevelModel("Happy Numbers", R.drawable.m_2_5,27));
        levelModelList.add(new LevelModel("Arcane Chest", R.drawable.m_2_6,28));
        levelModelList.add(new LevelModel("Vega", R.drawable.m_2_7,29));
        levelModelList.add(new LevelModel("Frost Bucket", R.drawable.m_2_8,30));
        levelModelList.add(new LevelModel("Glam Card", R.drawable.m_2_9,31));
        levelModelList.add(new LevelModel("Polar Crate", R.drawable.m_2_10,32));
        levelModelList.add(new LevelModel("Tick-Tock", R.drawable.m_2_11,33));
        levelModelList.add(new LevelModel("Delta", R.drawable.m_2_12,34));
        levelModelList.add(new LevelModel("Oak Tree", R.drawable.m_2_13,35));
        levelModelList.add(new LevelModel("Super Grid", R.drawable.m_2_14,36));
        levelModelList.add(new LevelModel("Aurora Toggle Switch", R.drawable.m_2_15,37));
        levelModelList.add(new LevelModel("Operation Grid", R.drawable.m_2_16,38));
        levelModelList.add(new LevelModel("Funny Spheres", R.drawable.m_2_17,39));
        levelModelList.add(new LevelModel("Happy Balancing", R.drawable.m_2_18,40));
        levelModelList.add(new LevelModel("Drive Slow", R.drawable.m_2_19,41));
        levelModelList.add(new LevelModel("Glowing with Battery", R.drawable.m_2_20,42));
        levelModelList.add(new LevelModel("Crystal Palace", R.drawable.m_2_21,43));
        levelModelList.add(new LevelModel("Phantom", R.drawable.m_2_22,44));
        levelModelList.add(new LevelModel("Nimbus Cloud Chair", R.drawable.m_2_23,45));
        levelModelList.add(new LevelModel("Cyclone", R.drawable.m_2_24,46));
        levelModelList.add(new LevelModel("Orbital", R.drawable.m_2_25,47));
        



        return levelModelList;


    }



    public List<LevelModel> getLevelData3(){
        List<LevelModel> levelModelList=new ArrayList<>();

        levelModelList.add(new LevelModel("Flower Button", R.drawable.m_3_1,48));
        levelModelList.add(new LevelModel("Funny Numbers", R.drawable.m_3_2,49));
        levelModelList.add(new LevelModel("Logic Numbers", R.drawable.m_3_3,50));
        levelModelList.add(new LevelModel("Threads", R.drawable.m_3_4,51));
        levelModelList.add(new LevelModel("Super Grid", R.drawable.m_3_5,52));
        levelModelList.add(new LevelModel("Bits", R.drawable.m_3_6,53));
        levelModelList.add(new LevelModel("Alien Diagrams", R.drawable.m_3_7,54));
        levelModelList.add(new LevelModel("Danger Sets", R.drawable.m_3_8,55));
        levelModelList.add(new LevelModel("Minding Diagrams", R.drawable.m_3_9,56));
        levelModelList.add(new LevelModel("See-Saw", R.drawable.m_3_10,57));
        levelModelList.add(new LevelModel("Gym Time", R.drawable.m_3_11,58));
        levelModelList.add(new LevelModel("Emoji Expressions", R.drawable.m_3_12,59));
        levelModelList.add(new LevelModel("Traffic Lights", R.drawable.m_3_13,60));
        levelModelList.add(new LevelModel("Triangle World", R.drawable.m_3_14,61));
        levelModelList.add(new LevelModel("Trekking", R.drawable.m_3_15,62));
        levelModelList.add(new LevelModel("Measure It", R.drawable.m_3_16,63));
        levelModelList.add(new LevelModel("Fast & Furious", R.drawable.m_3_17,64));
        levelModelList.add(new LevelModel("Swimming Blocks", R.drawable.m_3_18,65));
        levelModelList.add(new LevelModel("Semi-Circles", R.drawable.m_3_19,66));
        levelModelList.add(new LevelModel("Circle Galaxy", R.drawable.m_3_20,67));
        levelModelList.add(new LevelModel("Magic Keyboard", R.drawable.m_3_21,68));
      

        return levelModelList;


    }



    public List<LevelModel> getLevelData4(){
        List<LevelModel> levelModelList=new ArrayList<>();

        levelModelList.add(new LevelModel("Blue Buttons", R.drawable.m_4_1,69));
        levelModelList.add(new LevelModel("Strange Keyboard", R.drawable.m_4_2,70));
        levelModelList.add(new LevelModel("Confused Triangles", R.drawable.m_4_3,71));
        levelModelList.add(new LevelModel("PlayStation", R.drawable.m_4_4,72));
        levelModelList.add(new LevelModel("Tic-Tac-Toe", R.drawable.m_4_5,73));
        levelModelList.add(new LevelModel("Poison", R.drawable.m_4_6,74));
        levelModelList.add(new LevelModel("Glowing Keyboard", R.drawable.m_4_7,75));
        levelModelList.add(new LevelModel("Eagle Shapes", R.drawable.m_4_8,76));
        levelModelList.add(new LevelModel("Faces", R.drawable.m_4_9,77));
        levelModelList.add(new LevelModel("Confused Magic", R.drawable.m_4_10,78));
        levelModelList.add(new LevelModel("Solar System", R.drawable.m_4_11,79));
        levelModelList.add(new LevelModel("Pyramid", R.drawable.m_4_12,80));
        levelModelList.add(new LevelModel("Glowing Keys", R.drawable.m_4_13,81));
        levelModelList.add(new LevelModel("Strange Ladders", R.drawable.m_4_14,82));
        levelModelList.add(new LevelModel("Drex", R.drawable.m_4_15,83));
        levelModelList.add(new LevelModel("False Maths", R.drawable.m_4_16,84));
        levelModelList.add(new LevelModel("My Computer", R.drawable.m_4_17,85));
        levelModelList.add(new LevelModel("Airlift", R.drawable.m_4_18,86));
        levelModelList.add(new LevelModel("Good Smell", R.drawable.m_4_19,87));
        levelModelList.add(new LevelModel("DJ Night", R.drawable.m_4_20,88));
        levelModelList.add(new LevelModel("Universe of Triangles", R.drawable.m_4_21,89));
        

        return levelModelList;


    }

    public List<LevelModel> getLevelData5(){
        List<LevelModel> levelModelList=new ArrayList<>();

        levelModelList.add(new LevelModel("Multi Dimensions", R.drawable.m_5_1,90));
        levelModelList.add(new LevelModel("Bouncing Balls", R.drawable.m_5_2,91));
        levelModelList.add(new LevelModel("Sticky Sticks", R.drawable.m_5_3,92));
        levelModelList.add(new LevelModel("Superimposed", R.drawable.m_5_4,93));
        levelModelList.add(new LevelModel("Titan Table", R.drawable.m_5_5,94));
        levelModelList.add(new LevelModel("Pyramid Family", R.drawable.m_5_6,95));
        levelModelList.add(new LevelModel("Greedy Block", R.drawable.m_5_7,96));
        levelModelList.add(new LevelModel("Foul Numbers", R.drawable.m_5_8,97));
        levelModelList.add(new LevelModel("Maths of Tick-Cross", R.drawable.m_5_9,98));
        levelModelList.add(new LevelModel("Mother India", R.drawable.m_5_10,99));
        levelModelList.add(new LevelModel("Ancient Script", R.drawable.m_5_11,100));
        levelModelList.add(new LevelModel("Starfish Mania", R.drawable.m_5_12,101));
        levelModelList.add(new LevelModel("Road Of Trucks", R.drawable.m_5_13,102));
        levelModelList.add(new LevelModel("World of Shapes", R.drawable.m_5_14,103));
        levelModelList.add(new LevelModel("Stick and Dots", R.drawable.m_5_15,104));
        levelModelList.add(new LevelModel("Sweet Rainbow", R.drawable.m_5_16,105));
        levelModelList.add(new LevelModel("Direction Giver", R.drawable.m_5_17,106));
        levelModelList.add(new LevelModel("Equation of Circles", R.drawable.m_5_18,107));



        return levelModelList;


    }
    

    public void levelArray(int start,int end){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MAIN_LEVEL", 0);
        for(int i=start;i<=end;i++){
            if(i==1){
                String value = sharedPreferences.getString("LEVEL"+i,"UNLOCKED");
                levelArray.add(value);
            }else{
                String value = sharedPreferences.getString("LEVEL"+i,"LOCKED");
                levelArray.add(value);
            }

        }
    }


    public void levelPermissionSetter(int level,String text){
        final SharedPreferences sharedPreferences = context.getSharedPreferences("MAIN_LEVEL", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LEVEL"+level,text);
        editor.commit();
    }

    public String levelPermissionGetter(int level){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MAIN_LEVEL", 0);
        String value = sharedPreferences.getString("LEVEL"+level,"LOCKED");
        return value;
    }


    public void pointSetter(int level,int hintNumber){


        final SharedPreferences sharedPreferences = context.getSharedPreferences("POINT", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int value = sharedPreferences.getInt("LEVEL"+level,200);


        if(hintNumber==2){
            if(value==200){
                editor.putInt("LEVEL"+level,150);
                editor.commit();
            }
        }else if(hintNumber==3){
            if(value==150){
                editor.putInt("LEVEL"+level,100);
                editor.commit();
            }
        }else if(hintNumber==4){
            if(value==100){
                editor.putInt("LEVEL"+level,50);
                editor.commit();
            }
        }else{
            if(value==50){
                editor.putInt("LEVEL"+level,0);
                editor.commit();
            }
        }



    }



    public int pointGetter(int level){

        final SharedPreferences sharedPreferences = context.getSharedPreferences("POINT", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int value = sharedPreferences.getInt("LEVEL"+level,200);

        return value;


    }


}
