package com.test.app.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.app.R
import com.test.app.ui.post.list.PostListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, PostListFragment())
                .commit()
        }
    }
}
