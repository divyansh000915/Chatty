package com.paru.chatty.AdapterClasses

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paru.chatty.Activity.MessageChatActivity
import com.paru.chatty.Activity.WelcomeActivity
import com.paru.chatty.ModelClasses.Users
import com.paru.chatty.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.profile_image
import kotlinx.android.synthetic.main.activity_main.view.user_name


class UserAdapter(
    mContext:Context,
    mUsers:List<Users>,
    isChatCheck:Boolean
):RecyclerView.Adapter<UserAdapter.ViewHolder?>()
{
    private val mContext:Context
    private val mUsers:List<Users>
    private val isChatCheck:Boolean

    init {
        this.mUsers=mUsers
        this.mContext=mContext
        this.isChatCheck=isChatCheck
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layout,parent,false)
        return UserAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user :Users=mUsers[position]

        holder.userNameTxt.text=user!!.getUserName()
        Picasso.get().load(user.getProfile()).placeholder(R.drawable.profile).into(holder.profileImageView)


        holder.itemView.setOnClickListener{
            val options= arrayOf<CharSequence>(
                "Send Message",
                "Visit Profile"
            )
            val builder:AlertDialog.Builder=AlertDialog.Builder(mContext)
            builder.setTitle("What do you want?")
            builder.setItems(options,DialogInterface.OnClickListener{dialog, which ->  
                if(position == 0)
                {
                    val intent= Intent(mContext,
                        MessageChatActivity::class.java)
                    intent.putExtra("visit_id",user.getUID())
                    mContext.startActivity(intent)

                }
                if(position == 1)
                {

                }
            })
            builder.show()
        }

    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        var userNameTxt:TextView
        var profileImageView:ImageView
        var onlineImageView:ImageView
        var offlineImageView:ImageView
        var lastMessageTxt:TextView

        init {
            userNameTxt=itemView.findViewById(R.id.username)
            profileImageView=itemView.findViewById(R.id.profile_image)
            onlineImageView=itemView.findViewById(R.id.image_online)
            offlineImageView=itemView.findViewById(R.id.image_offline)
            lastMessageTxt=itemView.findViewById(R.id.message_last)
        }
    }

}