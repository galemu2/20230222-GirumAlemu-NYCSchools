package com.example.a20230222_girumalemu_nycschools.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.a20230222_girumalemu_nycschools.R
import com.example.a20230222_girumalemu_nycschools.adaptors.SchoolsAdaptor
import com.example.a20230222_girumalemu_nycschools.adaptors.SchoolsLoadStateAdaptor
import com.example.a20230222_girumalemu_nycschools.databinding.SchoolFragmentBinding
import com.example.a20230222_girumalemu_nycschools.ui.viewModels.SchoolsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SchoolsListFragment : Fragment(R.layout.school_fragment), SchoolsAdaptor.OnItemClickListener {

    private val viewModel: SchoolsViewModel by viewModels()

    private var _binding: SchoolFragmentBinding? = null
    private val binding: SchoolFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SchoolFragmentBinding.bind(view)

        val schoolsAdaptor = SchoolsAdaptor(this)

        binding.recyclerViewHighSchools.setHasFixedSize(true)
        binding.recyclerViewHighSchools.adapter = schoolsAdaptor.withLoadStateHeaderAndFooter(
            header = SchoolsLoadStateAdaptor { schoolsAdaptor.retry() },
            footer = SchoolsLoadStateAdaptor { schoolsAdaptor.retry() }
        )

        binding.buttonReload.setOnClickListener { schoolsAdaptor.retry() }


        // submit data to adaptor
        lifecycleScope.launch {
            viewModel.schools.collectLatest { pagingData ->
                schoolsAdaptor.submitData(pagingData = pagingData)
            }
        }

        schoolsAdaptor.addLoadStateListener { loadState ->

            binding.apply {
                val currentState = loadState.source.refresh
                progressBar.isVisible = currentState is LoadState.Loading
                recyclerViewHighSchools.isVisible = currentState is LoadState.NotLoading
                textViewError.isVisible = currentState is LoadState.Error
                buttonReload.isVisible = currentState is LoadState.Error

                if (currentState is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    schoolsAdaptor.itemCount < 1
                ) {
                    recyclerViewHighSchools.isVisible = false
                    textViewNoResults.isVisible = true
                } else {
                    textViewNoResults.isVisible = false

                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(dbn: String) {
        val action = SchoolsListFragmentDirections.actionSchoolsFragmentToSatFragment(dbn)
       findNavController().navigate(action)
    }
}
