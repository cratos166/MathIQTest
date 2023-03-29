package com.nbird.mathiqtest.LEVEL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nbird.mathiqtest.DATA.GameData;
import com.nbird.mathiqtest.GAME.GameActivity;
import com.nbird.mathiqtest.LEVEL.ADAPTER.LevelAdapter;
import com.nbird.mathiqtest.LEVEL.MODEL.LevelModel;
import com.nbird.mathiqtest.MAIN.ADAPTER.ModeAdapter;
import com.nbird.mathiqtest.MainActivity;
import com.nbird.mathiqtest.R;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    RecyclerView recyclerView;

    int level;

    List<LevelModel> levelModelList;
    LevelAdapter myAdapter;
    GameData gameData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        level=getIntent().getIntExtra("LEVEL",1);

        levelModelList=new ArrayList<>();
        gameData=new GameData(LevelActivity.this);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);

        if(level==1){
            levelModelList=gameData.getLevelData1();
        }else if(level==2){
            levelModelList=gameData.getLevelData2();
        }else if(level==3){
            levelModelList=gameData.getLevelData3();
        }else if(level==4){
            levelModelList=gameData.getLevelData4();
        }else {
            levelModelList=gameData.getLevelData5();
        }





        RecyclerCardView();




    }

    public void RecyclerCardView(){
        myAdapter=new LevelAdapter(this,levelModelList,level);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);
    }


    public void onBackPressed(){
        Intent intent=new Intent(LevelActivity.this, MainActivity.class);
        intent.putExtra("LEVEL",level);
        startActivity(intent);
        finish();
    }



}