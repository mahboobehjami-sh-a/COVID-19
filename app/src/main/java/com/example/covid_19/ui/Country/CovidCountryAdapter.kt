package com.example.covid_19.ui.Country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import java.util.*


class CovidCountryAdapter(var covidCountries: ArrayList<CovidCountry>) :
    RecyclerView.Adapter<CovidCountryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_covid_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val covidCountry = covidCountries[position]
        holder.tvTotalCases.text = covidCountry.getmCases()
        holder.tvCountryName.text = covidCountry.getmCovidCountry()
    }

    override fun getItemCount(): Int {
        return covidCountries.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvTotalCases: TextView
        var tvCountryName: TextView

        init {
            tvTotalCases = itemView.findViewById(R.id.tvTotalCases)
            tvCountryName = itemView.findViewById(R.id.tvCountryName)
        }
    }

}

