package com.foolography.swindy.di

import com.foolography.swindy.ui.addcity.AddCityFragment
import com.foolography.swindy.ui.weatherlist.WeatherListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeWeatherListFragment(): WeatherListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddCityFragment(): AddCityFragment
}
