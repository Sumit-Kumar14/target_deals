package com.target.dealbrowserpoc.dealbrowser.view;

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

    public static DealListItemAdapter newInstance(Context context, List<DealItem> items) {
        return new DealListItemAdapter(context, items);
    }

    public DealListItemAdapter(Context ctx, List<DealItem> items) {
        inflater = LayoutInflater.from(ctx);
        dealItems = items;
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
        Picasso.get().load(dealItem.getImage()).into(holder.productImage);
        holder.title.setText(dealItem.getTitle());
        holder.price.setText(dealItem.getSalePrice());
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
        private TextView title, price;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.deal_list_item_image_view);
            title = itemView.findViewById(R.id.deal_list_item_title);
            price = itemView.findViewById(R.id.deal_list_item_price);
        }
    }
}
