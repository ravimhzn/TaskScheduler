package com.ravimhzn.taskscheduler.database

import android.provider.BaseColumns

object TaskContract{
    internal const val TABLE_NAME = "Tasks"

    object columns{
        const val ID = BaseColumns._ID
        const val TASK_NAME = "Name"
        const val DESCRIPTION = "Description"
        const val SORT_ORDER = "SortOrder"
    }
}