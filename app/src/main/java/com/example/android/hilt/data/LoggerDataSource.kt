package com.example.android.hilt.data

/**
 * Definition of a logger data source.
 *
 * This interface is an abstraction from [LoggerLocalDataSource] and is used
 * to define the common behaviours of the logger.
 */
interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}