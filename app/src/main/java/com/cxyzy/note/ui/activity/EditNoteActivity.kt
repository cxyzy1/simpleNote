package com.cxyzy.note.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cxyzy.note.ext.obtainViewModel
import com.cxyzy.note.ui.adapter.NoteAdapter
import com.cxyzy.note.viewmodels.NoteViewModel
import com.cxyzy.note.R
import kotlinx.android.synthetic.main.activity_note.*

class EditNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
    }

}
