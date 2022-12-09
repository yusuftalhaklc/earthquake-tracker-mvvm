package com.yusuftalhaklc.depremtakp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusuftalhaklc.depremtakp.R
import com.yusuftalhaklc.depremtakp.model.Result
import com.yusuftalhaklc.depremtakp.view.MainScreenDirections
import kotlinx.android.synthetic.main.detail_row.view.*


class QuakeAdapter(private val quakesModelList: ArrayList<Result>,view:ViewModelStoreOwner,var fragmentView:View) :
    RecyclerView.Adapter<QuakeAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.detail_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val quakeVal = quakesModelList[position].mag

        if (quakeVal>=0 && quakeVal<2){
            holder.itemView.viewColor.setBackgroundResource(R.drawable.boxg)
            holder.itemView.quakeValue.setTextColor(Color.parseColor("#BFBDCB"))
        }
        else if (quakeVal>=2 && quakeVal<4){
            holder.itemView.viewColor.setBackgroundResource(R.drawable.boxp)
            holder.itemView.quakeValue.setTextColor(Color.parseColor("#8D8AFF"))
        }
        else if (quakeVal>=4 && quakeVal<5){
            holder.itemView.viewColor.setBackgroundResource(R.drawable.boxgreen)
            holder.itemView.quakeValue.setTextColor(Color.parseColor("#58D096"))
        }
        else if (quakeVal>=5 && quakeVal<6){
            holder.itemView.viewColor.setBackgroundResource(R.drawable.boxy)
            holder.itemView.quakeValue.setTextColor(Color.parseColor("#FECC8C"))
        }
        else if (quakeVal>=6){
            holder.itemView.viewColor.setBackgroundResource(R.drawable.boxr)
            holder.itemView.quakeValue.setTextColor(Color.parseColor("#F97689"))
        }



        holder.itemView.quakeValue.text = quakeVal.toString()
        holder.itemView.quakeLocation.text = quakesModelList[position].lokasyon
        holder.itemView.quakeTime.text = quakesModelList[position].date

        holder.itemView.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToDetailFragment(
                lokasyon = quakesModelList[position].lokasyon,
                derinlik = quakesModelList[position].depth.toFloat(),
                siddet = quakesModelList[position].mag.toFloat(),
                saat = quakesModelList[position].date,
                lat = quakesModelList[position].lat.toFloat(),
                lng =quakesModelList[position].lng.toFloat()
            )
            Navigation.findNavController(fragmentView).navigate(action)

        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return quakesModelList.size
    }
    fun refreshList(newList: List<Result>){
        quakesModelList.clear()
        quakesModelList.addAll(newList)
        notifyDataSetChanged()
    }

}