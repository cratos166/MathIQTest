package com.nbird.mathiqtest.MAIN.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nbird.mathiqtest.DATA.LocalData;
import com.nbird.mathiqtest.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileSelectorAdapter extends RecyclerView.Adapter<ProfileSelectorAdapter.MyViewHolder> {
    private Context mContext;
    private List<Integer> avaList;
    private List<LinearLayout> linearLayoutList=new ArrayList<>();
    LocalData localData;
    CircleImageView mainActivitPropic;
    public ProfileSelectorAdapter(Context mContext, List<Integer> avaList, CircleImageView mainActivitPropic){
        this.mContext=mContext;
        this.avaList=avaList;
        this.mainActivitPropic=mainActivitPropic;
//        this.isSelected=isSelected;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        localData=new LocalData(mContext);

        if(localData.getAvaInt()==101){
            Glide.with(mContext).load(localData.getMyImageURL()).into(mainActivitPropic);

        }

        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.item_ava,parent,false);

        return new MyViewHolder(view);

    }

    public void setAllLinearLayoutDefault(){

        for(int i=0;i<getItemCount();i++){
            try{
                linearLayoutList.get(i).setBackgroundResource(R.drawable.border_theme_2);
            }catch (Exception e){

            }

        }

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        linearLayoutList.add(holder.linearLayout);


        if(localData.getAvaInt()!=101&&localData.getAvaInt()!=100){

            if(localData.getAvaInt()==position){
                linearLayoutList.get(localData.getAvaInt()).setBackgroundResource(R.drawable.selected_back);
            }


        }

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllLinearLayoutDefault();
                holder.linearLayout.setBackgroundResource(R.drawable.selected_back);
                localData.setAvaInt(position);
            }
        });
        holder.avaImage.setImageResource(avaList.get(position));
    }



    @Override
    public int getItemCount() {
        return avaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView avaImage;
        CardView cardview;
        LinearLayout linearLayout;


        public MyViewHolder(View itemView){
            super(itemView);

            avaImage=(ImageView) itemView.findViewById(R.id.avaImage);
            cardview=(CardView) itemView.findViewById(R.id.cardview);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);

        }


    }

}
