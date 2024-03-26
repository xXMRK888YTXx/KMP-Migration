package com.xxmrk888ytxx.securespace.domain

import kotlinx.coroutines.flow.Flow

interface FirstStartStateHolder {

    val isFirstStartOfApplication: Flow<Boolean>

    suspend fun markFirstStartOfApplication()
}