package com.ravimhzn.taskscheduler.database

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns
import com.ravimhzn.taskscheduler.providers.CONTENT_AUTHORITY
import com.ravimhzn.taskscheduler.providers.CONTENT_AUTHORITY_URI

object TaskContract {
    internal const val TABLE_NAME = "Tasks"

    /**
     * The URI to access the Tasks Table
     */
    val CONTENT_URI: Uri = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)

    const val CONTENT_TYPE = "vnd.android.cursor.dir/vmd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vmd.$CONTENT_AUTHORITY.$TABLE_NAME"

    object columns {
        const val ID = BaseColumns._ID
        const val TASK_NAME = "Name"
        const val DESCRIPTION = "Description"
        const val SORT_ORDER = "SortOrder"
    }

    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}