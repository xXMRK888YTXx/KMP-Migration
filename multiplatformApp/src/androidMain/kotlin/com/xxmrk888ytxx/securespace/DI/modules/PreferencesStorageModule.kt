package com.xxmrk888ytxx.securespace.DI.modules

import android.content.Context
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.preferencesstorage.createAndroid
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class PreferencesStorageModule {

    @Provides
    @AppScope
    fun providePreferencesStorage(context: Context): PreferencesStorage {
        return PreferencesStorage.Factory.createAndroid(context, "preferences")
    }
}