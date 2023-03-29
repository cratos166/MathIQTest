package com.nbird.mathiqtest.LEVEL.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nbird.mathiqtest.DATA.Constants;
import com.nbird.mathiqtest.DATA.GameData;
import com.nbird.mathiqtest.DATA.LocalData;
import com.nbird.mathiqtest.GAME.GameActivity;
import com.nbird.mathiqtest.LEVEL.MODEL.LevelModel;
import com.nbird.mathiqtest.R;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.MyViewHolder> {
    private Context mContext;
    private List<LevelModel> mData;

    LocalData localData;

    GameData gameData;
    int gate;

    public LevelAdapter(Context mContext, List<LevelModel> mData, int level){
        this.mContext=mContext;
        this.mData=mData;
        this.gate =level;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        localData=new LocalData(mContext);
        gameData=new GameData(mContext);

        if(gate ==1){
            gameData.levelArray(Constants.STARTING_LEVEL_1,Constants.ENDING_LEVEL_1);
        }else if(gate==2){
            gameData.levelArray(Constants.STARTING_LEVEL_2,Constants.ENDING_LEVEL_2);
        }else if(gate==3){
            gameData.levelArray(Constants.STARTING_LEVEL_3,Constants.ENDING_LEVEL_3);
        }else if(gate==4){
            gameData.levelArray(Constants.STARTING_LEVEL_4,Constants.ENDING_LEVEL_4);
        }else{
            gameData.levelArray(Constants.STARTING_LEVEL_5,Constants.ENDING_LEVEL_5);
        }


        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.level_mode,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setIsRecyclable(true);
        holder.nameText.setText(mData.get(position).getName());






        holder.levelImage.setImageResource(mData.get(position).getImageURL());
        holder.level_text.setText("Lv. "+mData.get(position).getLevel());



            if(gameData.levelArray.get(position).equals("UNLOCKED")){
                holder.isLockedText.setTextColor(Color.parseColor("#BFD1FF"));
                holder.isLockedText.setText(gameData.levelArray.get(position));
            }else if(gameData.levelArray.get(position).equals("SUCCESSFUL")){
                holder.isLockedText.setTextColor(Color.parseColor("#52B649"));
                holder.isLockedText.setText(gameData.levelArray.get(position));
            }else if(gameData.levelArray.get(position).equals("LOCKED")){
                holder.isLockedText.setTextColor(Color.parseColor("#FF4234"));
                holder.isLockedText.setText(gameData.levelArray.get(position));
            }else{
                holder.isLockedText.setTextColor(Color.parseColor("#FF9800"));
                holder.isLockedText.setText(gameData.levelArray.get(position));
            }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameData.levelArray.get(position).equals("UNLOCKED")||gameData.levelArray.get(position).equals("SUCCESSFUL")||gameData.levelArray.get(position).equals("FAILED")){
                    Intent intent=new Intent(mContext, GameActivity.class);
                    intent.putExtra("LEVEL",mData.get(position).getLevel());
                    intent.putExtra("MAIN_LEVEL",gate);
                    intent.putExtra("LEVEL_NAME",mData.get(position).getName());
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                }else{
                    Toast.makeText(mContext, "This level is locked.Please Solve the the lower level first.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }





    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView level_text,isLockedText,nameText;
        ImageView levelImage;
        CardView cardView;


        public MyViewHolder(View itemView){
            super(itemView);


            level_text=(TextView) itemView.findViewById(R.id.level_text);
            levelImage=(ImageView) itemView.findViewById(R.id.levelImage);
            isLockedText=(TextView) itemView.findViewById(R.id.isLockedText);
            nameText=(TextView) itemView.findViewById(R.id.nameText);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);

        }


    }

}
