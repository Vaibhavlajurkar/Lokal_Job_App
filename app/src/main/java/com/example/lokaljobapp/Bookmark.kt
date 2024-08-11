// Bookmark.kt
package com.example.lokaljobapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Bookmark : Fragment() {

    private lateinit var bookmarkAdapter: JobAdapter
    private val bookmarkedList = mutableListOf<JobData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView1)

        recyclerView.layoutManager = LinearLayoutManager(context)

        bookmarkAdapter = JobAdapter(bookmarkedList, requireContext())
        recyclerView.adapter = bookmarkAdapter

        loadBookmarkedJobs()

        return view
    }

    private fun loadBookmarkedJobs() {
        val dbHelper = DatabaseHelper(requireContext())
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            null, null, null, null, null, null
        )

        with(cursor) {
            while (moveToNext()) {
                val companyName = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_COMPANY_NAME))
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val location = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION))
                val salary = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_SALARY))
                val phone = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE))
                val jobType = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_JOB_TYPE))
                val jobRole = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_JOB_ROLE))
                val jobcategory = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_JOB_CATEGORY))
                val jobhours = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_JOB_HOURS))
                val openingsCount = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_OPENINGS_COUNT))
                val experience = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPERIENCE))
                val qualification = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUALIFICATION))
                val salarymax = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_SALARY_MAX))
                val salarymin = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_SALARY_MIN))
                val shifttiming = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_SHIFT_TIMING))

                val joblocationslug = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_JOB_LOCATION_SLUG))
                val other_details = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_OTHER_DETAILS))



                bookmarkedList.add(JobData(company_name = companyName, title = title, location = location, salary = salary, phone = phone,job_type=jobType, job_role =jobRole,job_category=jobcategory,job_hours=jobhours,openings_count=openingsCount,experience=experience,qualification=qualification,salary_max=salarymax,salary_min=salarymin,shift_timing=shifttiming,job_location_slug=joblocationslug,other_details=other_details))
            }
        }
        cursor.close()
        bookmarkAdapter.notifyDataSetChanged()
    }
}
