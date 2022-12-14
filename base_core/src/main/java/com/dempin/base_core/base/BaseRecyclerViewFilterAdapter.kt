package com.dempin.base_core.base

import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding
import java.util.Locale

abstract class BaseRecyclerViewFilterAdapter<T, VDB : ViewDataBinding> constructor(
    items: MutableList<T>,
    private val locale: Locale
) : BaseRecyclerViewAdapter<T, VDB>(items), Filterable {

    private var filter: RecyclerViewFilter? = null

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = RecyclerViewFilter(getItems(), locale)
        }
        return filter as RecyclerViewFilter
    }

    fun getFilterAdapter(): RecyclerViewFilter? {
        return filter
    }

    fun setFilter(filterValue: String) {
        getFilter().filter(filterValue)
    }

    inner class RecyclerViewFilter internal constructor(filterItems: List<T>, locale: Locale) :
        BaseFilter<T>(filterItems, locale) {

        override fun getFilterItem(constLowerCase: String, value: T, controlParameter: String): T? {
            if (filter != null) {
                return getRecyclerFilterItem(constLowerCase, value, controlParameter)
            }
            return null
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(lowerCase: String, results: List<*>) {
            if (!onFilterFinish(lowerCase, results as List<T>)) {
                setItems(results)
            }
        }
    }

    protected abstract fun getRecyclerFilterItem(
        constLowerCase: String,
        value: T,
        controlParameter: String
    ): T?
    // Filterable //

    // clear Memory //
    fun unBindFilterAdapter() {
        clearFilter()
        clearItems()
    }

    fun clearFilter() {
        filter?.clear()
        filter = null
    }
    // clear Memory //


    protected open fun onFilterFinish(lowerCase: String, results: List<T>): Boolean {

        return false
    }

}