package com.preonboarding.customkeyboard.presentation.ui.input

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.model.ToolbarMenu
import com.preonboarding.customkeyboard.domain.usecase.DeleteBookmarkUseCase
import com.preonboarding.customkeyboard.domain.usecase.GetBookmarkListUseCase
import com.preonboarding.customkeyboard.domain.usecase.SaveBookmarkUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val getBookmarkListUseCase: GetBookmarkListUseCase,
    private val saveBookmarkUseCase: SaveBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
) : ViewModel() {
    private var localBookmarkList = mutableListOf<Bookmark>()

    // 북마크 (복사된 단어) 리스트
    private val _localBookmarks = MutableLiveData<List<Bookmark>>()
    val localBookmarks: LiveData<List<Bookmark>> get() = _localBookmarks

    // 현재 선택된 툴바 메뉴
    private val _toolbarMenu = MutableLiveData<ToolbarMenu>(ToolbarMenu.KEYBOARD)
    val toolbarMenu: LiveData<ToolbarMenu> get() = _toolbarMenu

    // 클립보드 화면에서 선택한 붙여 넣기 할 북마크
    private val _curPasteBookmark = MutableLiveData<Bookmark>()
    val curPasteBookmark: LiveData<Bookmark> get() = _curPasteBookmark

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

    fun changeMenu() {
        if (_toolbarMenu.value == ToolbarMenu.KEYBOARD) {
            _toolbarMenu.value = ToolbarMenu.CLIPBOARD
        }
        else {
            _toolbarMenu.value = ToolbarMenu.KEYBOARD
        }
    }

    fun updatePasteBookmark(bookmark: Bookmark) {
        _curPasteBookmark.value = bookmark
    }

    companion object {
        private const val TAG = "InputViewModel"
    }
}