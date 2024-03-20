package com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI

import kotlinx.coroutines.flow.Flow

/**
 * [Ru]
 * Интрефейс для держателя состояния для View
 */

/**
 * [En]
 * Interface for holder of state for View
 */
interface UiStateHolder<out STATE> {

    val state:Flow<STATE>

    /**
     * [Ru]
     * Состояние которое будет использоваться,
     * как стандартное значение при не возможности использовать [state]
     */

    /**
     * [En]
     * State which will be use as default value if [state] can't be use
     */
    val defValue:STATE
}