package com.example.android.hilt.data

import javax.inject.Inject

/**
 * Data manager class that handles data manipulation between the memory and the UI.
 *
 */
class LoggerInMemoryDataSource @Inject constructor() : LoggerDataSource {

    private val _logs = mutableListOf<Log>()
    private val logs: List<Log> = _logs

    override fun addLog(msg: String) {
        _logs.add(0, Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        _logs.clear()
    }
}