package com.example.taron.projecttwokeyvalue.delete;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.taron.projecttwokeyvalue.R;
import com.example.taron.projecttwokeyvalue.adapter.AdapterForKeyAndValueList;
import com.example.taron.projecttwokeyvalue.model.ModelForKeyValue;

import java.util.List;

public class DeleteItemToSwipe {

    public static void helperForRemoveItem(RecyclerView recyclerView, final Context context,
                                           final AdapterForKeyAndValueList adapter, final List<ModelForKeyValue> list) {
        final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.
                SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT | direction == ItemTouchHelper.RIGHT) {
                    remove(position, context, adapter, list);
                }
            }
        };
        final ItemTouchHelper itemTouch = new ItemTouchHelper(simpleCallback);
        itemTouch.attachToRecyclerView(recyclerView);
    }

    private static void remove(final int position, Context context,
                               final AdapterForKeyAndValueList adapter, final List<ModelForKeyValue> list) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.question_for_delete);
        builder.setPositiveButton(R.string.lis_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyItemRemoved(position);
                list.remove(position);
            }
        }).setNegativeButton(R.string.lis_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyItemRemoved(position + 1);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        }).setCancelable(false).show();
    }
}
