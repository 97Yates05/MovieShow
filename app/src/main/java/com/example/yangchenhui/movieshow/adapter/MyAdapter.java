package com.example.yangchenhui.movieshow.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangchenhui.movieshow.MovieBean;
import com.example.yangchenhui.movieshow.MovieShow;
import com.example.yangchenhui.movieshow.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<MovieBean> movieBeans;
    private Context context;
    private int flag;

    public MyAdapter(Context context, List<MovieBean> movieBeans, int flag) {
        this.context = context;
        this.movieBeans = movieBeans;
        this.flag = flag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {
        View view = null;
        MyViewHolder myViewHolder = null;
        if (flag == 1) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.item1, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
        } else  {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item2, viewGroup, false);
            myViewHolder=new MyViewHolder(view);
        }


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (flag == 1) {
            Glide.with(context).load(movieBeans.get(i).getImageUrl()).into(myViewHolder.item_photo);
            myViewHolder.item_name.setText(movieBeans.get(i).getTitle());
            myViewHolder.item_star.setRating
                    (Float.parseFloat(movieBeans.get(i).getRating().getStars()) / 10);
        } else {
            Glide.with(context).load(movieBeans.get(i).getImageUrl()).into(myViewHolder.photo);
            myViewHolder.name.setText(movieBeans.get(i).getTitle());
            myViewHolder.star.setRating
                    (Float.parseFloat(movieBeans.get(i).getRating().getStars()) / 10);
            myViewHolder.director.setText(movieBeans.get(i).getDirectors().get(0).getName());
            String actor_name = "";
            for (int j = 0; j < movieBeans.get(i).getCasts().size(); j++) {
                actor_name = actor_name + " " + movieBeans.get(i).getCasts().get(j).getName();
            }
            myViewHolder.actor.setText(actor_name);
            myViewHolder.time.setText(movieBeans.get(i).getYear());
            String types = "";
            for (int j = 0; j < movieBeans.get(i).getTypes().size(); j++) {
                types = types + movieBeans.get(i).getTypes().get(j) + "/";
            }
            myViewHolder.type.setText(types);
        }
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieShow.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("movie",movieBeans.get(i));
                intent.putExtras(bundle);


                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView item_photo,photo;
        TextView item_name,name,director,actor,time,type;
        RatingBar item_star,star;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            if(flag==1){
                item_photo=itemView.findViewById(R.id.item_photo);
                item_name=itemView.findViewById(R.id.item_name);
                item_star=itemView.findViewById(R.id.item_star);
            }else{
                photo=itemView.findViewById(R.id.search_photo);
                name=itemView.findViewById(R.id.search_name);
                star=itemView.findViewById(R.id.search_star);
                director=itemView.findViewById(R.id.search_director);
                actor=itemView.findViewById(R.id.search_actor);
                time=itemView.findViewById(R.id.search_time);
                type=itemView.findViewById(R.id.search_type);
            }

        }
    }
}
