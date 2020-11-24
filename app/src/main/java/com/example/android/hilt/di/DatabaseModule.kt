package com.example.android.hilt.di

import android.content.Context
import androidx.annotation.StringDef
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Create new module to add database related bindings. Specifically, to provide bindings
 * for [LogDao] and [AppDatabase], using the *Provides* annotation.
 *
 * Note that in Kotlin, modules that only contain @Provides functions can be object classes.
 * In this way, providers get optimized and almost in-lined in generated code.
 */
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    /**
     * Provides an [LogDao] implementation based on the database passed in.
     *
     * @param [database] the database the DAO has a dependency to.
     * @return the DAO to operate the specified database.
     */
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao {
        return database.logDao()
    }

    /**
     * Provides an app-wide [AppDatabase] in the provided app context.
     *
     * @param [appContext] the context where the database is initialized in.
     * @return the singleton database instance.
     */
    @Singleton
    @Provides
    fun provideLogDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }
}