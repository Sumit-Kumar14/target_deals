package com.target.dealbrowserpoc.dealbrowser.presenter;

import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;

import java.util.List;

/**
 * @author Sumit Kumar
 */

public interface IDealsPresenterContract {
    void onSuccess(List<DealItem> dealItemList);

    void onFailure(Throwable throwable);
}