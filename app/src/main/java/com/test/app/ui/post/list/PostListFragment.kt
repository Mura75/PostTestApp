package com.test.app.ui.post.list


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.loadmore.LoadMoreView
import kotlinx.android.synthetic.main.fragment_post_list.*
import com.test.app.App

import com.test.app.R
import com.test.app.di.component.DaggerPresenterComponent
import com.test.app.model.Post
import com.test.app.utils.Constants.INIT_PAGE
import com.test.app.utils.PostLoadMoreView
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

class PostListFragment : MvpAppCompatFragment(), PostListView {

    @InjectPresenter
    lateinit var postsListPresenter: PostListPresenter

    @ProvidePresenter
    fun providePostListPresenter(): PostListPresenter = DaggerPresenterComponent.builder()
        .appComponent((activity?.application as App).appComponent)
        .build()
        .getPostListPresenter()

    private var postListAdapter: PostListAdapter? = null

    private var currentPage = INIT_PAGE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        setAdapter()
        setPostSize(0)
    }

    override fun showLoading() {
        if (currentPage == 1) {
            srlPosts.isRefreshing = true
        }
    }

    override fun hideLoading() {
        srlPosts.isRefreshing = false
        postListAdapter?.loadMoreComplete()
    }

    override fun showError(errorMessage: String) {
        postListAdapter?.apply {
            loadMoreComplete()
            disableLoadMoreIfNotFullPage()
        }
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun result(page: Int, posts: List<Post>?) {
        postListAdapter?.apply {
            if (posts.isNullOrEmpty()) {
                loadMoreComplete()
                disableLoadMoreIfNotFullPage()
            }
            else {
                addData(posts)
                currentPage = page
                setPostSize(data.size)
            }
        }
    }

    private fun bindViews() {
        val divider = HorizontalDividerItemDecoration.Builder(context)
            .color(R.color.colorDivider)
            .size(1)
            .build()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(divider)
        }

        srlPosts.setOnRefreshListener {
            currentPage = INIT_PAGE
            postListAdapter?.apply {
                clearAllData()
                setPostSize(0)
            }
            postsListPresenter.getPosts()
        }
    }

    private fun setAdapter() {
        postListAdapter = PostListAdapter(R.layout.item_row_post)
        postListAdapter?.apply {
            setOnLoadMoreListener(object: BaseQuickAdapter.RequestLoadMoreListener {
                override fun onLoadMoreRequested() {
                    postsListPresenter.getPosts(currentPage)
                }
            }, recyclerView)
            setLoadMoreView(PostLoadMoreView())
        }
        recyclerView.adapter = postListAdapter
    }

    private fun setPostSize(size: Int) {
        (activity as AppCompatActivity).setTitle("Posts total number: $size")
    }
}
