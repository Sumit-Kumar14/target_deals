package com.target.dealbrowserpoc.dealbrowser.model.network;

import com.target.dealbrowserpoc.dealbrowser.model.DealItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Sumit Kumar
 */

public interface INetworkServiceInterface {

    @GET("api/deals")
    Call<DealsContainer> dealsList();

}