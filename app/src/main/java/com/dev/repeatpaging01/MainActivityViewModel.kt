package com.dev.repeatpaging01

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev.repeatpaging01.network.ApiService
import com.dev.repeatpaging01.network.RetroInstance
import com.dev.repeatpaging01.network.response.CharacterData
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {

    lateinit var api: ApiService

    init {
        api = RetroInstance.getRetroInstance().create(ApiService::class.java)
    }

    fun getListData(): Flow<PagingData<CharacterData>> {

        return Pager(config = PagingConfig(pageSize = 34, maxSize = 2000),
                    pagingSourceFactory = {CharacterPagingSource(api)})
            .flow.cachedIn(viewModelScope)
    }
}