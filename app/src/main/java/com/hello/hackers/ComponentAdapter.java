package com.hello.hackers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hello.hackers.R;

import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.CompnentViewHolder> {
    Context context;
    List<Component> componentList;

    public ComponentAdapter(Context context, List<Component> componentList) {
        this.context = context;
        this.componentList = componentList;
    }

    @NonNull
    @Override
    public ComponentAdapter.CompnentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_component,parent,false);
        return new CompnentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComponentAdapter.CompnentViewHolder holder, int position) {

        Component component=componentList.get(position);
        holder.howworkTv.setText(component.getHow_it_works());
        holder.sideeffecttv.setText(component.getSide_effects());
        holder.usedfortv.setText(component.getUsed_for());

    }

    @Override
    public int getItemCount() {
        return componentList.size();
    }

    public class CompnentViewHolder extends RecyclerView.ViewHolder{
          TextView usedfortv,sideeffecttv,instTv,howworkTv;
        public CompnentViewHolder(@NonNull View itemView) {
            super(itemView);
            usedfortv=itemView.findViewById(R.id.used_for);
            sideeffecttv=itemView.findViewById(R.id.side_effects);
            howworkTv=itemView.findViewById(R.id.how_it_works);
        }
    }

}
