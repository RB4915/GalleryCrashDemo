package com.shadowapps.gallerycrashdemo.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shadowapps.gallerycrashdemo.R

class ViewImagesAdapter (private val mValues: ArrayList<String>,
                         private val mCtx: Context,
                        ):RecyclerView.Adapter<ViewImagesAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_view_images, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = mValues[position]
        holder.mviewImage.setImageURI(Uri.parse(image))

        holder.mView.setOnClickListener {
          Toast.makeText(mCtx,"Image clicked!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mviewImage: ImageView = mView.findViewById(R.id.imgImage) as ImageView

    }
}
