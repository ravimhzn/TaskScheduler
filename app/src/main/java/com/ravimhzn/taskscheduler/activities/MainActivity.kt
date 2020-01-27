package com.ravimhzn.taskscheduler.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ravimhzn.taskscheduler.R
import com.ravimhzn.taskscheduler.database.AppDatabase
import java.lang.Long.getLong
import java.lang.reflect.Array


private const val TAG = "MainActivity TAG::"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase = AppDatabase.getInstance(this)
        val db = appDatabase.readableDatabase

        val cursor = db.rawQuery("select * from tasks", null)
        Log.d(TAG, "**************************")
        cursor.use {
            while (it.moveToNext()){
                with(cursor){
                    val id = getLong(0)
                    val name = getString(1)
                    val description = getString(2)
                    val sortOrder = getLong(3)
                    val result = """
                        $id \t $name \t $description \t$sortOrder
                    """.trimIndent()
                    Log.d(TAG, "Database Value : $result")
                }

            }
        }
        Log.d(TAG, "**************************")
    }
}
