package com.foolography.swindy.ui.citydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.foolography.swindy.data.CityData
import com.foolography.swindy.databinding.CityDetailLayoutBinding
import com.foolography.swindy.di.Injectable
import com.foolography.swindy.di.injectViewModel
import javax.inject.Inject

class CityDetailFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CityDetailViewModel
    private lateinit var binding: CityDetailLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var cityData = arguments?.getParcelable<CityData>("cityData")

        viewModel = injectViewModel(viewModelFactory)
        binding = CityDetailLayoutBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.cityData = cityData
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}
