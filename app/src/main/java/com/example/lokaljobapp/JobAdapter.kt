// JobAdapter.kt
package com.example.lokaljobapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobAdapter(private val jobList: List<JobData>, private val context: Context) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val location: TextView = itemView.findViewById(R.id.Location)
        val salary: TextView = itemView.findViewById(R.id.Salary)
        val phone: TextView = itemView.findViewById(R.id.LastDate)
        val bookmarkButton: ImageButton = itemView.findViewById(R.id.bookmarkButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        holder.jobTitle.text = "Title: ${job.title}"
        holder.location.text = "Location: ${job.location}"
        holder.salary.text = "Salary: ${job.salary}"
        holder.phone.text = "Phone: ${job.phone}"

        // Check if the job is already bookmarked
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            null,
            "${DatabaseHelper.COLUMN_TITLE} = ? AND ${DatabaseHelper.COLUMN_COMPANY_NAME} = ?",
            arrayOf(job.title, job.company_name),
            null,
            null,
            null
        )
        val isBookmarked = cursor.count > 0
        cursor.close()

        holder.bookmarkButton.isSelected = isBookmarked

        holder.bookmarkButton.setOnClickListener {
            if (holder.bookmarkButton.isSelected) {
                // Remove bookmark
                CoroutineScope(Dispatchers.IO).launch {
                    db.delete(
                        DatabaseHelper.TABLE_NAME,
                        "${DatabaseHelper.COLUMN_TITLE} = ? AND ${DatabaseHelper.COLUMN_COMPANY_NAME} = ?",
                        arrayOf(job.title, job.company_name)
                    )
                }
                holder.bookmarkButton.isSelected = false
            } else {
                // Add bookmark
                CoroutineScope(Dispatchers.IO).launch {
                    val values = ContentValues().apply {
                        put(DatabaseHelper.COLUMN_COMPANY_NAME, job.company_name)
                        put(DatabaseHelper.COLUMN_TITLE, job.title)
                        put(DatabaseHelper.COLUMN_LOCATION, job.location)
                        put(DatabaseHelper.COLUMN_SALARY, job.salary)
                        put(DatabaseHelper.COLUMN_PHONE, job.phone)
                        put(DatabaseHelper.COLUMN_JOB_TYPE, job.job_type)
                        put(DatabaseHelper.COLUMN_JOB_ROLE, job.job_role)
                        put(DatabaseHelper.COLUMN_JOB_CATEGORY, job.job_category)
                        put(DatabaseHelper.COLUMN_JOB_HOURS, job.job_hours)
                        put(DatabaseHelper.COLUMN_OPENINGS_COUNT, job.openings_count)
                        put(DatabaseHelper.COLUMN_EXPERIENCE, job.experience)
                        put(DatabaseHelper.COLUMN_QUALIFICATION, job.qualification)
                        put(DatabaseHelper.COLUMN_SALARY_MAX, job.salary_max)
                        put(DatabaseHelper.COLUMN_SALARY_MIN, job.salary_min)
                        put(DatabaseHelper.COLUMN_SHIFT_TIMING, job.shift_timing)
                        put(DatabaseHelper.COLUMN_JOB_LOCATION_SLUG, job.job_location_slug)
                        put(DatabaseHelper.COLUMN_OTHER_DETAILS, job.other_details)
                    }
                    db.insert(DatabaseHelper.TABLE_NAME, null, values)
                }
                holder.bookmarkButton.isSelected = true
            }
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, JobDetailActivity::class.java).apply {
                putExtra("title", job.title)
                putExtra("location", job.location)
                putExtra("salary", job.salary)
                putExtra("phone", job.phone)
                putExtra("company_name",job.company_name)
                putExtra("job_type", job.job_type)
                putExtra("job_role", job.job_role)
                putExtra("job_category", job.job_category)
                putExtra("job_hours", job.job_hours)
                putExtra("openings_count", job.openings_count)
                putExtra("experience", job.experience)
                putExtra("qualification", job.qualification)
                putExtra("salary_max", job.salary_max)
                putExtra("salary_min", job.salary_min)
                putExtra("shift_timing", job.shift_timing)
                putExtra("job_location_slug",job.job_location_slug)
                putExtra("other_details",job.other_details)

            }
            context.startActivity(intent)
        }
    }
    override fun getItemCount() = jobList.size
}

data class JobData(
    val company_name: String,
    val title: String,
    val location: String,
    val salary: String,
    val phone: String,
    val job_type: String,
    val job_role: String,
    val job_category: String,
    val job_hours: String,
    val openings_count: Int,
    val experience: String,
    val qualification: String,
    val salary_max: Int,
    val salary_min: Int,
    val shift_timing: String,
    val job_location_slug:String,
    val other_details:String
)
