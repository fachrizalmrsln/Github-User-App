package com.fachrizalmrsln.githubuserapp.presentation.detail_page.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fachrizalmrsln.githubuserapp.databinding.ItemLoadingListBinding
import com.fachrizalmrsln.githubuserapp.databinding.ItemNoSpaceBinding
import com.fachrizalmrsln.githubuserapp.databinding.ItemRepositoriesBinding
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import com.fachrizalmrsln.githubuserapp.presentation.detail_page.holder.HolderUserRepositories
import com.fachrizalmrsln.githubuserapp.presentation.home_page.holder.HolderLoading
import com.fachrizalmrsln.githubuserapp.presentation.home_page.holder.HolderNoSpace
import com.fachrizalmrsln.githubuserapp.utils.constant.VIEW_TYPE_ITEM
import com.fachrizalmrsln.githubuserapp.utils.constant.VIEW_TYPE_LOADING
import com.fachrizalmrsln.githubuserapp.utils.constant.VIEW_TYPE_NO_SPACE

@SuppressLint("NotifyDataSetChanged")
class AdapterUserRepositories : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mLoadingState: Boolean = true
    private var mDataList = mutableListOf<UserRepositoriesModel>()

    fun loadingState(loadingState: Boolean) {
        mLoadingState = loadingState
        if (loadingState) notifyItemInserted(-1)
        else notifyItemRemoved(-1)
        notifyItemChanged(-1)
        notifyDataSetChanged()
    }
    
    fun insertData(data: List<UserRepositoriesModel>) {
        mDataList.apply {
            addAll(size, data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                val mBinding = ItemLoadingListBinding.inflate(inflater)
                HolderLoading(mBinding)
            }
            VIEW_TYPE_NO_SPACE -> {
                val mBinding = ItemNoSpaceBinding.inflate(inflater)
                HolderNoSpace(mBinding)
            }
            else -> {
                val mBinding = ItemRepositoriesBinding.inflate(inflater)
                HolderUserRepositories(mBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_LOADING -> (holder as? HolderLoading)?.bindLoading(mLoadingState)
            VIEW_TYPE_NO_SPACE -> (holder as? HolderNoSpace)
            else -> (holder as? HolderUserRepositories)?.bindRepositories(mDataList[position])
        }
    }

    override fun getItemCount() = mDataList.size + 1

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> {
                if (mLoadingState) VIEW_TYPE_LOADING
                else VIEW_TYPE_NO_SPACE
            }
            else -> VIEW_TYPE_ITEM
        }
    }
}