package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ShowAdapter (val context: Context, var notificationData: List<ShowDatalist>, var fragment:Fragment) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {
    private var mContext = context





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (notificationData[position].getStringerID() != null) {
            holder.id.text = notificationData[position].getStringerID().toString()
        } else {
            holder.id.text = "Null"
        }


        if (notificationData[position].getName() != null) {
            holder.name.text = notificationData[position].getName()
        } else {
            holder.name.text = "Null"
        }


        if (notificationData[position].getAddress() != null) {
            holder.address.text = notificationData[position].getAddress().toString()
        } else {
            holder.address.text = "Null"
        }

        if (notificationData[position].getAge() != null) {
            holder.age_show.text = notificationData[position].getAge().toString()
        } else {
            holder.age_show.text = "Null"
        }


        if (notificationData[position].getPhoneNumber() != null) {
            holder.phone_number_show.text = notificationData[position].getPhoneNumber().toString()
        } else {
            holder.phone_number_show.text = "Null"
        }

        holder.delete.setOnClickListener(View.OnClickListener {
            (fragment as ShowDataFragment).deletedata(
                notificationData[position].getStringerID().toString(), position
            )
        })


        holder.edit.setOnClickListener(View.OnClickListener {
            val editfragment = EditDataFragment()

            val bundle = Bundle()
            if (notificationData[position].getStringerID() != null) {
                bundle.putString("YourKey", notificationData[position].getStringerID().toString())
            }

            if (notificationData[position].getName() != null) {
                bundle.putString("YourKey1", notificationData[position].getName().toString())
            }
            if (notificationData[position].getAge() != null) {
                bundle.putString("YourKey2", notificationData[position].getAge().toString())
            }
            if (notificationData[position].getPhoneNumber() != null) {
                bundle.putString("YourKey3", notificationData[position].getPhoneNumber().toString())
            }
            if (notificationData[position].getPassword() != null) {
                bundle.putString("YourKey4", notificationData[position].getPassword().toString())
            }
            if (notificationData[position].getStartTiming() != null) {
                bundle.putString("YourKey5", notificationData[position].getStartTiming().toString())
            }
            if (notificationData[position].getCloseTiming() != null) {
                bundle.putString("YourKey6", notificationData[position].getCloseTiming().toString())
            }
            if (notificationData[position].getAddress() != null) {
                bundle.putString("YourKey7", notificationData[position].getAddress().toString())
            }

            editfragment.arguments = bundle
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, editfragment).commit()
        })
    }

    override fun getItemCount(): Int {
        return notificationData.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var name: TextView
        var address: TextView
        var age_show: TextView
        var phone_number_show: TextView
        var delete: ImageView
        var edit: ImageView

        init {
            id = itemView.findViewById<View>(R.id.id_show) as TextView
            name = itemView.findViewById<View>(R.id.name_show) as TextView
            address = itemView.findViewById<View>(R.id.address_show) as TextView
            age_show = itemView.findViewById<View>(R.id.age_show) as TextView
            phone_number_show = itemView.findViewById<View>(R.id.phone_number_show) as TextView
            delete = itemView.findViewById<View>(R.id.delete_detail) as ImageView
            edit = itemView.findViewById<View>(R.id.edit_detail) as ImageView
        }
    }
}