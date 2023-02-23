package com.example.a20230222_girumalemu_nycschools.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230222_girumalemu_nycschools.data.model.NYCSchools
import com.example.a20230222_girumalemu_nycschools.databinding.ItemSchlBinding

class SchoolsAdaptor(
    private val listener: OnItemClickListener,
    diffCallBack: DiffUtil.ItemCallback<NYCSchools> = object : DiffUtil.ItemCallback<NYCSchools>() {
        override fun areItemsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem.dbn == newItem.dbn
        }

        override fun areContentsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem == newItem
        }

    }
) : PagingDataAdapter<NYCSchools, SchoolsAdaptor.HighSchoolViewHolder>(diffCallback = diffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HighSchoolViewHolder {
        val binding = ItemSchlBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return HighSchoolViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HighSchoolViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(dbn: String)
    }

    inner class HighSchoolViewHolder(private val binding: ItemSchlBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onItemClicked(it.dbn)
                    }
                }
            }
        }

        fun bind(NYCSchoolsItem: NYCSchools) {
            binding.apply {
                textViewHighSchoolName.text = NYCSchoolsItem.schoolName
                textViewHighSchoolAddress.text = NYCSchoolsItem.primaryAddressLine1
                textViewHighSchoolBorough.text = NYCSchoolsItem.borough
            }
        }

    }
}