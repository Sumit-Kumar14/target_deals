package com.target.dealbrowserpoc.dealbrowser.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.target.dealbrowserpoc.dealbrowser.R;

import com.target.dealbrowserpoc.dealbrowser.model.DealItem;
import com.target.dealbrowserpoc.dealbrowser.model.network.NetworkService;
import com.target.dealbrowserpoc.dealbrowser.presenter.DealsPresenter;

import java.util.ArrayList;
import java.util.List;

public class DealListActivity extends AppCompatActivity implements IDealsViewContract{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private RecyclerView mDealsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<DealItem> dealItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.deal_detail_container) != null) {
            mTwoPane = true;
        }

        mDealsRecyclerView = findViewById(R.id.deal_list);
        assert mDealsRecyclerView != null;
        setupRecyclerView();

        NetworkService networkService = new NetworkService();
        DealsPresenter dealsPresenter = new DealsPresenter(networkService, this);
        dealsPresenter.fetchDealsFromNetwork();
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDealsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DealListItemAdapter(this, dealItemList, mTwoPane);
        mDealsRecyclerView.setAdapter(mAdapter);
    }

    public void updateUI(List<DealItem> dealItems) {
        dealItemList.clear();
        dealItemList.addAll(dealItems);
        if(mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void showError(String error) {

    }

    @Override
    public void updateDealsList(List<DealItem> dealItems) {
        updateUI(dealItems);
    }

    @Override
    public void showErrorOnLoading(String error) {
        showError(error);
    }
}
