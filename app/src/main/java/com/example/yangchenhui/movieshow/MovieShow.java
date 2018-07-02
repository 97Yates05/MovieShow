package com.example.yangchenhui.movieshow;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieShow extends AppCompatActivity {
    public static final String MOVIE_NAME="movie_name";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.content_pic)
    ImageView content_pic;
    @BindView(R.id.content_director)
    TextView director;
    @BindView(R.id.content_actor)
    TextView actor;
    @BindView(R.id.content_time)
    TextView time;
    @BindView(R.id.content_type)
    TextView type;
    @BindView(R.id.content_story)
    TextView story;



    private MovieBean movieBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        Intent intent=getIntent();
        if(intent.getSerializableExtra("movie")!=null){
            movieBean= (MovieBean) intent.getSerializableExtra("movie");
        }
        ButterKnife.bind(this);
        initView();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }





    }

    private void initView() {
        Log.d("sss", movieBean.toString());
        collapsing_toolbar.setTitle(movieBean.getTitle());
        Glide.with(MovieShow.this).load(movieBean.getImageUrl()).into(content_pic);
        director.setText(movieBean.getDirectors().get(0).getName());
        String actor_name = "";
        for (int j = 0; j < movieBean.getCasts().size(); j++) {
            actor_name = actor_name + " " + movieBean.getCasts().get(j).getName();
        }
        actor.setText(actor_name);
        time.setText(movieBean.getYear());
        String types = "";
        for (int j = 0; j < movieBean.getTypes().size(); j++) {
            types = types + movieBean.getTypes().get(j) + "/";
        }
        type.setText(types);
        story.setText(movieBean.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
