package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainSecureSpaceModule {
    @Provides
    fun provideSecureSpaceMainViewModel() : SecureSpaceMainViewModel = SecureSpaceMainViewModel()
}