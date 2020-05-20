package com.foolography.swindy.ui.addcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.foolography.swindy.R
import com.foolography.swindy.databinding.AddCityLayoutBinding
import com.foolography.swindy.di.Injectable
import com.foolography.swindy.di.injectViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_city_layout.*
import javax.inject.Inject

class AddCityFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AddCityViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelBtn.setOnClickListener {
            activity?.onBackPressed()
        }
        continueBtn.setOnClickListener {

            if (viewModel.isValidCity(cityName.text.toString())) {
                viewModel.validateCity(cityName.text.toString())
                    .observe(viewLifecycleOwner, Observer {
                        if (it.code == 200) {
                            viewModel.saveCity(it.id)
                            val bundle = bundleOf("cityData" to it)
                            findNavController().navigate(
                                R.id.action_addCityFragment_to_cityDetailFragment,
                                bundle
                            )
                        } else {
                            showError("Sorry , We couldn't find this city in our Database")
                        }
                    })
            } else {
                showError("Please Enter a valid city name")
            }
        }

    }

    private fun showError(s: String) {
        view?.let {
            Snackbar.make(it, s, Snackbar.LENGTH_LONG)
                .show()
        };
    }

}
