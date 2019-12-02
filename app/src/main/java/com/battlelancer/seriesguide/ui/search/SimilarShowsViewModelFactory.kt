package com.battlelancer.seriesguide.ui.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SimilarShowsViewModelFactory(
    private val application: Application,
    private val showTvdbId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SimilarShowsViewModel(application, showTvdbId) as T
    }
}