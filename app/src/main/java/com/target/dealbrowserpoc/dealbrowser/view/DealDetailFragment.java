package com.target.dealbrowserpoc.dealbrowser.view;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.model.datasource.DealItem;

public class DealDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Content of this fragment.
     */
    private DealItem mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = getArguments().getParcelable(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deal_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.tv_price)).setText(mItem.getSalePrice());
            ((TextView) rootView.findViewById(R.id.deal_list_item_title)).setText(mItem.getTitle());
            ((TextView) rootView.findViewById(R.id.deal_list_item_description)).setText(mItem.getDescription());
            if(mItem.getOriginalPrice() != null) {
                TextView originalPrice = (TextView) rootView.findViewById(R.id.tv_reg_price);
                originalPrice.setText(mItem.getOriginalPrice());
                originalPrice.setPaintFlags(originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else {
                rootView.findViewById(R.id.tv_reg_price_tag).setVisibility(View.GONE);
                rootView.findViewById(R.id.tv_reg_price).setVisibility(View.GONE);
            }
            Picasso.get().load("https://3.imimg.com/data3/IP/PV/MY-10556739/men-t-shirts-250x250.png").into((ImageView) rootView.findViewById(R.id.im_product));
        }

        return rootView;
    }
}