package com.fachrizalmrsln.githubuserapp.presentation.home_page

import androidx.recyclerview.widget.RecyclerView
import com.fachrizalmrsln.githubuserapp.databinding.ItemSearchResultsBinding
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.utils.image.loadImage

class HolderSearchResults(
    private val mBinding: ItemSearchResultsBinding,
    private val listener: AdapterSearchResults.ListenerSearchResults
) : RecyclerView.ViewHolder(mBinding.root) {

    private val mContext = mBinding.root.context

    fun bindSearchResults(data: UserModel) = with(mBinding) {
        ivImage.loadImage(mContext, data.avatar_url)
        tvName.text = data.login
        tvUsername.text = data.login
        tvAbout.text = data.organizations_url
        tvLocation.text = data.login
        tvEmail.text = data.login

        itemView.setOnClickListener {
            listener.onSearchItemCLick(data)
        }
    }

}