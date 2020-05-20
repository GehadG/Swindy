package com.foolography.swindy.ui.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.foolography.swindy.R
import com.foolography.swindy.data.CityData
import com.foolography.swindy.databinding.WeatherListLayoutBinding
import com.foolography.swindy.di.Injectable
import com.foolography.swindy.di.injectViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.weather_list_layout.*
import javax.inject.Inject

class WeatherListFragment : Fragment(), Injectable, WeatherListAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherListViewModel
    private lateinit var mAdapter: WeatherListAdapter
    private lateinit var binding: WeatherListLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = WeatherListLayoutBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.weatherListViewModel = viewModel
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)


        viewModel.weatherList.observe(viewLifecycleOwner, Observer { newsResponse ->
            populateList(newsResponse.citiesList)
            viewModel.isShowProgress.value = View.GONE

        })
        insertCityBtn.setOnClickListener {
            findNavController().navigate(R.id.action_weatherListFragment_to_addCityFragment)
        }
        if (viewModel.isListEmpty()) {
            viewModel.isShowProgress.value = View.GONE
            binding.swipeRefresh.isRefreshing = false
        }
        viewModel.storageWatcher.observe(viewLifecycleOwner, Observer {
            reloadCities()
        })

    }

    override fun onPause() {
        super.onPause()
        viewModel.scrollPosition.value =
            (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
    }

    private fun populateList(citiesList: List<CityData>) {
        mAdapter = WeatherListAdapter(citiesList, context!!, this)
        binding.recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.scrollToPosition(viewModel.scrollPosition.value!!)
        binding.swipeRefresh.setOnRefreshListener {
            reloadCities()
        }
    }

    private fun reloadCities() {
        if (!viewModel.isListEmpty()) {
            binding.swipeRefresh.isRefreshing = true
            viewModel.loadWeather().observe(viewLifecycleOwner, Observer {
                if (it.code == "404") {
                    showEmptyState()

                } else {
                    viewModel.showEmptyState.value = false
                    populateList(it.citiesList)
                }

                binding.swipeRefresh.isRefreshing = false
            })
        } else {
            showEmptyState()
        }

    }

    private fun showEmptyState() {
        binding.swipeRefresh.isRefreshing = false
        populateList(emptyList())
        viewModel.isShowProgress.value = View.GONE
        viewModel.showEmptyState.value = true
    }

    override fun onItemClick(item: CityData) {

    }

    override fun onLongClick(item: CityData): Boolean {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Delete City ?")
                .setMessage("Are you sure you want to delete ${item.name} from the list")
                .setNegativeButton("Cancel") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("Confirm") { dialog, which ->
                    viewModel.deleteCity(item.id)
                    reloadCities()
                }
                .show()
        }

        return true
    }

    companion object {
        var TAG: String = WeatherListFragment::class.java.simpleName
    }


}