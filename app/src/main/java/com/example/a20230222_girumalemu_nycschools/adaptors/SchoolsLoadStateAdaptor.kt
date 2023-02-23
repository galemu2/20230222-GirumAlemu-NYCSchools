package com.example.a20230222_girumalemu_nycschools.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230222_girumalemu_nycschools.databinding.LoadstateHeaderFooterBinding


class SchoolsLoadStateAdaptor(private val retry: () -> Unit) :
    LoadStateAdapter<SchoolsLoadStateAdaptor.SchoolsLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SchoolsLoadStateViewHolder {

        val binding = LoadstateHeaderFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SchoolsLoadStateViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: SchoolsLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }


    inner class SchoolsLoadStateViewHolder(
        private val binding: LoadstateHeaderFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {

            binding.apply {

                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}
