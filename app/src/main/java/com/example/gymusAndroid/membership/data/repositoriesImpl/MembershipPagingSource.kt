package com.example.gymusAndroid.membership.data.repositoriesImpl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.dto.Error
import com.example.core.dto.Success
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.toModel

class MembershipPagingSource(
    private val membershipApiService: MembershipApiService
) : PagingSource<Int, Member>() {
    override fun getRefreshKey(state: PagingState<Int, Member>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Member> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val result = membershipApiService.findAllMembers(
                page = page,
                pageSize = pageSize
            )

            when (result) {
                is Error -> {
                    LoadResult.Error(Throwable(result.error.message))
                }

                is Success -> {
                    LoadResult.Page(
                        data = result.data.content.map { it.toModel() },
                        prevKey = if (result.data.hasPrevious) page - 1 else null,
                        nextKey = if (result.data.hasNext) page + 1 else null,
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}