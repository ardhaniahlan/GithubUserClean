package org.apps.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.apps.core.R
import org.apps.core.domain.User
import java.util.ArrayList

class UserAdapter: RecyclerView.Adapter<org.apps.core.adapter.UserAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val avatarUrl: ImageView = itemView.findViewById(R.id.img_user)
        val tvUser : TextView = itemView.findViewById(R.id.tv_user)
    }

    private var users = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(newListData: List<User>?) {
        if (newListData == null) return
        users.clear()
        users.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.tvUser.text = user.login
        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .centerCrop()
            .into(holder.avatarUrl)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(user)
        }
    }

    override fun getItemCount(): Int = users.size

}