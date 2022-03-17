package com.fachrizalmrsln.githubuserapp.presentation.home_page

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fachrizalmrsln.githubuserapp.databinding.ItemSearchResultsBinding
import com.fachrizalmrsln.githubuserapp.model.UserModel

@SuppressLint("NotifyDataSetChanged")
class AdapterSearchResults : RecyclerView.Adapter<HolderSearchResults>() {

    interface ListenerSearchResults {
        fun onSearchItemCLick(result: UserModel)
    }

    lateinit var listener: ListenerSearchResults
    private var mDataList = mutableListOf<UserModel>()

    fun insertData(dataNews: List<UserModel>) {
        mDataList.apply {
            clear()
            addAll(dataNews)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSearchResults {
        val inflater = LayoutInflater.from(parent.context)
        val mBinding = ItemSearchResultsBinding.inflate(inflater)
        return HolderSearchResults(mBinding, listener)
    }

    override fun onBindViewHolder(holder: HolderSearchResults, position: Int) {
        holder.bindSearchResults(mDataList[position])
    }

    override fun getItemCount() = mDataList.size

}