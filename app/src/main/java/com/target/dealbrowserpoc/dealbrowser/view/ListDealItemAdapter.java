package com.target.dealbrowserpoc.dealbrowser.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;

/**
 * @author Sumit Kumar
 */

public class ListDealItemAdapter extends DealsItemAdapter {

    ListDealItemAdapter(DealListActivity parent, List<DealItem> items, boolean twoPane) {
        super(parent, items, twoPane);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.deal_list_item, parent, false);
        return new ViewHolder(view);
    }
}
