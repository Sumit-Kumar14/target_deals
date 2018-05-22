package com.target.dealbrowserpoc.dealbrowser.view;

import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;

import java.util.List;

/**
 * @author Sumit Kumar
 */

public interface IDealsViewContract {
    void updateDealsList(List<DealItem> dealItems);

    void showErrorOnLoading(String error);
}