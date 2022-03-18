package com.fachrizalmrsln.githubuserapp.presentation.detail_page.holder

import androidx.recyclerview.widget.RecyclerView
import com.fachrizalmrsln.githubuserapp.databinding.ItemRepositoriesBinding
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import com.fachrizalmrsln.githubuserapp.utils.image.loadImage
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty

class HolderUserRepositories(
    private val mBinding: ItemRepositoriesBinding
) : RecyclerView.ViewHolder(mBinding.root) {

    private val mContext = mBinding.root.context

    fun bindRepositories(data: UserRepositories) = with(mBinding) {
        data.avatar_url?.let { ivImage.loadImage(mContext, it) }
        tvName.text = data.name.checkNullOrEmpty()
        tvDescription.text = data.description.checkNullOrEmpty()
        tvStarCount.text = data.stargazers_count.checkNullOrEmpty()
        tvLastUpdated.text = data.updated_at.checkNullOrEmpty()
    }

}