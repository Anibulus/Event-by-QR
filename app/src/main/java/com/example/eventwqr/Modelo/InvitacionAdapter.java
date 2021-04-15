package com.example.eventwqr.Modelo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventwqr.R;

import java.util.List;

public class InvitacionAdapter extends RecyclerView.Adapter<InvitacionAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView cantidadLlego, cantidadTotal, ID, Mesa, grupo, espacioMesa;
        private ImageView foto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=(ImageView)itemView.findViewById(R.id.imageView);

            cantidadTotal=(TextView)itemView.findViewById(R.id.txtCantidadDeInvitadosTotal);
            cantidadLlego=(TextView)itemView.findViewById(R.id.txtCantidadHanLlegado);
            Mesa=(TextView)itemView.findViewById(R.id.txtMesa);
            espacioMesa=(TextView)itemView.findViewById(R.id.txtEspacioMesa);
            ID=(TextView)itemView.findViewById(R.id.txtID);
            grupo=(TextView)itemView.findViewById(R.id.txtGrupo);
        }//Fin del contructor
    }//Fin de la clase interna

    public List<Invitacion> invitados;

    public InvitacionAdapter(List<Invitacion> i){
        this.invitados=i;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitados,parent,false);
        ViewHolder vh= new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Invitados es la lista
        holder.cantidadLlego.setText("Asistieron: "+String.valueOf(invitados.get(position).getAsisten()));//Asisten
        holder.cantidadTotal.setText("Cantidad: "+String.valueOf(invitados.get(position).getCantidadInvitados()));//Cantidad invitados
        holder.Mesa.setText("Mesa: "+String.valueOf(invitados.get(position).getMesa().getIdMesa()));//Mesa
        holder.espacioMesa.setText("Espacio en la mesa: "+String.valueOf(invitados.get(position).getMesa().getCantidadTotal()));//Espacio total en la mesa
        holder.grupo.setText("Grupo: "+String.valueOf(invitados.get(position).getFamilia().getNombre()));
        holder.ID.setText("ID: "+String.valueOf(invitados.get(position).getFamilia().getIdGrupo()));
    }

    //Cantidad de elementos que se procesaran
    @Override
    public int getItemCount() {
        return invitados.size();
    }
}
