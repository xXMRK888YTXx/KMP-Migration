package com.xxmrk888ytxx.coreandroid.annotations

@Retention(value = AnnotationRetention.BINARY)
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "After use you must remove this debug log functions"
)
internal annotation class UsedFastLogger