package hanalyst.application.hanalystclub.Network;

import java.util.List;

import hanalyst.application.hanalystclub.Entity.Temperature;
import hanalyst.application.hanalystclub.Entity.remote.ClubUser;
import hanalyst.application.hanalystclub.Entity.remote.RGame;
import hanalyst.application.hanalystclub.Entity.remote.RGameType;
import hanalyst.application.hanalystclub.Entity.remote.RNotation;
import hanalyst.application.hanalystclub.Entity.remote.RPlayer;
import hanalyst.application.hanalystclub.Entity.remote.RTeam;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("clubusers/findOne?")
    Call<ClubUser> getUser(@Query("filter") String param);

    @GET("GameTypes")
    Call<List<RGameType>> getGameTypes();

    @GET("players?")
    Call<List<RPlayer>> getPlayerInATeam(@Query("filter") String param);

    @GET("teams/{teamId}/members")
    Call<List<RPlayer>> getMembersInATeam(@Path("teamId") String teamId);

    @FormUrlEncoded
    @POST("players")
    Call<RPlayer> addAPlayerToATeam(
            @Field("name") String fullName,
            @Field("tnumber") int tNumber,
            @Field("teamId") String teamId
    );

    @GET("teams/{teamId}")
    Call<RTeam> getTeamData(@Path("teamId") String teamId);

    @GET("teams")
    Call<List<RTeam>> getAllTeams();

    @FormUrlEncoded
    @POST("games")
    Call<RGame> saveGame(
            @Field("startTime") String startTime,
            @Field("endTime") String endTime,
            @Field("venue") String venue,
            @Field("ha") boolean ha,
            @Field("referee") String referee,
            @Field("temperature") String temperature,
            @Field("location") String location,
            @Field("gameType") String gameType,
            @Field("playingTeams[]") List<String> playingTeams
    );

    @GET("games")
    Call<List<RGame>> getGames();

    @FormUrlEncoded
    @POST("notations")
    Call<RNotation> saveNotation(
            @Field("what") String what,
            @Field("who") String who,
            @Field("where") String where,
            @Field("when") String when,
            @Field("gameId") String gameId
    );

    @GET("games/{gameId}/notations")
    Call<List<RNotation>> getNotations(@Path("gameId") String gameId);

}
