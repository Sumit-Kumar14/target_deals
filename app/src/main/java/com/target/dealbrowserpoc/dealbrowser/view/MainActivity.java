package com.target.dealbrowserpoc.dealbrowser.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.model.DealItem;
import com.target.dealbrowserpoc.dealbrowser.model.network.NetworkService;
import com.target.dealbrowserpoc.dealbrowser.presenter.DealsPresenter;

import java.util.List;

public class MainActivity extends Activity implements DealListFragment.OnFragmentInteractionListener, IDealsViewContract {

    private DealListFragment dealListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dealListFragment = DealListFragment.newInstance();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, dealListFragment)
                    .commit();
        }

        NetworkService networkService = new NetworkService();
        DealsPresenter dealsPresenter = new DealsPresenter(networkService, this);
        dealsPresenter.fetchDealsFromNetwork();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Product Id");
        alertDialog.setMessage(id);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    public void updateDealsList(List<DealItem> dealItems) {
        dealListFragment.updateUI(dealItems);
    }

    @Override
    public void showErrorOnLoading(String error) {
        dealListFragment.showError(error);
    }
}
