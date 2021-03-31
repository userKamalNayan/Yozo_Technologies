package com.sampleassignment.yozotechnologies.views.Posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sampleassignment.yozotechnologies.R;
import com.sampleassignment.yozotechnologies.adapters.PostsAdapter;
import com.sampleassignment.yozotechnologies.common.Common;
import com.sampleassignment.yozotechnologies.model.entities.PostsModel;
import com.sampleassignment.yozotechnologies.viewmodels.SharedViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostsFragment extends Fragment {

    private SharedViewModel viewModel;

    @BindView(R.id.posts_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.posts_progressbar)
    ProgressBar  progressBar;

    Unbinder unbinder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this).get(SharedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_posts, container, false);


        viewModel.getPostsList().observe(getViewLifecycleOwner(), new Observer<List<PostsModel>>() {
            @Override
            public void onChanged(List<PostsModel> postsModels) {
               if (postsModels.size()>0)
               {
                   progressBar.setVisibility(View.GONE);
                   recyclerView.setVisibility(View.VISIBLE);
                   PostsAdapter postsAdapter = new PostsAdapter(postsModels,getContext());
                   recyclerView.setAdapter(postsAdapter);

                   if (null != Common.postsRecyclerViewState)
                   {
                       recyclerView.getLayoutManager().onRestoreInstanceState(Common.postsRecyclerViewState);
                   }
               }
            }
        });

        unbinder= ButterKnife.bind(this,root);
        initViews();
        return root;
    }


    private void initViews() {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),layoutManager.getOrientation()));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Common.postsRecyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            }
        });


    }
}