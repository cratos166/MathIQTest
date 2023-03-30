package com.nbird.mathiqtest.MAIN.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nbird.mathiqtest.DATA.LocalData;
import com.nbird.mathiqtest.LEVEL.LevelActivity;
import com.nbird.mathiqtest.MAIN.MODEL.Modes;
import com.nbird.mathiqtest.R;

import java.util.List;

public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.MyViewHolder> {
    private Context mContext;
    private List<Modes> mData;
    int a=1;

    LocalData localData;

    public ModeAdapter(Context mContext, List<Modes> mData){
        this.mContext=mContext;
        this.mData=mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        localData=new LocalData(mContext);

        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.item_mode,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.tv_exam_title.setText(mData.get(position).getTitle());
        holder.img_exam_thumbnail.setImageResource(mData.get(position).getThumbnail());

        List<Boolean> levelList = localData.getMyLevelStatus();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent=new Intent(mContext, LevelActivity.class);
                    intent.putExtra("LEVEL",position+1);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();


            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_exam_title;
        ImageView img_exam_thumbnail;
        CardView cardView;


        public MyViewHolder(View itemView){
            super(itemView);

            tv_exam_title=(TextView) itemView.findViewById(R.id.exam_title);
            img_exam_thumbnail=(ImageView) itemView.findViewById(R.id.exam_img_id);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);

        }


    }

}
