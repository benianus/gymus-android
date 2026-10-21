package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.domain.useCases.LoginUseCase
import com.example.gymusAndroid.auth.domain.useCases.LogoutUseCase
import com.example.gymusAndroid.auth.domain.useCases.RegisterUseCase
import com.example.gymusAndroid.membership.domain.useCases.FindAllMembersUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::LogoutUseCase)

    singleOf(::FindAllMembersUseCase)
}