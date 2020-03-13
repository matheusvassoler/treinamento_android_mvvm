package com.example.aulamvvm.userDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.aula3mvvn.model.User
import com.example.aulamvvm.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_relative.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class RelativeAdapter(val users: List<User>): RecyclerView.Adapter<RelativeAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //AttachToRoot informa se o inflate deve estar atrelado ao pai, no caso, RecycleView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_relative, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = users.count()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    //View holder responsavel por mapear os valores de user para a lista
    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val name = itemView.txt_name

        fun bindView(user: User) {
            name.text = user.name
            /*
            address.text = user.address
            //Se o dado vier com informação, haverá a atribuição da imagem no elemento
            //strUserImage se refere ao imgUrl
            user.imgUrl.let {strUserImage ->
                Picasso.get().load(strUserImage).into(imageView)
            }*/
        }
    }
}