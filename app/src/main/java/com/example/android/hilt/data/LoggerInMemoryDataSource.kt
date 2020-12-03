package com.example.android.hilt.data

import javax.inject.Inject

class LoggerInMemoryDataSource @Inject constructor(): LoggerDataSource {

    private val _logs = mutableListOf<Log>()
    private val logs: List<Log> = _logs


    override fun addLog(msg: String) {
        _logs.add(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        _logs.clear()
    }

}