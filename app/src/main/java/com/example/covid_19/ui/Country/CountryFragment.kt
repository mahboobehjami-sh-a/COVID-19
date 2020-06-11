import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid_19.R
import com.example.covid_19.ui.Country.CovidCountry
import com.example.covid_19.ui.Country.CovidCountryAdapter
import org.json.JSONArray
import org.json.JSONException
import java.util.*


class CountryFragment : Fragment() {
    var rvCovidCountry: RecyclerView? = null
    var progressBar: ProgressBar? = null
    var covidCountries: ArrayList<CovidCountry>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_country, container, false)

        //Call View
        rvCovidCountry = root.findViewById(R.id.rvCovidCountry)
        progressBar = root.findViewById(R.id.progress_circular_country)
        rvCovidCountry.setLayoutManager(LinearLayoutManager(activity))

        // Call Volley(HTTP Library) Method
        dataFromServer
        return root
    }

    private fun showRecyclerView() {
        val covidCountryAdapter = CovidCountryAdapter(covidCountries)
        rvCovidCountry!!.adapter = covidCountryAdapter
    }

    private val dataFromServer: Unit
        private get() {
            val url = "https://corona.lmao.ninja/v2/countries"
            covidCountries = ArrayList<CovidCountry>()
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { response ->
                    progressBar!!.visibility = View.GONE
                    if (response != null) {
                        Log.e(TAG, "on Response:$response")
                        try {
                            val jsonArray = JSONArray(response)
                            for (i in 0 until jsonArray.length()) {
                                val data = jsonArray.getJSONObject(i)
                                covidCountries!!.add(
                                    CovidCountry(
                                        data.getString("country"),
                                        data.getString("cases")
                                    )
                                )
                            }
                            showRecyclerView()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                },
                Response.ErrorListener { error ->
                    progressBar!!.visibility = View.GONE
                    Log.e(TAG, "onResponse: $error")
                })
            Volley.newRequestQueue(activity).add(stringRequest)
        }

    companion object {
        private val TAG = CountryFragment::class.java.simpleName
    }
}