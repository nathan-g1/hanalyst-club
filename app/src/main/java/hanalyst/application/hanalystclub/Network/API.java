package hanalyst.application.hanalystclub.Network;

import hanalyst.application.hanalystclub.Entity.remote.ClubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("clubusers/findOne?")
    Call<ClubUser> getUser(@Query("filter") String param);
}
