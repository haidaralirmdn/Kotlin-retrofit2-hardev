package com.app.firstappkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.firstappkotlin.R
import com.app.firstappkotlin.model.ResponseUser
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var dataList : List<ResponseUser> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val name = dataList[position].name
        val descrip = dataList[position].description
        val avatarUrl = dataList[position].owner?.avatarUrl

        if (descrip != null) {
            holder.tvDesc.text = descrip
        } else {
            val descEmpty = "Tidak ada deskripsi"
            holder.tvDesc.text = descEmpty
        }

        holder.tvName.text = name

        Glide.with(context).load(avatarUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

    fun setUserListItems(userList: List<ResponseUser>){
        this.dataList = userList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val tvName: TextView = itemView!!.findViewById(R.id.tv_name)
        val tvDesc: TextView = itemView!!.findViewById(R.id.tv_desc)
        val image: ImageView = itemView!!.findViewById(R.id.iv_avatar)

    }
}