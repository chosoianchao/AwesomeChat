package com.rikkei.awesomechat.di

import com.rikkei.awesomechat.data.repository.AuthRepository
import com.rikkei.awesomechat.data.repository.AuthRepositoryImpl
import com.rikkei.awesomechat.data.source.AuthDataSource
import com.rikkei.awesomechat.data.source.remote.AuthRemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideAuthRemoteDataSource(): AuthDataSource.Remote {
        return AuthRemoteDatasource()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        @AppModule.RemoteDataSource remoteAuthDataSource: AuthDataSource.Remote,
    ): AuthRepository {
        return AuthRepositoryImpl(
            remoteAuthDataSource
        )
    }
}
