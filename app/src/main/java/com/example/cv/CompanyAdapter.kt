package com.example.cv

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.cv.models.Company
import com.example.cv.utils.AppDataBase

class CompanyAdapter(private val mList: MutableList<Company>) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {


    private lateinit var builder : AlertDialog.Builder


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        val image = ItemsViewModel.image
        val fileUri = Uri.parse(image)
        holder.imageView.setImageURI(fileUri)

        holder.nameTextView.text = ItemsViewModel.name
        holder.adressTextView.text = ItemsViewModel.address
        holder.starDateTextView.text = ItemsViewModel.startDate
        holder.endDateTextView.text = ItemsViewModel.endDate
        holder.deleteButton.setOnClickListener {

            builder = AlertDialog.Builder(holder.itemView.context)

            builder.setTitle("Delete").setMessage("Are you sure you want to delete it?")
                .setCancelable(true)
                .setNegativeButton("No"){dialogInterface,it ->
                    dialogInterface.cancel()
                }
                .setPositiveButton("Yes"){dialogInterface,it ->
                    AppDataBase.getDatabase(holder.itemView.context).companyDao().delete(mList[position])
                    mList.remove(mList[position])
                    notifyDataSetChanged()
                }
                .show()

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val adressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        val starDateTextView: TextView = itemView.findViewById(R.id.startDateTextView)
        val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }
}

