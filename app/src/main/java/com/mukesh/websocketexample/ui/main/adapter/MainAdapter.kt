package com.mukesh.websocketexample.ui.main.adapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.utils.Utility
import com.mukesh.websocketexample.R
import com.mukesh.websocketexample.model.Citydata
import com.mukesh.websocketexample.ui.main.view.ChartActivity
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val cityData: ArrayList<Citydata>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cityData : Citydata) {
            itemView.textViewCity.text = cityData.cityName

            //Conver string value to decimal and also set proper color code
            val aqiDecimalStr = Utility().stringToDecimal(cityData.aqi)
            val colorCode = Utility().getColorCodes(aqiDecimalStr)
            itemView.textViewAqi.text = aqiDecimalStr
            itemView.textViewAqi.setTextColor(colorCode)

            //Click action for lost item, pass the city name
            itemView.setOnClickListener(View.OnClickListener {
                val intent = Intent(itemView.context, ChartActivity::class.java)
                intent.putExtra(CITY_NAME,cityData.cityName)
                itemView.context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = cityData.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(cityData[position])

    fun addCityData(list: ArrayList<Citydata>) {
        cityData.clear()
        cityData.addAll(list)
    }

    companion object{
        val CITY_NAME = "city_name"
    }
}