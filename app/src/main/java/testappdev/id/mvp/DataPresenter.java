package testappdev.id.mvp;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import testappdev.id.model.Data;

public class DataPresenter implements MainContract.DataPresenter, MainContract.Intractor.OnDataListener{

    private final MainContract.DataView dataView;
    private final MainContract.Intractor intractor;

    public DataPresenter(MainContract.DataView dataView, MainContract.Intractor intractor) {
        this.dataView = dataView;
        this.intractor = intractor;
    }

    @Override
    public void getData(Context context) {
        intractor.getData(context, this);
    }

    @Override
    public void sentData(Context context, String id, String name) {
        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name)){
            intractor.sendData(context, id, name, this);
        }
    }

    @Override
    public void onSuccess(List<Data> data) {
        dataView.isSuccess(data);
    }

    @Override
    public void onSuccessSendData() {
        dataView.isSuccessSendData();
    }

    @Override
    public void onFailure(String message) {
        dataView.isFailure(message);
    }
}
