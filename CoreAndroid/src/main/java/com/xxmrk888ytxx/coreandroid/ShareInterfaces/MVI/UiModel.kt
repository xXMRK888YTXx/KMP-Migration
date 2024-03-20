package com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI

/**
 * [Ru]
 * Интрефейс для объединения [UiEventHandler] и [UiEventHandler]
 */

/**
 * [En]
 *  Interface for join [UiEventHandler] and [UiEventHandler]
 */
interface UiModel<STATE> : UiEventHandler,UiStateHolder<STATE>