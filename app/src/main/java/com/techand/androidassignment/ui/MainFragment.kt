package com.techand.androidassignment.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.techand.androidassignment.R
import com.techand.androidassignment.databinding.FragmentMainBinding
import com.techand.androidassignment.util.Constants.Companion.titleBar
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
        swipeContainer.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireActivity(), R.color.purple_200))
        swipeContainer.setColorSchemeColors(Color.WHITE)
        setupRecyclerView()
        setupPulltoRefresh()
        setupObservers()
    }

    private fun setupPulltoRefresh() {
        swipeContainer.setOnRefreshListener {
            adapter.clear()
            viewModel.getData()
            setupRecyclerView()
            setupObservers()
        }
    }
    private fun setupRecyclerView() {
        adapter = MainAdapter()
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
    }
    private fun setupObservers() {
        val toolbar = (requireActivity() as AppCompatActivity).supportActionBar
        viewModel.items.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { data ->
                        toolbar?.title = titleBar
                        //binding.progressBar.visibility = View.GONE
                        adapter.setItems(data)
                        swipeContainer.isRefreshing = false
                    }
                    //if (it.data!=null) adapter.setItems(it.data)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    swipeContainer.isRefreshing = true
            }
        }
    }
}