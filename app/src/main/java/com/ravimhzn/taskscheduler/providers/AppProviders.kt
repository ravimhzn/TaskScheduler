package com.ravimhzn.taskscheduler.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.util.Log
import com.ravimhzn.taskscheduler.database.AppDatabase
import com.ravimhzn.taskscheduler.database.TaskContract
import java.lang.IllegalArgumentException


private const val TAG = "AppProvider"
const val CONTENT_AUTHORITY = "com.ravimhzn.taskscheduler.providers.provider"

private const val TASKS = 100
private const val TASKS_ID = 101

private const val TIMINGS = 200
private const val TIMINGS_ID = 201

private const val TASK_DURATIONS = 400
private const val TASK_DURATIONS_ID = 401

val CONTENT_AUTHORITY_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

class AppProviders : ContentProvider() {


    private val uriMatcher by lazy {
        buildURIMatcher()
    }

    private fun buildURIMatcher(): UriMatcher {
        Log.d(TAG, "Building URIMatcher Starts")
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        //e.g. content://com.ravimhzn.taskscheduler.providers.provider/Tasks
        matcher.addURI(CONTENT_AUTHORITY, TaskContract.TABLE_NAME, TASKS)
        //e.g. content://com.ravimhzn.taskscheduler.providers.provider/Tasks/101
        matcher.addURI(CONTENT_AUTHORITY, "${TaskContract.TABLE_NAME}/#", TASKS_ID)

//        matcher.addURI(CONTENT_AUTHORITY, TimingContract.TABLE_NAME, TIMINGS)
//        matcher.addURI(CONTENT_AUTHORITY, "${TimingContract.TABLE_NAME}/#", TIMINGS_ID)
//
//        matcher.addURI(CONTENT_AUTHORITY, DurationContract.TABLE_NAME, TASK_DURATIONS)
//        matcher.addURI(CONTENT_AUTHORITY, "${DurationContract.TABLE_NAME}/#", TASK_DURATIONS_ID)

        return matcher
    }

    override fun onCreate(): Boolean {
        return true;
    }

    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        Log.d(TAG, "query called with URI: $uri")
        val match = uriMatcher.match(uri)
        Log.d(TAG, "query: match is $match")

        val queryBuilder = SQLiteQueryBuilder()

        when (match) {
            TASKS -> queryBuilder.tables = TaskContract.TABLE_NAME

            TASKS_ID -> {
                queryBuilder.tables = TaskContract.TABLE_NAME
                val taskId = TaskContract.getId(uri)
                queryBuilder.appendWhereEscapeString("${TaskContract.columns.ID} = $TASKS_ID")
            }
            else
            -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        val db = AppDatabase.getInstance(context).readableDatabase
        val cursor =
            queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        Log.d(TAG, "query: rows in returned cursor = ${cursor.count}")
        return cursor
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}