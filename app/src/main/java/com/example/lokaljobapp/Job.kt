package com.example.lokaljobapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Job : Fragment() {

    private lateinit var jobAdapter: JobAdapter
    private val jobList = mutableListOf<JobData>()
    private var currentPage = 1
    private var isLoading = false
    private val url = "https://testapi.getlokalapp.com/common/jobs?page="

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_job, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        progressBar = view.findViewById(R.id.progressBar)
        emptyView = view.findViewById(R.id.emptyView)


        recyclerView.layoutManager = LinearLayoutManager(context)
        // Pass the context to the JobAdapter
        jobAdapter = JobAdapter(jobList, requireContext())
        recyclerView.adapter = jobAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading && layoutManager.findLastCompletelyVisibleItemPosition() == jobList.size - 1) {
                    // Load more data
                    currentPage++
                    getJobData(currentPage)
                }
            }
        })

        getJobData(currentPage)

        return view
    }

    private fun getJobData(page: Int) {
        isLoading = true
        progressBar.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET, "$url$page",
            { response ->
                try {
                    Log.d("Response", "Full JSON Response: $response")

                    val jsonObject = JSONObject(response)
                    val resultsArray = jsonObject.optJSONArray("results")
                    if (resultsArray != null && resultsArray.length() > 0) {
                        for (i in 0 until resultsArray.length()) {
                            val jobObject = resultsArray.getJSONObject(i)
                            val companyName = jobObject.optString("company_name", "N/A")
                            val title = jobObject.optString("title", "N/A")
                            val primaryDetails = jobObject.optJSONObject("primary_details")
                            val location = primaryDetails?.optString("Place", "N/A") ?: "N/A"
                            val salary = primaryDetails?.optString("Salary", "N/A")?.replace("â\u0082¹", "\u20B9") ?: "N/A"
                            val phone = jobObject.optString("whatsapp_no", "N/A")
                            val jobtype = jobObject.optString("job_type", "N/A")
                            val jobrole = jobObject.optString("job_role", "N/A")
                            val jobcategory = jobObject.optString("job_category", "N/A")
                            val jobhours = jobObject.optString("job_hours", "N/A")
                            val openingsCount = jobObject.optInt("openings_count", -1)
                            val experience = jobObject.optString("experience", "N/A")
                            val qualification =jobObject.optString("qualification","N/A")
                            val salarymax=jobObject.optInt("salary_max",-1)
                            val salarymin=jobObject.optInt("salary_min",-1)
                            val shifttiming=jobObject.optString("qualification","N/A")
                            val joblocationslug=jobObject.optString("job_location_slug", "N/A")
                            val other_details=jobObject.optString("other_details", "N/A")


                            jobList.add(JobData(
                                company_name = companyName,
                                title = title,
                                location = location,
                                salary = salary,
                                phone = phone,
                                job_type = jobtype,
                                job_role = jobrole,
                                job_category = jobcategory,
                                job_hours = jobhours,
                                openings_count = openingsCount,
                                experience=experience,
                                qualification=qualification,
                                salary_max=salarymax,
                                salary_min=salarymin,
                                shift_timing=shifttiming,
                                job_location_slug=joblocationslug,
                                other_details=other_details

                            ))
                        }
                        jobAdapter.notifyDataSetChanged()
                    } else {
                        if (jobList.isEmpty()) {
                            emptyView.visibility = View.VISIBLE
                        }
                        Toast.makeText(context, "No job data available", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {

                    Log.e("ResponseError", "Error parsing JSON response", e)
                    Toast.makeText(context, "Failed to parse data", Toast.LENGTH_SHORT).show()
                }
                isLoading = false
                progressBar.visibility = View.GONE
            },
            { error ->
                Log.e("VolleyError", "Error: ${error.localizedMessage}")
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                isLoading = false
                progressBar.visibility = View.GONE
            })

        queue.add(stringRequest)
    }
}
