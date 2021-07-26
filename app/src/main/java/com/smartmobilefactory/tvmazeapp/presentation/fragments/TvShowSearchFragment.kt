package com.smartmobilefactory.tvmazeapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartmobilefactory.tvmazeapp.databinding.FragmentTvShowSearchBinding
import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponseItem
import com.smartmobilefactory.tvmazeapp.presentation.MainActivity
import com.smartmobilefactory.tvmazeapp.presentation.adapter.TvShowAdapter
import com.smartmobilefactory.tvmazeapp.presentation.viewmodel.TvShowViewModel
import java.util.concurrent.CancellationException
import javax.inject.Inject


class TvShowSearchFragment : Fragment() {
    private lateinit var binding: FragmentTvShowSearchBinding

    @Inject
    lateinit var viewModel: TvShowViewModel


    private var tvShowlistAdapter = TvShowAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchView()
        setupShowListObserver()
    }


    private fun setupSearchView() {
        binding.tvShowSearchRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowlistAdapter
        }

        binding.tvShowSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    tvShowlistAdapter.tvShowList.clear()
                }else{
                    viewModel.job?.cancel()
                    viewModel.searchTvShow(newText)
                }
                return true
            }

        })
    }

    private fun setupShowListObserver() {
        viewModel.tvshowList.observe(viewLifecycleOwner, Observer { tvShowSearch ->
            tvShowlistAdapter.updateTvShows(tvShowSearch)
            tvShowlistAdapter.onItemClick = { tvShow ->
                navigateToTvShowDetail(tvShow)
            }
        })


        viewModel.tvShowSearchError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError != null) {
                Toast.makeText(context, isError, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                binding.progressLoading.visibility = View.VISIBLE
            } else {
                binding.progressLoading.visibility = View.GONE
            }
        })
    }


    private fun navigateToTvShowDetail(tvShowSearchResponseItem: TvShowResponseItem) {
        val direction =
            TvShowSearchFragmentDirections.navigateToTvShowDetailFragment(tvShowSearchResponseItem)
        findNavController().navigate(direction)

    }

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }

}