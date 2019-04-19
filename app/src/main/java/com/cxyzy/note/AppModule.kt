package com.cxyzy.note

import com.cxyzy.note.db.AppDatabase
import com.cxyzy.note.db.repository.NoteRepository
import com.cxyzy.note.viewmodels.NoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createdAtStart = false) { get<AppDatabase>().noteDao() }
    factory { NoteRepository(get()) }
    viewModel { NoteViewModel(get()) }
}
