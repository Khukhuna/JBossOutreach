package com.khukhuna.jbossoutreach.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.khukhuna.jbossoutreach.R;
import com.khukhuna.jbossoutreach.models.Contributor;

import java.util.ArrayList;
import java.util.List;

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.MyViewHolder> {
    private List<Contributor> mDataset = new ArrayList<>();


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        ImageView avatar;
        TextView name;
        public MyViewHolder(View v) {
            super(v);
            view = v;

            avatar = v.findViewById(R.id.contribAvatar);
            name = v.findViewById(R.id.contribName);
        }

        public void bind(Contributor contributor) {
            Glide.with(view.getContext())
                    .load(contributor.getImageurl())
                    .apply(new RequestOptions().circleCrop())
                    .into(avatar);
            name.setText(contributor.getName());

        }
    }

    public ContributorsAdapter(List<Contributor> myDataset) {
        mDataset = myDataset;
    }

    public ContributorsAdapter(){}

    // Create new views (invoked by the layout manager)
    @Override
    public ContributorsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contributors_cell, parent, false);

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItems(List<Contributor> data){
        mDataset.addAll(data);
        notifyDataSetChanged();
    }
}