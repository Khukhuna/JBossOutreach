package com.khukhuna.jbossoutreach.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khukhuna.jbossoutreach.R;
import com.khukhuna.jbossoutreach.models.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.MyViewHolder> {
    private List<Repository> mDataset = new ArrayList<>();

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;

        TextView title;
        TextView stars;
        TextView issues;
        TextView forks;
        TextView description;
        ImageView logo;

        public MyViewHolder(View v) {
            super(v);
            view = v;
            title = v.findViewById(R.id.title);
            stars = v.findViewById(R.id.stars);
            issues = v.findViewById(R.id.issues);
            forks = v.findViewById(R.id.forks);
            logo = v.findViewById(R.id.repo_logo);
            description = v.findViewById(R.id.description);
        }

        public void bind(Repository repository) {
            title.setText(repository.getName());
            stars.setText(String.valueOf(repository.getStars()));
            issues.setText(String.valueOf(repository.getIssues()));
            forks.setText(String.valueOf(repository.getForks()));
            description.setText(repository.getDescription());
            Glide.with(view.getContext()).load("http://maciek.lasyk.info/sysop/wp-content/uploads/2013/12/redhat.png").into(logo);
        }
    }

    public RepositoryAdapter(List<Repository> myDataset) {
        mDataset = myDataset;
    }
    public RepositoryAdapter(){}

    @Override
    public RepositoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_cell, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addData(List<Repository> data){
        mDataset.addAll(data);
        notifyDataSetChanged();
    }
}