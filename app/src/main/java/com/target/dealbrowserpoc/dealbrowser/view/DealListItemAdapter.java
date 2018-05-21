package com.target.dealbrowserpoc.dealbrowser.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.model.DealItem;

public class DealListItemAdapter extends RecyclerView.Adapter<DealListItemAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<DealItem> dealItems;
    private boolean mTwoPane;
    private DealListActivity mParentActivity;

    DealListItemAdapter(DealListActivity parent, List<DealItem> items, boolean twoPane) {
        this.inflater = LayoutInflater.from(parent);
        this.dealItems = items;
        this.mTwoPane = twoPane;
        this.mParentActivity = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.deal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DealItem dealItem = dealItems.get(position);
        //Picasso.get().load(dealItem.getImage()).into(holder.productImage);
        Picasso.get().load("https://3.imimg.com/data3/IP/PV/MY-10556739/men-t-shirts-250x250.png").into(holder.productImage);
        holder.title.setText(dealItem.getTitle());
        holder.price.setText(dealItem.getSalePrice());
        holder.aisle.setText(dealItem.getAisle().toUpperCase());
        holder.itemView.setTag(dealItems.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dealItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView title, price, aisle;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.deal_list_item_image_view);
            title = itemView.findViewById(R.id.deal_list_item_title);
            price = itemView.findViewById(R.id.deal_list_item_price);
            aisle = itemView.findViewById(R.id.tv_aisle);
        }
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DealItem item = (DealItem) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(DealDetailFragment.ARG_ITEM_ID, item);
                DealDetailFragment fragment = new DealDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.deal_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, DealDetailActivity.class);
                intent.putExtra(DealDetailFragment.ARG_ITEM_ID, item);

                context.startActivity(intent);
            }
        }
    };
}
