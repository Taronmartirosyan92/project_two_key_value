package com.example.taron.projecttwokeyvalue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taron.projecttwokeyvalue.R;
import com.example.taron.projecttwokeyvalue.model.ModelForKeyValue;

import java.util.List;

public class AdapterForKeyAndValueList extends RecyclerView.Adapter<AdapterForKeyAndValueList.KeyValueViewHolder> {
    private Context context;
    private List<ModelForKeyValue> list;

    public AdapterForKeyAndValueList(Context context, List<ModelForKeyValue> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public KeyValueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View view = inflater.inflate(R.layout.key_value_item, parent, false);
        return new KeyValueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyValueViewHolder holder, int position) {
        ModelForKeyValue model = list.get(position);
        holder.keyTextView.setText(model.getKey());
        holder.valueTextView.setText(model.getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class KeyValueViewHolder extends RecyclerView.ViewHolder {
        TextView keyTextView;
        TextView valueTextView;

        KeyValueViewHolder(View itemView) {
            super(itemView);
            keyTextView = itemView.findViewById(R.id.key_id);
            valueTextView = itemView.findViewById(R.id.value_id);
        }
    }
}
