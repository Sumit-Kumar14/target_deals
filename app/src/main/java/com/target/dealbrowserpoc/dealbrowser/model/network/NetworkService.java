package com.target.dealbrowserpoc.dealbrowser.model.network;

import com.target.dealbrowserpoc.dealbrowser.Utils.Constants;
import com.target.dealbrowserpoc.dealbrowser.presenter.DealsPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Sumit Kumar
 */

public class NetworkService {

    private static Retrofit retrofitClient;

    private static Retrofit getRetrofitClient() {
        if(retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }

    public void getDealsList(final DealsPresenter dealsPresenter) {
        INetworkServiceInterface networkServiceInterface = getRetrofitClient().create(INetworkServiceInterface.class);
        Call<DealsContainer> apiCall = networkServiceInterface.dealsList();
        apiCall.enqueue(new Callback<DealsContainer>() {
            @Override
            public void onResponse(Call<DealsContainer> call, Response<DealsContainer> response) {
                if(response.body() != null) {
                    dealsPresenter.onSuccess(response.body().getDealItemList());
                }else {
                    dealsPresenter.onFailure(new NullPointerException());
                }
            }

            @Override
            public void onFailure(Call<DealsContainer> call, Throwable throwable) {
                dealsPresenter.onFailure(throwable);
            }
        });
    }
}