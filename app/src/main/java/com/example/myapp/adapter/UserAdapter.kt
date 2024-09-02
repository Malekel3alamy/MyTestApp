package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapp.R
import com.example.myapp.model.UserItem

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : ViewHolder(view) {

        val userName = view.findViewById<TextView>(R.id.userTV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false))

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.userName.text = user.name

        holder.itemView.setOnClickListener {
            onUserClick?.invoke(user)
        }
    }

    private val differCallback = object  : DiffUtil.ItemCallback<UserItem>(){
        override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {

            return  oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)

    var onUserClick :((UserItem) -> Unit)? = null

}