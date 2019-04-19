package com.cxyzy.note.viewmodels

import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.repository.NoteRepositoryImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NoteViewModel(private val noteRepository: NoteRepositoryImpl) : BaseViewModel() {
    val noteList = noteRepository.list()


    fun add(content: String,
            onSuccess: (() -> Unit)? = null,
            onError: ((throwable: Throwable) -> Unit)? = null,
            onFinish: (() -> Unit)? = null) {

        launchOnUITryCatch(
                {
                    noteRepository.add(content)
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
            noteRepository.update(note)
        }
    }

    fun del(id: Long) {
        GlobalScope.launch {
            noteRepository.del(id)
        }
    }
}