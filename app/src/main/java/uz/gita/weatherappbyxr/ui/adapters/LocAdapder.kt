package uz.gita.weatherappbyxr.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.weatherappbyxr.R

import uz.gita.weatherappbyxr.database.models.geo_code.Location

class LocAdapder(): ListAdapter<Location, LocAdapder.Holder>(CallBack) {

    private var onClickListener: ((Location) -> Unit)? = null

    fun setOnClickListener(block:((Location) -> Unit) ){
        onClickListener = block
    }



    object CallBack : DiffUtil.ItemCallback<Location>(){
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.lon == newItem.lon && oldItem.lat == newItem.lat
        }

    }


    inner class Holder(view:View) :RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.city_name)
        val state: TextView = view.findViewById(R.id.state)
        val card: CardView = view.findViewById(R.id.card)

        fun bind(position: Int){
            name.text = getItem(position).name
            state.text = getItem(position).country

            card.setOnClickListener{
                onClickListener?.invoke(getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }
}