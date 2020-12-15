package testappdev.id.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import testappdev.id.model.Data;

public interface Services {

    @GET("get_data")
    Call<List<Data>> getData();

    @FormUrlEncoded
    @POST("send_data")
    Call<POST> sendData(@Field("id") String id,
                        @Field("name") String name);
}
