package testappdev.id.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import testappdev.id.R;
import testappdev.id.model.Data;

public class SpinnerAdapter extends ArrayAdapter {

    List<Data> dataList;
    TextView tvName;

    public SpinnerAdapter(Context context, int textViewResourceId, List<Data> data) {
        super(context, textViewResourceId, data);
        this.dataList = data;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, parent, false);

        tvName = v.findViewById(R.id.rbSpinner);
        tvName.setText(dataList.get(position).getName());

        return v;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


}
