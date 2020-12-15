package testappdev.id.mvp;

import android.content.Context;

import java.util.List;

import testappdev.id.model.Data;

public interface MainContract {

    interface DataPresenter{
        void getData(Context context);
        void sentData(Context context, String id, String name);
    }

    interface DataView{
        void isSuccess(List<Data> data);
        void isFailure(String message);
        void isSuccessSendData();
    }

    interface Intractor{

        interface OnDataListener{
            void onSuccess(List<Data> data);
            void onSuccessSendData();
            void onFailure(String message);
        }

        void getData(Context context, OnDataListener listener);
        void sendData(Context context, String id, String name, OnDataListener listener);

    }


}
