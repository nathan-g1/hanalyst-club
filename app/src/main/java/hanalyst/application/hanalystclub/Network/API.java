package hanalyst.application.hanalystclub.Network;

import java.util.List;

import hanalyst.application.hanalystclub.Entity.remote.ClubUser;
import hanalyst.application.hanalystclub.Entity.remote.RGameType;
import hanalyst.application.hanalystclub.Entity.remote.RPlayer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @GET("clubusers/findOne?")
    Call<ClubUser> getUser(@Query("filter") String param);

    @GET("GameTypes")
    Call<List<RGameType>> getGameTypes();

    @GET("players?")
    Call<List<RPlayer>> getPlayerInATeam(@Query("filter") String param);

    @FormUrlEncoded
    @POST("players")
    Call<RPlayer> addAPlayerToATeam(
            @Field("name") String fullName,
            @Field("tnumber") int tNumber,
            @Field("teamId") String teamId
    );

}
