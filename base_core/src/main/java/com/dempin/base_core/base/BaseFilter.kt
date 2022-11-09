package com.dempin.base_core.base

import android.widget.Filter
import java.util.Locale

abstract class BaseFilter<T> constructor(filterItems: List<T>,private  val locale: Locale) : Filter() {

    private var allItems: MutableList<T>? = null

    init {
        this.allItems = filterItems as MutableList<T>
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        synchronized(this) {
            val results = FilterResults()
            if (constraint != null && constraint.length > 2) {
                val list: MutableList<T> = mutableListOf()
                val constLowerCase = constraint.toString().toLowerCase(locale)
                val controlParameter = constLowerCase.substring(0, 2)
                val lowerCase = constLowerCase.substring(2, constLowerCase.length)
                allItems?.forEach {
                    getFilterItem(lowerCase, it, controlParameter)?.let { model ->
                        list.add(model)
                    }
                }
                results.values = list
                results.count = list.size
            } else {
                results.values = allItems
                results.count = allItems?.size!!
            }
            return results
        }
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults?) {
        synchronized(this) {
            results?.values?.let {
                val constLowerCase = constraint.toString().lowercase(locale)
                val lowerCase = constLowerCase.substring(2, constLowerCase.length)
                val arrayList: List<*> = it as List<*>
                publishResults(lowerCase,arrayList)
            }
        }
    }


    protected abstract fun publishResults(lowerCase:String,results: List<*>)

    protected abstract fun getFilterItem(
        constLowerCase: String,
        value: T,
        controlParameter: String
    ): T?

    fun clear() {
        allItems?.clear()
    }


    fun isContainsLower(model: T, value: String, constLowerCase: String): T? {
        return if (value.lowercase(locale).contains(constLowerCase)) {
            model
        } else null
    }


}