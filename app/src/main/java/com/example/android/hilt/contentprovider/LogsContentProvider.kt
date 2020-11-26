package com.example.android.hilt.contentprovider

import android.content.*
import android.database.Cursor
import android.net.Uri
import com.example.android.hilt.data.LogDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

private const val AUTHORITY  = "com.example.android.hilt.provider"
private const val PATH_LOGS_TABLE = "logs"
private const val PATH_LOGS_TABLE_ITEM = "logs/*"
private const val MATCH_CODE_LOGS_DIR  = 1
private const val MATCH_CODE_LOGS_ITEM = 2

class LogsContentProvider: ContentProvider() {

    private val matcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, PATH_LOGS_TABLE, MATCH_CODE_LOGS_DIR)
        addURI(AUTHORITY, PATH_LOGS_TABLE_ITEM, MATCH_CODE_LOGS_DIR)
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val appContext = context?.applicationContext ?: throw IllegalStateException()
        val logDao: LogDao = getLogDao(appContext)
        val cursor =  when(matcher.match(uri)) {
            MATCH_CODE_LOGS_DIR -> logDao.selectAllLogsCursor()
            MATCH_CODE_LOGS_ITEM -> logDao.selectLogById(ContentUris.parseId(uri))
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        cursor.setNotificationUri(appContext.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    private fun getLogDao(appContext: Context): LogDao {
        return EntryPointAccessors.fromApplication(appContext,
            LogsContentProviderEntryPoint::class.java).logDao()
    }

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface LogsContentProviderEntryPoint {
        fun logDao(): LogDao
    }
}
