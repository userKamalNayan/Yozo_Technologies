package com.sampleassignment.yozotechnologies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sampleassignment.yozotechnologies.R;
import com.sampleassignment.yozotechnologies.model.entities.PostsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    List<PostsModel> postsList;
    Context context;


    public PostsAdapter(List<PostsModel> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_recycler_posts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_user_id.setText(new StringBuilder("User Id : ").append(postsList.get(position).getUserId()));
        holder.txt_id.setText(String.valueOf(postsList.get(position).getId()));

        holder.txt_body.setText(new StringBuilder(postsList.get(position).getBody().substring(0,20)).append("...").toString());
        holder.txt_title.setText(postsList.get(position).getTitle().toString());


    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_posts_body)
        TextView txt_body;
        @BindView(R.id.layout_posts_title)
        TextView txt_title;
        @BindView(R.id.layout_posts_id)
        TextView txt_id;
        @BindView(R.id.layout_posts_user_id)
        TextView txt_user_id;

Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }
}
