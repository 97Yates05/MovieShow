package com.example.yangchenhui.movieshow;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangchenhui.movieshow.adapter.MyAdapter;
import com.example.yangchenhui.movieshow.adapter.MyAsyncLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.type_list)
    Spinner spinner;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.movie_list)
    RecyclerView movie_list;
    @BindView(R.id.info)
    TextView info;

    private String movie_type = "";
    private LoaderManager manager;
    public static final String URL = "http://www.imooc.com/api/movie";
    public int flag = 1;
    private List<MovieBean> movieBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        manager = getSupportLoaderManager();
        Bundle bundle = new Bundle();
        bundle.putString("url", URL);
        manager.initLoader(0, bundle, this);
        movie_list.setLayoutManager(new GridLayoutManager(this, 2));
        back.setVisibility(View.GONE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                movie_type = (String) spinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.search, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (TextUtils.isEmpty(name.getText()) && movie_type.equals("请选择")) {
                    Toast.makeText(MainActivity.this, "名字和类型请选择一项",
                            Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    info.setText("搜索结果");
                    flag=2;
                    back.setVisibility(View.VISIBLE);
                    search(name.getText().toString(), movie_type);
                    break;
                }
            case R.id.back:
                info.setText("正在热映");
                flag=1;
                movie_list.setLayoutManager(new GridLayoutManager(this, 2));
                Bundle bundle = new Bundle();
                bundle.putString("url", URL);
                manager.restartLoader(0, bundle, this);
                back.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 搜素事件
     *
     * @param s
     * @param movie_type
     */
    private void search(String s, String movie_type) {

        if (TextUtils.isEmpty(s)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", URL + "?types=" + movie_type);
            manager.restartLoader(0, bundle, this);
        } else if (movie_type.equals("请选择")) {
            Bundle bundle = new Bundle();
            bundle.putString("url", URL + "?title=" + s);
            manager.restartLoader(0, bundle, this);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("url", URL + "?title=" + s + "&types=" + movie_type);
            manager.restartLoader(0, bundle, this);
        }
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String data = bundle.getString("url");
        return new MyAsyncLoader(MainActivity.this, data);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String o) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        try {
            jsonObject = new JSONObject(o);
            if (jsonObject.getInt("total") == 0) {
                movie_list.setAdapter(null);
                Toast.makeText(MainActivity.this, "暂无该电影!",
                        Toast.LENGTH_SHORT).show();
            } else {
                jsonArray = jsonObject.getJSONArray("movies");
                Gson gson = new Gson();
                movieBeanList = gson.fromJson(jsonArray.toString(),
                        new TypeToken<List<MovieBean>>() {
                        }.getType());
                if(flag==2){

                    movie_list.setLayoutManager(new LinearLayoutManager(this));
                }
                movie_list.setAdapter(new MyAdapter(MainActivity.this, movieBeanList,flag));

//        Logger.d(movieBeanList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
