package com.test.app.utils

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.test.app.R

class PostLoadMoreView : LoadMoreView() {

    override fun getLayoutId(): Int {
        return R.layout.load_more_view
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}