package com.target.dealbrowserpoc.dealbrowser.presenter;

import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;
import com.target.dealbrowserpoc.dealbrowser.model.network.NetworkService;
import com.target.dealbrowserpoc.dealbrowser.view.IDealsViewContract;

import java.util.List;

/**
 * @author Sumit Kumar
 */

public class DealsPresenter implements IDealsPresenterContract{

    private NetworkService networkService;
    private IDealsViewContract viewContract;

    public DealsPresenter(NetworkService networkService, IDealsViewContract viewContract) {
        this.networkService = networkService;
        this.viewContract = viewContract;
    }

    public void fetchDealsFromNetwork() {
        networkService.getDealsList(this);
    }

    @Override
    public void onSuccess(List<DealItem> dealItemList) {
        viewContract.updateDealsList(dealItemList);
    }

    @Override
    public void onFailure(Throwable throwable) {
        viewContract.showErrorOnLoading(throwable.getMessage());
    }
}