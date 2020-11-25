package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerInMemoryDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * This module provides different logging implementation with
 * corresponding qualifiers.
 */
@Module
@InstallIn(ApplicationComponent::class)
interface LoggingDatabaseModule {

    /**
     * Binds an abstract [LoggerDataSource] to its database implementation.
     */
    @Binds
    @Singleton
    @DatabaseLogger
    fun bindDatabaseLogger(impl: LoggerLocalDataSource): LoggerDataSource
}

@Module
@InstallIn(ActivityComponent::class)
interface LoggingInMemoryModule {

    /**
     * Binds an abstract [LoggerDataSource] to its in-memory implementation.
     */
    @Binds
    @ActivityScoped
    @InMemoryLogger
    fun bindInMemoryLogger(impl: LoggerInMemoryDataSource): LoggerDataSource
}

@Qualifier
annotation class DatabaseLogger

@Qualifier
annotation class InMemoryLogger