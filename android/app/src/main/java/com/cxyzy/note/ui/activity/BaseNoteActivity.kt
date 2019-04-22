package com.cxyzy.note.ui.activity

import com.cxyzy.note.ExtraKey.KEY_NOTE
import com.cxyzy.note.R
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.utils.KeyBoardUtil.showSoftInput
import com.cxyzy.note.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * Base class for add and edit note
 */
abstract class BaseNoteActivity : BaseActivity<NoteViewModel>() {

    override fun viewModel(): NoteViewModel = getViewModel()

    protected var note: Note? = null

    override fun layoutId(): Int = R.layout.activity_edit_note

    override fun prepareBeforeInitView() {
        note = intent?.getParcelableExtra(KEY_NOTE)
    }

    override fun initView() {
        editNoteToolbar.inflateMenu(R.menu.edit_note_menu)
        editNoteToolbar.setNavigationOnClickListener { finish() }
        initTitle()
        initContent()
        initMenu()
        showSoftInput(this, contentET)
    }

    override fun initListeners() {
        editNoteToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.submitNoteMenuItem -> saveNote(contentET.text.toString())
                R.id.delNoteMenuItem -> delNote()
            }
            false
        }

    }

    private fun delNote() {
        note?.let { viewModel().del(it.id) }
        finish()
    }

    abstract fun initTitle()
    open fun initContent() {}
    abstract fun initMenu()
    abstract fun saveNote(content: String)
}
