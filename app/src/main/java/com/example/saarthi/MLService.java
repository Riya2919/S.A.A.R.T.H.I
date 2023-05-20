package com.example.saarthi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MLService {

    @POST("http://620abf4e-88b6-4c3d-a636-643c54352362.eastus2.azurecontainer.io/score")
    Call<YourResponseModel> makePrediction(@Header("Authorization") String apiKey, @Body YourRequestModel request);
}
