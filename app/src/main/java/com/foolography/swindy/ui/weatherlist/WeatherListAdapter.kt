package com.foolography.swindy.ui.weatherlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foolography.swindy.data.CityData
import com.foolography.swindy.databinding.WeatherListRowBinding

class WeatherListAdapter(
    private val items: List<CityData>,
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view = WeatherListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        val newsItem = items[position]
        holder.apply { bind(newsItem) }
    }

    inner class WeatherListViewHolder(private val binding: WeatherListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CityData) {
            binding.apply {
                cityData = item
                binding.cityCard.setOnClickListener { listener.onItemClick(item) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: CityData)
    }

}