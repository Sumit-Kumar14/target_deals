package com.target.dealbrowserpoc.dealbrowser.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;

import java.util.List;

/**
 * @author Sumit Kumar
 */

public class GridDealItemAdapter extends DealsItemAdapter{
    GridDealItemAdapter(DealListActivity parent, List<DealItem> items, boolean twoPane) {
        super(parent, items, twoPane);
    }

    @NonNull
    @Override
    public DealsItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_deals_item, parent, false);
        return new DealsItemAdapter.ViewHolder(view);
    }
}
