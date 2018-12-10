package com.khukhuna.jbossoutreach.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.khukhuna.jbossoutreach.R;
import com.khukhuna.jbossoutreach.adapters.ContributorsAdapter;
import com.khukhuna.jbossoutreach.helpers.Constants;
import com.khukhuna.jbossoutreach.models.Contributor;
import com.khukhuna.jbossoutreach.network.GitHubClient;
import com.khukhuna.jbossoutreach.network.GitHubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    String name;
    private RecyclerView mRecyclerView;
    private ContributorsAdapter mAdapter;
    private ImageView firstPlace;
    private ImageView secondPlace;
    private ImageView thirdPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mRecyclerView = findViewById(R.id.contribRecycler);

        ImageView logo = findViewById(R.id.repo_logo);
        firstPlace = findViewById(R.id.first_place);
        secondPlace = findViewById(R.id.second_place);
        thirdPlace = findViewById(R.id.third_place);

        Intent intent = getIntent();
        name = intent.getStringExtra(Constants.NAME);
        setTitle(name);
        Glide.with(this).load("http://maciek.lasyk.info/sysop/wp-content/uploads/2013/12/redhat.png").into(logo);


        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new ContributorsAdapter();
        mRecyclerView.setAdapter(mAdapter);


        GitHubService service = GitHubClient.getClient().create(GitHubService.class);
        Call<List<Contributor>> call = service.getContributors(name);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contributor>> call, @NonNull Response<List<Contributor>> response) {
                if(response.isSuccessful()){
                    List<Contributor> data = response.body();
                    if(data != null){

                        Glide.with(DetailsActivity.this).load(data.get(0).getImageurl()).apply(RequestOptions.circleCropTransform()).into(firstPlace);
                        Glide.with(DetailsActivity.this).load(data.get(1).getImageurl()).apply(RequestOptions.circleCropTransform()).into(secondPlace);
                        Glide.with(DetailsActivity.this).load(data.get(2).getImageurl()).apply(RequestOptions.circleCropTransform()).into(thirdPlace);

                        mAdapter.addItems(data);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Contributor>> call, @NonNull Throwable t) {
                Toast.makeText(DetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
