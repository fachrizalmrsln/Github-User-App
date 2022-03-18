package com.fachrizalmrsln.githubuserapp.presentation.home_page.holder

import android.view.View
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.recyclerview.widget.RecyclerView
import com.fachrizalmrsln.githubuserapp.databinding.ItemLoadingListBinding

class HolderLoading(
    private val mBinding: ItemLoadingListBinding
) : RecyclerView.ViewHolder(mBinding.root) {

    fun bindLoading(loadingState: Boolean) = with(mBinding) {
        if (loadingState) {
            val rotate = RotateAnimation(
                0f,
                360f,
                RELATIVE_TO_SELF,
                0.5f,
                RELATIVE_TO_SELF,
                0.5f
            )
            rotate.duration = 1000
            rotate.interpolator = LinearInterpolator()
            rotate.repeatCount = INFINITE

            ivLoading.startAnimation(rotate)
            containerLoading.visibility = View.VISIBLE
        }
        else {
            ivLoading.clearAnimation()
            containerLoading.apply {
                removeAllViews()
                removeAllViewsInLayout()
                removeView(this)
                visibility = View.GONE
            }
        }

    }

}