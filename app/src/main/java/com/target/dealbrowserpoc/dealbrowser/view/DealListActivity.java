package com.target.dealbrowserpoc.dealbrowser.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.target.dealbrowserpoc.dealbrowser.R;

import com.target.dealbrowserpoc.dealbrowser.Utils.ItemDecorator;
import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;
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

    private boolean listview = true;
    private Menu menu;

    private DealsPresenter dealsPresenter;

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
        toggleRecyclerView();

        NetworkService networkService = new NetworkService();
        dealsPresenter = new DealsPresenter(networkService, this);
        dealsPresenter.fetchDealsFromNetwork();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_menu) {
            toggleRecyclerView();
            toggleMenuIcon();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggleRecyclerView() {
        if(listview) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mDealsRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ListDealItemAdapter(this, dealItemList, mTwoPane);
            mDealsRecyclerView.setAdapter(mAdapter);
            mDealsRecyclerView.addItemDecoration(new ItemDecorator(this));
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            mDealsRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new GridDealItemAdapter(this, dealItemList, mTwoPane);
            mDealsRecyclerView.setAdapter(mAdapter);
            mDealsRecyclerView.addItemDecoration(new ItemDecorator(this));
        }
        listview = !listview;
    }

    private void toggleMenuIcon() {
        if(!listview) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_list_24dp));
        }else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_grid_24dp));
        }
    }

    public void updateUI(List<DealItem> dealItems) {
        dealItemList.clear();
        dealItemList.addAll(dealItems);
        if(mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void showError(String error) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.cl_layout), error, Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dealsPresenter.fetchDealsFromNetwork();
                    }
                });

        snackbar.setActionTextColor(Color.RED);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
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
