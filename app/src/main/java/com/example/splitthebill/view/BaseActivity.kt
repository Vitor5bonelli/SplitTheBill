package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity

sealed class BaseActivity: AppCompatActivity() {
    protected val EXTRA_FRIEND = "Friend"
    protected val EXTRA_VIEW_FRIEND = "ViewFriend"
    protected val EXTRA_SPLIT = "Split"
}