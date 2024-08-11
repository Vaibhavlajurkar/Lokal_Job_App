package com.example.lokaljobapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lokaljobapp.databinding.ActivityJobDetailBinding

class JobDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the job details from the intent
        val title = intent.getStringExtra("title") ?: "N/A"
        val location = intent.getStringExtra("location") ?: "N/A"
        val salary = intent.getStringExtra("salary") ?: "N/A"
        val phone = intent.getStringExtra("phone") ?: "N/A"
        val company = intent.getStringExtra("company_name") ?: "N/A"
        val jobType = intent.getStringExtra("job_type") ?: "N/A"
        val jobRole = intent.getStringExtra("job_role") ?: "N/A"
        val jobCategory = intent.getStringExtra("job_category") ?: "N/A"
        val jobHours = intent.getStringExtra("job_hours") ?: "N/A"
        val openingsCount = intent.getIntExtra("openings_count", -1)
        val openingsCountStr = if (openingsCount == -1) "N/A" else openingsCount.toString()
        val experience = intent.getStringExtra("experience")?: "N/A"
        val QualificationRequired =intent.getStringExtra("qualification")
        val salarymax=intent.getIntExtra("salary_max",-1)
        val MinimumSalary=intent.getIntExtra("salary_min",-1)
        val ShiftTiming=intent.getStringExtra("shift_timing")?: "N/A"
        val company_Address=intent.getStringExtra("job_location_slug")?: "N/A"
        val other_details =intent.getStringExtra("other_details")?: "N/A"

        binding.Phonenumber.text = "Phone: $phone"



        // Set the job details to the views
        binding.JobTitleJobDetail.text = "Title: $title"
        binding.LocationJobDetail.text = "Location: $location"
        binding.SalaryDetails.text = "Salary: $salary"
        binding.Phonenumber.text = "Phone: $phone"
        binding.companyname.text = "Company: $company"
        binding.jobtype.text = "Job Type: $jobType"
        binding.jobrole.text = "Job Role: $jobRole"
        binding.JobCategory.text = "Job Category: $jobCategory"
        binding.JobHours.text = "Job Hours: $jobHours"
        binding.Numberofopening.text = "Number of Openings: $openingsCountStr"
        binding.ExperienceRequired.text ="Experience Required: $experience"
        binding.QualificationRequired.text ="QualificationRequired: $QualificationRequired"
        binding.maximumSalary.text = "Maximum Salary: $salarymax"
        binding.MinimumSalary.text ="Minimum Salary: $MinimumSalary"
        binding.ShiftTiming.text="Shift Timing: $ShiftTiming"
        binding.joblocationslug.text="Job Location: $company_Address"
        binding.AdditionalDetails.text="Additional Details: $other_details"

        binding.callHrButton.setOnClickListener {
            if (phone != "N/A") {
                makePhoneCall(phone)
            } else {
                Toast.makeText(this, "No phone number provided", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up WhatsApp button
        binding.whatsappButton.setOnClickListener {
            if (phone != "N/A") {
                sendWhatsAppMessage(phone)
            } else {
                Toast.makeText(this, "No phone number provided", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makePhoneCall(phone: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phone")
            startActivity(callIntent)
        }
    }

    private fun sendWhatsAppMessage(phone: String) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phone")
        val whatsappIntent = Intent(Intent.ACTION_VIEW, uri)
        whatsappIntent.setPackage("com.whatsapp")
        try {
            startActivity(whatsappIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val phone = intent.getStringExtra("phone") ?: "N/A"
                if (phone != "N/A") {
                    makePhoneCall(phone)
                }
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}





