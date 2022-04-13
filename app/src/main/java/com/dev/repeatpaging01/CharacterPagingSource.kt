package com.dev.repeatpaging01

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev.repeatpaging01.network.ApiService
import com.dev.repeatpaging01.network.response.CharacterData
import java.lang.Exception

class CharacterPagingSource(val api: ApiService): PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = api.getDataOfCharacters(nextPage)
            var nextPageNumber: Int? = null
            if(response?.info?.next != null) {
                val uri = Uri.parse(response.info.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()!!
            }
            LoadResult.Page(data = response.results,
                            prevKey = null,
                            nextKey = nextPageNumber)
        }
        catch (e: Exception) {
            LoadResult.Error(e)

        }
    }

    companion object {
        const val FIRST_PAGE_INDEX = 1
    }

}