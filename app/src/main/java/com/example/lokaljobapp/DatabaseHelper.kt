package com.example.lokaljobapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "bookmarks.db"
        private const val DATABASE_VERSION = 2 // Incremented the version to 2
        const val TABLE_NAME = "bookmarked_jobs"
        const val COLUMN_ID = "id"
        const val COLUMN_COMPANY_NAME = "company_name"
        const val COLUMN_TITLE = "title"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_SALARY = "salary"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_JOB_TYPE = "job_type"
        const val COLUMN_JOB_ROLE = "job_role"
        const val COLUMN_JOB_CATEGORY = "job_category"
        const val COLUMN_JOB_HOURS = "job_hours"
        const val COLUMN_OPENINGS_COUNT = "openings_count"
        const val COLUMN_EXPERIENCE = "experience"
        const val COLUMN_QUALIFICATION = "qualification"
        const val COLUMN_SALARY_MAX = "salary_max"
        const val COLUMN_SALARY_MIN = "salary_min"
        const val COLUMN_SHIFT_TIMING = "shift_timing"
        const val COLUMN_JOB_LOCATION_SLUG = "job_location_slug"
        const val COLUMN_OTHER_DETAILS = "other_details"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_COMPANY_NAME TEXT,"
                + "$COLUMN_TITLE TEXT,"
                + "$COLUMN_LOCATION TEXT,"
                + "$COLUMN_SALARY TEXT,"
                + "$COLUMN_PHONE TEXT,"
                + "$COLUMN_JOB_TYPE TEXT,"
                + "$COLUMN_JOB_ROLE TEXT,"
                + "$COLUMN_JOB_CATEGORY TEXT,"
                + "$COLUMN_JOB_HOURS TEXT,"
                + "$COLUMN_OPENINGS_COUNT INTEGER,"
                + "$COLUMN_EXPERIENCE TEXT,"
                + "$COLUMN_QUALIFICATION TEXT,"
                + "$COLUMN_SALARY_MAX TEXT,"
                + "$COLUMN_SALARY_MIN TEXT,"
                + "$COLUMN_SHIFT_TIMING TEXT,"
                + "$COLUMN_JOB_LOCATION_SLUG TEXT,"
                + "$COLUMN_OTHER_DETAILS TEXT"
                + ")")

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_EXPERIENCE TEXT")
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_QUALIFICATION TEXT")
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SALARY_MAX TEXT")
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SALARY_MIN TEXT")
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SHIFT_TIMING TEXT")
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_JOB_LOCATION_SLUG TEXT")
            db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_OTHER_DETAILS TEXT")
        }
    }
}
