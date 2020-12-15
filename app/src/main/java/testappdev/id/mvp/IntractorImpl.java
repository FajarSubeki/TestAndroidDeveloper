package testappdev.id.mvp;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;
import testappdev.id.MainActivity;
import testappdev.id.api.ApiClient;
import testappdev.id.api.Services;
import testappdev.id.model.Data;

public class IntractorImpl implements MainContract.Intractor{


    @Override
    public void getData(Context context, OnDataListener listener) {

        Call<List<Data>> dataCall = ApiClient.getClient().create(Services.class).getData();
        dataCall.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful() && response.body() != null){
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFailure("Failure");
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void sendData(Context context, String id, String name, OnDataListener listener) {

        Call<POST> dataCall = ApiClient.getClient().create(Services.class).sendData(id, name);
        dataCall.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if (response.isSuccessful() && response.body() != null){
                    listener.onSuccessSendData();
                    return;
                }
                listener.onFailure(response.raw().toString());
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });

    }
}
