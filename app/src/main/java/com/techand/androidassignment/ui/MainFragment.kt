package com.techand.androidassignment.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.techand.androidassignment.R
import com.techand.androidassignment.databinding.FragmentMainBinding
import com.techand.androidassignment.util.Constants.Companion.Prefs
import com.techand.androidassignment.util.Resource
import com.techand.androidassignment.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding by autoCleared()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupPullToRefresh()
        setupObservers()
    }

    //on swipe data refresh - fetch from network
    private fun setupPullToRefresh() {
        swipeContainer.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.purple_200
            )
        )
        swipeContainer.setColorSchemeColors(Color.WHITE)
        swipeContainer.setOnRefreshListener {
            adapter.clear()
            viewModel.run { refreshData() }
            setupRecyclerView()
            setupObservers()
        }
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter()
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
    }

    //observe Live Data
    private fun setupObservers() {
        val toolbar = (requireActivity() as AppCompatActivity).supportActionBar
        viewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { data ->
                        adapter.setItems(data)
                        toolbar?.title = Prefs.getString("title", getString(R.string.app_name))
                        swipeContainer.isRefreshing = false
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    swipeContainer.isRefreshing = true
            }
        }
        //no data text visible if no data available
        viewModel.empty.observe(viewLifecycleOwner) {
            if (it)
                tv_no_data.visibility = View.VISIBLE
            else
                tv_no_data.visibility = View.GONE

        }
    }
}