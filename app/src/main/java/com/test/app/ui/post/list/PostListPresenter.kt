package com.test.app.ui.post.list

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.test.app.PostRepository
import com.test.app.model.Post
import com.test.app.utils.Constants.INIT_PAGE
import javax.inject.Inject

@InjectViewState
class PostListPresenter @Inject constructor(
    private val postRepository: PostRepository
): MvpPresenter<PostListView>() {

    private val compositeDisposable = CompositeDisposable()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getPosts(INIT_PAGE)
    }

    fun getPosts(page: Int = INIT_PAGE) {
        compositeDisposable.add(postRepository.getPosts(page)
            .map { result ->
                val nextPage = result.page + 1
                val posts = result.hits
                Log.d("Post_page", page.toString())
                Pair<Int, List<Post>?>(nextPage, posts)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .doFinally { viewState.hideLoading() }
            .subscribe(
                { result -> viewState.result(result.first, result.second) },
                { error ->
                    viewState.showError(error.localizedMessage)
                    viewState.hideLoading()
                })
        )
    }



    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}