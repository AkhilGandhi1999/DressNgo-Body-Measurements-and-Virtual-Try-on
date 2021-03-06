package com.example.bodymeasurements_vton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {

    private Float[] data;
    public ListAdapter(Float[] data){
        this.data = data.clone();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return RecyclerViewData.parts.length;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView t1;
        private final TextView t2;

         RecyclerViewData obj  = new RecyclerViewData(data);

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.label);
            t2 = (TextView) itemView.findViewById(R.id.measure);
            itemView.setOnClickListener(this);

        }
        public void bindView(int position){
            t1.setText(obj.parts[position]);
            t2.setText(String.valueOf(obj.measurements[position]).concat(" cm"));

        }

        @Override
        public void onClick(View view) {

        }
    }
}
