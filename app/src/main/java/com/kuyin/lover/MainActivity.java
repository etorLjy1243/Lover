package com.kuyin.lover;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kuyin.lover.adapter.ImageListAdapter;
import com.kuyin.lover.model.ImageModel;
import com.kuyin.lover.viewmodel.MainViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.no_result)
    TextView noResult;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<ImageModel.DataBean> imageModels = new ArrayList<>();
    private ImageListAdapter adapter;
    private MainViewModel viewModel;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ImageListAdapter(this, imageModels);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.mutableLiveData.observe(this, listBaseResponse -> {

            if (listBaseResponse != null) {
                if (page == 1) {
                    imageModels.clear();
                    refreshLayout.finishRefresh();
                } else {
                    refreshLayout.finishLoadMore();
                }
                imageModels.addAll(listBaseResponse.getData());
                adapter.setImageModels(imageModels);
                noResult.setVisibility(View.GONE);
            } else {
                noResult.setVisibility(View.VISIBLE);
            }
        });

        viewModel.makeApiCall(page, 10);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                viewModel.makeApiCall(page, 10);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                viewModel.makeApiCall(page, 10);
            }
        });
    }
}
