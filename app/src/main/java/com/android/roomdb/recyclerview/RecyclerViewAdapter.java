package com.android.roomdb.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.roomdb.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<RecycleModal> textList = new ArrayList<>();
    private ArrayList<RecycleModal> selectedList = new ArrayList<>();
    public Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView uid;
        public TextView name;
        public TextView email_dis;
        public CheckBox check_box;

        public MyViewHolder(View itemView) {
            super(itemView);
            uid = itemView.findViewById(R.id.textView2);
            name = itemView.findViewById(R.id.textView3);
            email_dis = itemView.findViewById(R.id.textView4);
            check_box = itemView.findViewById(R.id.checkbox_data);
        }
    }

    public RecyclerViewAdapter(Context context, ArrayList<RecycleModal> textList) {
        this.context = context;
        this.textList = textList;
        selectedList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        RecycleModal current = textList.get(position);
        holder.uid.setText(Integer.toString((current.getUid())));
        holder.name.setText((current.getName()));
        holder.email_dis.setText(current.getEmail());
        holder.check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check_box.setOnCheckedChangeListener(null);

                holder.check_box.setChecked(current.isSelected());

                holder.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //set your object's last status
                        current.setSelected(isChecked);
                        if(isChecked){
                            selectedList.add(current);
                        }
                        else{
                            selectedList.remove(current);
                        }
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return textList.size();
    }

    public ArrayList<RecycleModal> getSelectedData() {
        return selectedList;
    }
}
