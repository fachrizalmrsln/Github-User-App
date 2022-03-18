package com.fachrizalmrsln.githubuserapp.presentation.home_page

import androidx.recyclerview.widget.RecyclerView
import com.fachrizalmrsln.githubuserapp.databinding.ItemSearchResultsBinding
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.utils.image.loadImage
import com.fachrizalmrsln.githubuserapp.utils.strings.isUserName

class HolderSearchResults(
    private val mBinding: ItemSearchResultsBinding,
    private val listener: AdapterSearchResults.ListenerSearchResults
) : RecyclerView.ViewHolder(mBinding.root) {

    private val mContext = mBinding.root.context

    fun bindSearchResults(data: SearchItemModel) = with(mBinding) {
        ivImage.loadImage(mContext, data.avatar_url)
        tvName.text = data.user_full_name
        tvUsername.text = data.login.isUserName()
        tvAbout.text = data.bio
        tvLocation.text = data.location
        tvEmail.text = data.email

        itemView.setOnClickListener {
            listener.onSearchItemCLick(data)
        }
    }

}