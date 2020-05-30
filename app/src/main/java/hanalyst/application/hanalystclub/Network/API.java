package hanalyst.application.hanalystclub.Network;

import java.util.List;

import hanalyst.application.hanalystclub.Entity.remote.ClubUser;
import hanalyst.application.hanalystclub.Entity.remote.RGameType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("clubusers/findOne?")
    Call<ClubUser> getUser(@Query("filter") String param);

    @GET("GameTypes")
    Call<List<RGameType>> getGameTypes();
}
