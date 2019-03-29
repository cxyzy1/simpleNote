package com.cxyzy.note.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cxyzy.note.InjectionUtil
import com.cxyzy.note.db.repository.NoteRepository

class ViewModelFactory private constructor(
        private val noteRepository: NoteRepository
//    ,
//    private val gankFilterRepository: GankFilterRepository,
//    private val searchRepository: GankSearchRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(NoteViewModel::class.java) -> {
                        NoteViewModel(noteRepository)
                    }
//                isAssignableFrom(GankFilterViewModel::class.java) -> {
//                    GankFilterViewModel(gankFilterRepository)
//                }
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
                }

            } as T


    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(
//                    InjectionUtil.provideGankRepository(application),
                            InjectionUtil.getTaskRepository()
                    )
                }
    }
}