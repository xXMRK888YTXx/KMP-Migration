package com.xxmrk888ytxx.coreandroid.annotations

@Retention(value = AnnotationRetention.BINARY)
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "Use interface Logger, but not implementation. Provide it in class constructor or use dependence injection." +
            "And already there, you need pass this implementation"
)
annotation class UseLoggerInterface
