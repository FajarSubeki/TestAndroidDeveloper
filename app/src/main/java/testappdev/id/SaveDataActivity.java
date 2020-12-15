package testappdev.id;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import testappdev.id.model.Data;
import testappdev.id.mvp.DataPresenter;
import testappdev.id.mvp.IntractorImpl;
import testappdev.id.mvp.MainContract;

public class SaveDataActivity extends AppCompatActivity implements MainContract.DataView{

    private Spinner mSpinData;
    private String mName;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        mSpinData = findViewById(R.id.spinnerData);
        Button btnSave = findViewById(R.id.btnSave);
        MainContract.DataPresenter dataPresenter = new DataPresenter(this, new IntractorImpl());
        dataPresenter.getData(SaveDataActivity.this);

        btnSave.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mId)){
                if (mName.equalsIgnoreCase("Pilih Item")){
                    Toast.makeText(this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("keyName", mName);
                intent.putExtra("keyId", mId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void isSuccess(List<Data> data) {

        Data data1 = new Data("0", "Pilih Item");
        data.add(data1);

        SpinnerAdapter spinnerAdapter = new testappdev.id.adapter.SpinnerAdapter(SaveDataActivity.this, R.layout.item_spinner, data);
        mSpinData.setAdapter(spinnerAdapter);
        mSpinData.setSelection(10);
        mSpinData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Data data1 = (Data) adapterView.getSelectedItem();
                mName = data1.getName();
                mId = data1.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void isFailure(String message) {

    }

    @Override
    public void isSuccessSendData() {

    }
}