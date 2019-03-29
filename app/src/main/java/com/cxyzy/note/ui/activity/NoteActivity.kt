package com.cxyzy.note.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cxyzy.note.ext.obtainViewModel
import com.cxyzy.note.ui.adapter.NoteAdapter
import com.cxyzy.note.viewmodels.NoteViewModel
import com.cxyzy.note.R
import com.cxyzy.note.utils.ExtraKey.KEY_NOTE
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val viewModel = obtainViewModel(NoteViewModel::class.java)
        val adapter = NoteAdapter()
        adapter.setOnItemClick { note ->
            val intent = Intent(this, EditNoteActivity().javaClass)
            intent.putExtra(KEY_NOTE, note)
            startActivity(intent)
        }
        taskRv.adapter = adapter
        viewModel.taskList.observe(this, Observer { adapter.submitList(it) })

        //设置下拉刷新转圈的颜色
//        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

}
