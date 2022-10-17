package com.austin.coffeshoptycoon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_layout_simulation.view.*
import java.util.zip.Inflater

class SimulationAdaptor(val context: Context):RecyclerView.Adapter<SimulationAdaptor.SimulationViewHolder>() {
    class SimulationViewHolder(val v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimulationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.activity_layout_simulation, parent, false)
        return SimulationViewHolder(v)

    }

    override fun onBindViewHolder(holder: SimulationViewHolder, position: Int) {
        with(Global.simulation[position]){
            holder.v.lblTime.text = time
            holder.v.lblCustomer.text = customer
        }
    }

    override fun getItemCount(): Int {
        return Global.simulation.size
    }
}