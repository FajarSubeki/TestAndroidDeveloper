package testappdev.id;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import testappdev.id.model.Data;
import testappdev.id.mvp.DataPresenter;
import testappdev.id.mvp.IntractorImpl;
import testappdev.id.mvp.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.DataView {

    private String nameUser;
    private EditText etItemData;
    private TextView mTvNameItem;
    private LinearLayout mLlItem;
    private LinearLayout mLlChoseItem;
    private final int REQUEST_SAVE_DATA = 1000;
    private  Button btnSave;

    MainContract.DataPresenter dataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataPresenter = new DataPresenter(this, new IntractorImpl());
        etItemData = findViewById(R.id.etItemData);
        mTvNameItem = findViewById(R.id.tvNameItem);
        btnSave = findViewById(R.id.btnSave);
        mLlItem = findViewById(R.id.llDataItem);
        mLlChoseItem = findViewById(R.id.llChooseItem);

        dataPresenter.getData(MainActivity.this);

        btnSave.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SaveDataActivity.class);
            startActivityForResult(intent, REQUEST_SAVE_DATA);
        });
    }

    @Override
    public void isSuccess(List<Data> data) {

        List<String> list = new ArrayList<>();

        for (int i=0; i<data.size(); i++){
            list.add(data.get(i).getName());
            nameUser = TextUtils.join(", ", list);
        }

        if (!TextUtils.isEmpty(nameUser)){
            etItemData.setText(nameUser);
        }
    }

    @Override
    public void isFailure(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isSuccessSendData() {
        Toast.makeText(this, "Success Send Data", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_SAVE_DATA && resultCode == RESULT_OK) {
            if (data != null){
                String sName = data.getStringExtra("keyName");
                String sId = data.getStringExtra("keyId");

                mLlChoseItem.setVisibility(View.VISIBLE);
                mLlItem.setVisibility(View.GONE);
                mTvNameItem.setText(sName);
                btnSave.setText("Kirim");

                btnSave.setOnClickListener(view -> dataPresenter.sentData(MainActivity.this, sName, sId));

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}