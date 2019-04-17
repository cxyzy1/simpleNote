package com.cxyzy.note.ui.activity

import com.cxyzy.note.R
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : BaseNoteActivity() {

    override fun initTitle() {
        editNoteToolbar.title = getString(R.string.edit_note)
    }

    override fun initContent() {
        contentET.setText(note!!.content)
    }

    override fun initMenu() {
        editNoteToolbar.menu.findItem(R.id.delNoteMenuItem).isVisible = true
    }

    override fun saveNote(content: String) {
        note?.let {
            it.content = content
            viewModel.update(it)
        }

        finish()
    }
}
