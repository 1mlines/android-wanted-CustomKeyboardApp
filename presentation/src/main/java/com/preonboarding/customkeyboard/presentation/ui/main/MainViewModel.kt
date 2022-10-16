package com.preonboarding.customkeyboard.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.usecase.DeleteBookmarkUseCase
import com.preonboarding.customkeyboard.domain.usecase.GetBookmarkListUseCase
import com.preonboarding.customkeyboard.domain.usecase.SaveBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBookmarkListUseCase: GetBookmarkListUseCase,
    private val saveBookmarkUseCase: SaveBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
) : ViewModel() {
    private var localBookmarkList = mutableListOf<Bookmark>()

    // 북마크 (복사된 단어) 리스트
    private val _localBookmarks = MutableLiveData<List<Bookmark>>()
    val localBookmarks: LiveData<List<Bookmark>> get() = _localBookmarks

    init {
        // localBookmark 변수 초기화 이후에 init block 호출
        getBookmarks()
    }

    private fun getBookmarks() {
        kotlin.runCatching {
            viewModelScope.launch {
                localBookmarkList = getBookmarkListUseCase.invoke().toMutableList()
                _localBookmarks.postValue(localBookmarkList.toList())
            }
        }
            .onSuccess {
                Timber.tag("TAG").e("success get bookmarks")
            }
    }

    fun saveBookmark(bookmark: Bookmark) {
        kotlin.runCatching {
            viewModelScope.launch {
                saveBookmarkUseCase.invoke(bookmark = bookmark)
            }
        }
            .onSuccess {
                localBookmarkList.add(bookmark)
                _localBookmarks.setValue(localBookmarkList.toList())
            }
    }

    fun deleteBookmark(bookmark: Bookmark) {
        kotlin.runCatching {
            viewModelScope.launch {
                deleteBookmarkUseCase.invoke(bookmark = bookmark)
            }
        }
            .onSuccess {
                localBookmarkList.remove(bookmark)
                _localBookmarks.setValue(localBookmarkList.toList())
            }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}