package com.example.covid_19.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid_19.R
import org.json.JSONException
import org.json.JSONObject


class HomeFragment : Fragment() {
    private var tvTotalConfirmed: TextView? = null
    private var tvTotalDeaths: TextView? = null
    private var tvTotalRecoverd: TextView? = null
    private var progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_home, container, false)

        // Call View
        tvTotalConfirmed = root.findViewById(R.id.tvTotalConfirmed)
        tvTotalDeaths = root.findViewById(R.id.tvTotalDeaths)
        tvTotalRecoverd = root.findViewById(R.id.tvTotalRecoverd)
        progressBar = root.findViewById(R.id.progress_circular_home)

        //Call Volley
        data
        return root
    }

    private val data: Unit
        private get() {
            val queue = Volley.newRequestQueue(activity)
            val url = "https://corona.lmao.ninja/v2/all"
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { response ->
                    progressBar!!.visibility = View.GONE
                    try {
                        val jsonObject = JSONObject(response)
                        tvTotalConfirmed!!.text = jsonObject.getString("cases")
                        tvTotalDeaths!!.text = jsonObject.getString("deaths")
                        tvTotalRecoverd!!.text = jsonObject.getString("recovered")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    progressBar!!.visibility = View.GONE
                    Log.d("Error Response", error.toString())
                })
            queue.add(stringRequest)
        }
}