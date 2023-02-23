package com.example.a20230222_girumalemu_nycschools.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.a20230222_girumalemu_nycschools.R
import com.example.a20230222_girumalemu_nycschools.data.model.SatResult
import com.example.a20230222_girumalemu_nycschools.databinding.SatScoreFragmentBinding
import com.example.a20230222_girumalemu_nycschools.ui.viewModels.SchoolsViewModel
import com.example.a20230222_girumalemu_nycschools.util.Resource


class SatScoreFragment : Fragment(R.layout.sat_score_fragment) {

    private var _binding: SatScoreFragmentBinding? = null
    private val binding: SatScoreFragmentBinding
        get() = _binding!!

    private val args:SatScoreFragmentArgs by navArgs()

    private val viewModel: SchoolsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SatScoreFragmentBinding.bind(view)

        viewModel.getSatResults(args.dbn)

        viewModel.satResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.apply {
                        layoutError.visibility = View.GONE
                        layoutLoading.visibility = View.VISIBLE
                        layoutSatResule.visibility = View.GONE
                    }
                }
                is Resource.Success -> {
                    response.data?.let { satResults ->
                        if (satResults.isEmpty()) {
                            response.message = "Empty result"
                            errorResponse(response)
                            return@let
                        }
                        val satResult = satResults[0]
                        binding.apply {
                            layoutError.visibility = View.GONE
                            layoutLoading.visibility = View.GONE
                            layoutSatResule.visibility = View.VISIBLE

                            textViewSchool.text = satResult.schoolName ?: "-"
                            textViewTestTakers.text = satResult.numOfSatTestTakers ?: "-"
                            textViewAvgReading.text = satResult.satCriticalReadingAvgScore ?: "-"
                            textViewAvgWriting.text = satResult.satWritingAvgScore ?: "-"
                            textViewAvgMath.text = satResult.satMathAvgScore ?: "-"
                        }
                    }


                }
                is Resource.Error -> {
                    errorResponse(response)
                }
                else -> {
                    errorResponse(response)
                }
            }

        }

    }

    private fun errorResponse(response: Resource<List<SatResult>>) {
        val message = response.message
        binding.apply {
            layoutError.visibility = View.VISIBLE
            layoutLoading.visibility = View.GONE
            layoutSatResule.visibility = View.GONE
            textViewErrorMessage.text = message ?: "Unknown Error"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}