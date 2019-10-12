package com.example.owner_app.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.owner_app.R;
import com.example.owner_app.listitems.owner_listitem;

import java.util.List;

public class adpt_ownerREcycler extends RecyclerView.Adapter<adpt_ownerREcycler.ViewHolder> {
    private Context context;
    private List<owner_listitem> listitem;

    public adpt_ownerREcycler(Context context, List<owner_listitem> listitem) {
        this.context = context;
        this.listitem = listitem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hogozat_to_owner,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        owner_listitem listitem1=listitem.get(i);
        holder.player_name.setText(listitem1.getPlayer_name());
        holder.player_number.setText(listitem1.getPlayer_phone());
        holder.elsa3ah.setText(listitem1.getElsa3ah());
        holder.el_date.setText(listitem1.getDate());
    }

    @Override
    public int getItemCount() {
        return listitem.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView player_name,player_number,elsa3ah,el_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            player_name=itemView.findViewById(R.id.player_name);
            player_number=itemView.findViewById(R.id.playe_number);
            elsa3ah=itemView.findViewById(R.id.L_sa3ah);
            el_date=itemView.findViewById(R.id.L_Date);
        }
    }
}
