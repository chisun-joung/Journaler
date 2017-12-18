package com.example.chisunjoung.journaler.model

/**
 * Created by chisunjoung on 2017. 12. 18..
 */
enum class MODE(val mode: Int) {
    CREATE(0),
    EDIT(1),
    VIEW(2);

    companion object {
        val EXTRAS_KEY = "MODE"

        fun getByValue(value: Int): MODE {
            values().forEach {
                item ->

                if (item.mode == value) {
                    return item
                }
            }
            return VIEW
        }
    }
}