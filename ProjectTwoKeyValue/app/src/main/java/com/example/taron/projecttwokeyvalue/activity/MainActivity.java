package com.example.taron.projecttwokeyvalue.activity;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.taron.projecttwokeyvalue.fragment.AddKeyValueDialogFragment;
import com.example.taron.projecttwokeyvalue.sp.CurrentSharedPreferences;
import com.example.taron.projecttwokeyvalue.delete.DeleteItemToSwipe;
import com.example.taron.projecttwokeyvalue.R;
import com.example.taron.projecttwokeyvalue.adapter.AdapterForKeyAndValueList;
import com.example.taron.projecttwokeyvalue.model.ModelForKeyValue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String FR_TAG = "tag";
    private AddKeyValueDialogFragment dialogFragment;
    public List<ModelForKeyValue> list;
    private AdapterForKeyAndValueList adapter;
    private RecyclerView recyclerView;
    private SharedPreferences sp;
    private CurrentSharedPreferences cSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final FloatingActionButton fab = findViewById(R.id.fab);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        cSP = new CurrentSharedPreferences();
        setSupportActionBar(toolbar);
        setList();
        initializationAdapter();
        goToTheFragment(fab);
        DeleteItemToSwipe.helperForRemoveItem(recyclerView, this, adapter, list);
    }

    private void initializationAdapter() {
        final LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.key_value_list);
        adapter = new AdapterForKeyAndValueList(this, getList());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void goToTheFragment(FloatingActionButton fab) {
        final FragmentManager fragmentTransaction = (this.getFragmentManager());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFragment = new AddKeyValueDialogFragment();
                dialogFragment.show(fragmentTransaction, FR_TAG);
                if (!dialogFragment.isCancelable()) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void setList() {
        if (cSP.getArrList(sp) != null) {
            list = cSP.getArrList(sp);
        } else {
            list = new ArrayList<>();
        }
    }

    public List<ModelForKeyValue> getList() {
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        cSP.saveArrList((ArrayList<ModelForKeyValue>) list, sp);
    }

}
