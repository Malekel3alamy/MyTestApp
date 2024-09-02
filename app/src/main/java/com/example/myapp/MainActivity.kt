package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.adapter.UserAdapter
import com.example.myapp.api.RetrofitInstance
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.viewmodel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val viewModel = UserViewModel()
    private val userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        viewModel.getUsers()

        lifecycleScope.launch {
            viewModel.usersList.collectLatest {
                userAdapter.differ.submitList(it)
            }
        }

        userAdapter.onUserClick ={
            val dialog = BottomSheetDialog(this)
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            val view = layoutInflater.inflate(R.layout.details_item,null,false)
            dialog.setContentView(view)
            dialog.show()

            val tvEmail = view.findViewById<TextView>(R.id.emailTV)
            val btn_cancel = view.findViewById<Button>(R.id.cancelBTN)

             tvEmail.text = it.email

            btn_cancel.setOnClickListener {
                dialog.dismiss()
            }

        }








    }
    fun setupRV(){
        binding.userRV.apply {
            adapter = userAdapter
            layoutManager =LinearLayoutManager(context)
        }
    }


}