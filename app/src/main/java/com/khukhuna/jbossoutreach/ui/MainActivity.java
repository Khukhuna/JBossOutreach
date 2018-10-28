package com.khukhuna.jbossoutreach.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.khukhuna.jbossoutreach.R;
import com.khukhuna.jbossoutreach.adapters.RepositoryAdapter;
import com.khukhuna.jbossoutreach.models.Repository;
import com.khukhuna.jbossoutreach.network.GitHubClient;
import com.khukhuna.jbossoutreach.network.GitHubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GitHubService service;

    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = GitHubClient.getClient().create(GitHubService.class);
        getData("JbossOutreach");

        recyclerView = findViewById(R.id.repoRecyclerView);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RepositoryAdapter();
        recyclerView.setAdapter(adapter);
    }


    public void getData(String organization){
        Call<List<Repository>> call = service.getProfile(organization);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                if(response.isSuccessful()){
                    List<Repository> data = response.body();
                    if(data != null){
                        adapter.addData(data);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
