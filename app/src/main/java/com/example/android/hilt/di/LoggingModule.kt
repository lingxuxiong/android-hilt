package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerDatabaseDataSource
import com.example.android.hilt.data.LoggerInMemoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
interface LoggingInMemoryModule {

    @Binds
    @InMemoryLogger
    @ActivityScoped
    fun bindLogger(dataSource: LoggerInMemoryDataSource): LoggerDataSource
}

@Module
@InstallIn(ApplicationComponent::class)
interface LoggingDatabaseModule {

    @Binds
    @DatabaseLogger
    @Singleton
    fun bindLogger(dataSource: LoggerDatabaseDataSource): LoggerDataSource
}

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseLogger

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class InMemoryLogger