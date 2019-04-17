package com.cxyzy.note.viewmodels

import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NoteViewModel : BaseViewModel() {
    val noteList = NoteRepository.getNoteList()


    fun add(content: String,
            onSuccess: (() -> Unit)? = null,
            onError: ((throwable: Throwable) -> Unit)? = null,
            onFinish: (() -> Unit)? = null) {

        launchOnUITryCatch(
                {
                    NoteRepository.add(content)
                    onSuccess?.invoke()
                },
                {
                    onError?.invoke(it)
                    Timber.e(it)
                },
                { onFinish?.invoke() },
                true)
    }

    fun update(note: Note) {
        GlobalScope.launch {
            NoteRepository.update(note)
        }
    }

    fun del(id: Long) {
        GlobalScope.launch {
            NoteRepository.del(id)
        }
    }
}