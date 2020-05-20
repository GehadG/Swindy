package com.foolography.swindy.ui.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.foolography.swindy.data.CityData
import com.foolography.swindy.databinding.WeatherListLayoutBinding
import com.foolography.swindy.di.Injectable
import com.foolography.swindy.di.injectViewModel
import kotlinx.android.synthetic.main.weather_list_layout.*
import javax.inject.Inject

class WeatherListFragment : Fragment(), Injectable, WeatherListAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherListViewModel
    private lateinit var mAdapter: WeatherListAdapter
    private lateinit var binding: WeatherListLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
            populateNewsList(newsResponse.citiesList)
            viewModel.isShowProgress.value = View.GONE

        })

    }

    override fun onPause() {
        super.onPause()
        viewModel.scrollPosition.value =
            (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
    }

    private fun populateNewsList(citiesList: List<CityData>) {
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
            viewModel.loadWeather().observe(viewLifecycleOwner, Observer {
                populateNewsList(it.citiesList)
                binding.swipeRefresh.isRefreshing = false
            })
        }
    }

    override fun onItemClick(item: CityData) {
    }

    companion object {
        var TAG: String = WeatherListFragment::class.java.simpleName
    }


}
