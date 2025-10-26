package com.bhuvaneshw.dokkaplayground.core

/**
 * A core API class for Android applications.
 *
 * This class provides utility functions and services
 * that can be reused across your app modules.
 *
 * Example usage:
 * ```
 * val api = MyAndroidCoreApi()
 * api.initialize(context)
 * val result = api.doSomething("input")
 * ```
 */
class MyAndroidCoreApi {

    /**
     * Indicates whether the API has been initialized.
     */
    var isInitialized: Boolean = false
        private set

    /**
     * Initializes the API with the given context.
     *
     * @param context The Android context required for initialization.
     */
    fun initialize(context: android.content.Context) {
        // Initialization logic
        isInitialized = true
    }

    /**
     * Performs a core API operation with the provided input.
     *
     * @param input A string input for processing.
     * @return A processed string result.
     */
    fun doSomething(input: String): String {
        return "Processed: $input"
    }

    /**
     * Resets the API to its default state.
     */
    fun reset() {
        isInitialized = false
    }

    /**
     * Returns a summary of the API state.
     *
     * @return A string describing whether the API is initialized.
     */
    fun getStatus(): String {
        return "MyAndroidCoreApi initialized: $isInitialized"
    }
}
