package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * A module to provide navigation related bindings using *Binds* annotation.
 *
 * As method annotated with *Binds* must be abstract function, so this module
 * must also be defined abstract.
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    /**
     * Binds a navigator to a specific implementation.
     */
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}