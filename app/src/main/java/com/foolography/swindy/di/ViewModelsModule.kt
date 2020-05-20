package com.foolography.swindy.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foolography.swindy.ui.addcity.AddCityViewModel
import com.foolography.swindy.ui.weatherlist.WeatherListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherListViewModel::class)
    abstract fun bindWeatherDetailsViewModel(viewModel: WeatherListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCityViewModel::class)
    abstract fun bindAddCityViewModel(viewModel: AddCityViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

