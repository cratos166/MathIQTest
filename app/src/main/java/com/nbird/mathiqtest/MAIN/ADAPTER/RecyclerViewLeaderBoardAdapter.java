package com.nbird.mathiqtest.MAIN.ADAPTER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.nbird.mathiqtest.MAIN.MODEL.User;
import com.nbird.mathiqtest.R;


import java.util.List;

public class RecyclerViewLeaderBoardAdapter extends RecyclerView.Adapter<RecyclerViewLeaderBoardAdapter.MyViewHolder> {
    private List<User> mData;

    public RecyclerViewLeaderBoardAdapter(List<User> mData){
        this.mData=mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_board_listview,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int position) {

        holder.setData(mData.get(position).getName()
                ,mData.get(position).getScore()
                ,mData.get(position).getImageURL()
                ,position+1);


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView score;
        ImageView imageUrl1;
        TextView levelTextView;

        public MyViewHolder(View itemView){
            super(itemView);

            username=(TextView) itemView.findViewById(R.id.username);
            score=(TextView) itemView.findViewById(R.id.score);
            imageUrl1=(ImageView) itemView.findViewById(R.id.pic);
            levelTextView=(TextView) itemView.findViewById(R.id.levelText123);
        }

        public void setData(String username, int score, int ImageURL ,int rank) {
            this.imageUrl1.setImageResource(ImageURL);
            this.username.setText(username);
            this.score.setText(String.valueOf("HS : "+score+" "));
            this.levelTextView.setText(String.valueOf(rank));
        }



    }


}
