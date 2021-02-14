package com.jallalla.jallallavotos.ListTasks.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jallalla.jallallavotos.Entities.ListTaskDetail;
import com.jallalla.jallallavotos.R;
import com.jallalla.jallallavotos.Utils.GeneralUtils;

import java.util.ArrayList;

public class ListTaskAdapter extends RecyclerView.Adapter<ListTaskAdapter.ListTaskViewHolder> {

    ArrayList<ListTaskDetail> listTaskDetails;
    Context context;
    GeneralUtils generalUtils =  new GeneralUtils();

    public ListTaskAdapter(ArrayList<ListTaskDetail> listTaskDetails, Context context) {
        this.listTaskDetails = listTaskDetails;
        this.context =  context;
    }

    @Override
    public ListTaskAdapter.ListTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesa,parent,false);
        ListTaskViewHolder viewHolder=new ListTaskViewHolder(v);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(ListTaskAdapter.ListTaskViewHolder holder, int position) {
        ListTaskDetail misTareas = listTaskDetails.get(position);
        final Integer mesa = misTareas.getIdMesa();
        final String colegio = misTareas.getNombreUnidad();
        final String distrito = misTareas.getCodigoDistrito();
        holder.lbl_mesa.setText(context.getString(R.string.item_mesa) +" "+mesa);
        holder.lbl_distrito.setText(distrito);
        holder.lbl_colegio.setText(colegio);
    }

    @Override
    public int getItemCount() {
        return listTaskDetails.size();
    }

    public static class ListTaskViewHolder extends RecyclerView.ViewHolder{

        public TextView lbl_mesa;
        public TextView lbl_distrito;
        public TextView lbl_colegio;

        public ListTaskViewHolder(View view) {
            super(view);
            lbl_mesa = (TextView) view.findViewById(R.id.lbl_mesa);
            lbl_distrito = (TextView) view.findViewById(R.id.lbl_distrito);
            lbl_colegio = (TextView) view.findViewById(R.id.lbl_colegio);
        }
    }
}