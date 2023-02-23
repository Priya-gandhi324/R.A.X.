package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import TrendingNews.Articles;
import TrendingNews.Headlines;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewApp extends AppCompatActivity {

    private static Button news_login;

    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;
    EditText search_query;
    Button searchbtn;

    final String API_KEY = "b015eb248ca944fd909ecbece682096e";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_app);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recycleView);
        search_query = findViewById(R.id.search_query);
        searchbtn = findViewById(R.id.searchbtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String country = getCountry();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson("", country, API_KEY);
            }
        });

        retrieveJson("", country, API_KEY);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!search_query.getText().toString().equals("")){

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retrieveJson(search_query.getText().toString(), country, API_KEY);
                        }
                    });

                    retrieveJson(search_query.getText().toString(), country, API_KEY);
                }
                else {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retrieveJson("", country, API_KEY);
                        }
                    });

                    retrieveJson("", country, API_KEY);
                }
            }
        });

        onClickButtonListenerLogin();

    }

    public void retrieveJson(String query, String country, String apiKey){

        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;

        if(!search_query.getText().toString().equals("")){
            call = ApiClient.getInstance().getApi().getSpecificData(query,apiKey);
        }
        else {
            call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        }

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);

                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(NewApp.this ,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(NewApp.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    public void onClickButtonListenerLogin()
    {
        news_login = (Button)findViewById(R.id.news_login);
        news_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Login");
                        startActivity(intent);
                    }
                }
        );
    }
}