package com.smartmobilefactory.tvmazeapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.smartmobilefactory.tvmazeapp.R
import com.smartmobilefactory.tvmazeapp.databinding.FragmentTvShowDetailBinding
import com.smartmobilefactory.tvmazeapp.presentation.utils.loadImage


/**
 * A simple [Fragment] subclass.
 * Use the [TvShowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TvShowDetailFragment : Fragment() {
    private lateinit var binding: FragmentTvShowDetailBinding
    val args: TvShowDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        val tvShowItem = args.tvShowItem
        tvShowItem.show.image?.let { image ->
            binding.tvShowDetailImageView.loadImage(image.original)
        }
        binding.tvShowName.text =
            String.format(getString(R.string.tv_show_detailview_name), tvShowItem.show.name)
        binding.tvShowLanguage.text =
            String.format(getString(R.string.tv_show_detailview_language), tvShowItem.show.language)
        binding.tvShowGenres.text = tvShowItem.show.genres.toString()
        binding.tvShowRating.text = tvShowItem.show.rating.toString()
    }

}