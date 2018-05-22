package com.target.dealbrowserpoc.dealbrowser.model.network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Sumit Kumar
 */

public interface INetworkServiceInterface {

    @GET("api/deals")
    Call<DealsContainer> dealsList();

}