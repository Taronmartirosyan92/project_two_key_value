package com.example.taron.projecttwokeyvalue.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taron.projecttwokeyvalue.R;
import com.example.taron.projecttwokeyvalue.activity.MainActivity;
import com.example.taron.projecttwokeyvalue.model.ModelForKeyValue;

import java.util.List;

public class AddKeyValueDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_key_value, container, false);
        final EditText keyEdit = view.findViewById(R.id.key_edit);
        final EditText valueEdit = view.findViewById(R.id.value_edit);
        final Button add = view.findViewById(R.id.add_button);
        final List<ModelForKeyValue> refreshList = ((MainActivity) getActivity()).getList();
        addOnClick(keyEdit, valueEdit, add, refreshList);
        return view;
    }

    private void addOnClick(final EditText keyEdit,
                            final EditText valueEdit, Button add,
                            final List<ModelForKeyValue> refreshList) {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String key = keyEdit.getText().toString();
                final String value = valueEdit.getText().toString();
                if (!key.isEmpty() && !value.isEmpty()) {
                    refreshList.add(new ModelForKeyValue(key, value));
                    dismiss();
                } else {
                    Toast.makeText(getActivity(), R.string.key_and_value, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
