package com.bhuvaneshw.dokkaplayground.ui

import android.content.Context
import android.view.View

/**
 * A custom view that demonstrates awesome behavior.
 *
 * Use this view when you want to showcase something awesome
 * in your UI.
 *
 * @param context The context to use for this view.
 */
class MyAwesomeView(context: Context) : View(context) {

    /**
     * A title for the view.
     */
    var title: String = "Awesome"

    /**
     * A flag indicating whether the view is in awesome mode.
     */
    var isAwesome: Boolean = true
        private set

    /**
     * Performs an awesome action with the given argument.
     *
     * @param arg Any input value to perform the action.
     */
    fun doSomethingAwesome(arg: Any) {
        // Implementation goes here
        println("Doing something awesome with $arg")
    }

    /**
     * Toggles the awesome state of this view.
     */
    fun toggleAwesome() {
        isAwesome = !isAwesome
        println("Awesome state is now $isAwesome")
    }

    /**
     * Returns a description of this view.
     *
     * @return A human-readable description including the title and awesome state.
     */
    fun getDescription(): String {
        return "$title (awesome: $isAwesome)"
    }
}
