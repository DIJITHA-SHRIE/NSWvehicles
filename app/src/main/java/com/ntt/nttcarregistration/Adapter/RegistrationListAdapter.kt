package com.ntt.nttcarregistration.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntt.nttcarregistration.Model.RegistrationDetail
import com.ntt.nttcarregistration.R
import com.ntt.nttcarregistration.UI.RegistrationDetails
import com.ntt.nttcarregistration.databinding.AdapterBinding

class RegistrationListAdapter (val items:List<RegistrationDetail>, val context: Context): RecyclerView.Adapter<RegistrationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBinding.inflate(inflater, parent, false)
        return RegistrationViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        holder.bind(items.get(position),context)

    }


}
class RegistrationViewHolder constructor(itemView: View, binding: AdapterBinding):
    RecyclerView.ViewHolder(itemView){

    private var mBinding: AdapterBinding

    init {
        mBinding = binding
    }



    fun bind(getitems:RegistrationDetail,context: Context){
        mBinding.yearVal.text = "${getitems.vehicle.model} "
        mBinding.brandval.text="${getitems.vehicle.make}"
        mBinding.bodyTypeVal.text = " ${getitems.vehicle.type}"
        if(!getitems.registration.expired){
        mBinding.statusVal.text= context.resources.getString(R.string.registered)
        }
        else{
            mBinding.statusVal.text= context.resources.getString(R.string.expired)

        }




        itemView.setOnClickListener{
            val intent = Intent(context,RegistrationDetails::class.java)
            intent.putExtra("DETAILS",getitems)
            context.startActivity(intent)
        }




    }



}