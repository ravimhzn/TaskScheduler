package com.ravimhzn.taskscheduler.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ravimhzn.taskscheduler.utilities.SingletonHolder

private const val TAG = "AppDatabase"
private const val DATABASE_NAME = "TaskScheduler.db"
private const val DATABASE_VERSION = 3

internal class AppDatabase private constructor(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {
    init {
        Log.d(TAG, "AppDatabase: Initilizing");
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Creating Db Table");
        val createTableQuery = """
            CREATE TABLE ${TaskContract.TABLE_NAME}(
            ${TaskContract.columns.ID} INTEGER PRIMARY KEY NOT NULL,
            ${TaskContract.columns.TASK_NAME} TEXT NOT NULL,
            ${TaskContract.columns.DESCRIPTION} TEXT,
            ${TaskContract.columns.SORT_ORDER} INTEGER);            
            )
        """.replaceIndent(" ")
        Log.d(TAG, createTableQuery)
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object : SingletonHolder<AppDatabase, Context>(::AppDatabase)
/*
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance
            ?: synchronized(this) {
                instance
                    ?: AppDatabase(context).also {
                        instance = it
                    }
            }
    }
*/

}