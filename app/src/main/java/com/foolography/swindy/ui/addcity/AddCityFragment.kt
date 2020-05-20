package com.foolography.swindy.ui.addcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.foolography.swindy.databinding.AddCityLayoutBinding
import com.foolography.swindy.di.Injectable
import com.foolography.swindy.di.injectViewModel
import com.foolography.swindy.ui.weatherlist.WeatherListViewModel
import javax.inject.Inject

class AddCityFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherListViewModel
    private lateinit var binding: AddCityLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = AddCityLayoutBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}
