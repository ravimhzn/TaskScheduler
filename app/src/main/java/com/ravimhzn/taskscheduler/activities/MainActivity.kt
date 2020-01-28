package com.ravimhzn.taskscheduler.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ravimhzn.taskscheduler.R
import com.ravimhzn.taskscheduler.database.AppDatabase
import com.ravimhzn.taskscheduler.database.TaskContract
import java.lang.Long.getLong
import java.lang.reflect.Array


private const val TAG = "MainActivity TAG::"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataFromSqlite()



    }

    private fun getDataFromSqlite() {
        val projection = arrayOf(TaskContract.columns.TASK_NAME, TaskContract.columns.SORT_ORDER)
        val sortColumn = TaskContract.columns.SORT_ORDER

        val cursor = contentResolver.query(TaskContract.CONTENT_URI,
            projection,
            null,
            null,
            sortColumn)
        Log.d(TAG, "*****************************")
        cursor.use {
            while(it.moveToNext()) {
                // Cycle through all records
                with(cursor) {
                    //                    val id = getLong(0)
                    val name = getString(0)
//                    val description = getString(2)
                    val sortOrder = getString(1)
                    val result = "Name: $name sort order: $sortOrder"
                    Log.d(TAG,"onCreate: reading data $result")
                }
            }
        }

        Log.d(TAG, "*****************************")
    }
}
