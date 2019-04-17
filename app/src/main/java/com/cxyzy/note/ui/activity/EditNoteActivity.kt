package com.cxyzy.note.ui.activity

import com.cxyzy.note.ExtraKey.KEY_NOTE
import com.cxyzy.note.R
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.utils.KeyBoardUtil.showSoftInput
import com.cxyzy.note.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*

/**
 * Add or edit note
 */
class EditNoteActivity : BaseActivity<NoteViewModel>() {

    override fun providerVMClass(): Class<NoteViewModel> = NoteViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_edit_note

    private var note: Note? = null

    override fun prepareBeforeInitView() {
        note = intent?.getParcelableExtra(KEY_NOTE)
    }

    override fun initView() {
        editNoteToolbar.inflateMenu(R.menu.edit_note_menu)
        editNoteToolbar.setNavigationOnClickListener { finish() }
        if (isToEditNote()) {
            editNoteToolbar.title = getString(R.string.edit_note)
            contentET.setText(note!!.content)
        } else {
            editNoteToolbar.title = getString(R.string.add_note)
            editNoteToolbar.menu.findItem(R.id.delNoteMenuItem).isVisible = false
        }

        showSoftInput(this, contentET)

    }

    override fun initListeners() {
        editNoteToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.submitNoteMenuItem -> saveNote()
                R.id.delNoteMenuItem -> delNote()
            }
            false
        }

    }

    private fun isToEditNote() = note != null
    private fun delNote() {
        viewModel.del(note!!.id)
        finish()
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

}
