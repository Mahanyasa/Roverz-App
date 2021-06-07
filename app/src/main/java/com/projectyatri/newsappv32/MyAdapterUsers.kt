package com.projectyatri.newsappv32

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import com.projectyatri.newsappv32.ModelClasses.Users

class MyAdapterUsers(private val userList: ArrayList<Users>  ,param: ValueEventListener) :
    RecyclerView.Adapter<MyAdapterUsers.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        val viewHolder = MyViewHolder(itemView)
        //return MyViewHolder(itemView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyAdapterUsers.MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.username.text = currentItem.getUserName()
        holder.college.text = currentItem.getCollege()
        holder.instagram.text = currentItem.getInsta()

    }

    override fun getItemCount(): Int {

        return userList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val username: TextView = itemView.findViewById(R.id.search_username_text)
        val college: TextView = itemView.findViewById(R.id.search_college_text)
        val instagram: TextView = itemView.findViewById(R.id.search_instagram_text)
    }

}
interface SearchItemClicked{
    fun onItemClicked(item: Users)
}
