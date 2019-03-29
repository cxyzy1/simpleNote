package com.cxyzy.note.ui.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.note.App.Companion.context
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.utils.obtainViewModel
import com.cxyzy.note.ExtraKey.KEY_NOTE
import com.cxyzy.note.utils.KeyBoardUtil.hideSoftInput
import com.cxyzy.note.utils.KeyBoardUtil.showSoftInput
import com.cxyzy.note.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*


class EditNoteActivity : AppCompatActivity() {
    private var note: Note? = null
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.cxyzy.note.R.layout.activity_edit_note)
        viewModel = obtainViewModel(NoteViewModel::class.java)
        processIntent()
        initViews()
        initListeners()
    }

    private fun initListeners() {
        editNoteToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                com.cxyzy.note.R.id.submit -> saveNote()
            }
            false
        }
    }

    private fun saveNote() {
        val content = contentET.text
        if (note == null) {
            viewModel.add(content.toString())
        } else {
            note!!.content = content.toString()
            viewModel.update(note!!)
        }
        finish()
    }

    private fun processIntent() {
        note = intent?.getParcelableExtra(KEY_NOTE)
    }

    private fun initViews() {
        editNoteToolbar.inflateMenu(com.cxyzy.note.R.menu.edit_note_menu)
        editNoteToolbar.setNavigationOnClickListener { finish() }
        note?.let {
            contentET.setText(note!!.content)
        }

        showSoftInput(this, contentET)

    }



}
