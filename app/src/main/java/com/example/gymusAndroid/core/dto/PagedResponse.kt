package com.example.gymusAndroid.core.dto

data class PagedResponse<T>(
    val data: T,
    val pageSize: Int,
    val totalItems: Long,
    val totalPages: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)
