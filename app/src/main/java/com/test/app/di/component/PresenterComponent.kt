package com.test.app.di.component

import dagger.Component
import com.test.app.di.Presenter
import com.test.app.ui.post.list.PostListPresenter

@Presenter
@Component(dependencies = [AppComponent::class])
interface PresenterComponent {

    fun getPostListPresenter(): PostListPresenter

}