package com.test.app.ui.post.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.test.app.R
import com.test.app.model.Post

class PostListAdapter(
    private val layoutResId: Int
) : BaseQuickAdapter<Post, BaseViewHolder>(layoutResId, null) {


    override fun convert(helper: BaseViewHolder?, item: Post?) {
        helper?.apply {
            if (item != null) {
                setText(R.id.tvTitle, item.title)
                setText(R.id.tvTime, item.getTime())
            }
        }
    }

    fun clearAllData() {
        data.clear()
        notifyDataSetChanged()
    }
}