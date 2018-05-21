package com.target.dealbrowserpoc.dealbrowser.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.model.DealItem;

import java.util.List;

public class DealListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView dealsRecyclerView;

    public static DealListFragment newInstance() {
        return new DealListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deal_list, null);
        dealsRecyclerView = rootView.findViewById(R.id.rv_deals);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateUI(List<DealItem> dealItems) {
        Log.d(DealListFragment.class.getSimpleName(), dealItems.toString());
    }

    public void showError(String error) {

    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }
}