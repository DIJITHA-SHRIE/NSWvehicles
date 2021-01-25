package com.ntt.nttcarregistration.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ntt.nttcarregistration.Model.RegistrationDetail
import com.ntt.nttcarregistration.R
import com.ntt.nttcarregistration.databinding.ActivityRegistrationDetailsBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RegistrationDetails : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_details)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration_details)
        binding.setLifecycleOwner(this)
        val getRegistrationDetails = intent.getSerializableExtra("DETAILS") as? RegistrationDetail

        binding.plateNumber.text = "${getRegistrationDetails?.plate_number}"

        val expiryDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formatExpiryDate = expiryDate.parse(getRegistrationDetails?.registration?.expiry_date)
        val desiredFormat = SimpleDateFormat("dd, MMM yyyy").format(formatExpiryDate)
        binding.expiry.text = desiredFormat


        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val compareDate = SimpleDateFormat("dd/MM/yyyy").format(formatExpiryDate)
        val days = getDaysBetweenDates(currentDate, compareDate,"dd/MM/yyyy")




        binding.numdays.text = "${days}days"
        binding.color.text = "${getRegistrationDetails?.vehicle?.colour}"
        val vinMask = getRegistrationDetails?.vehicle?.vin
        if(vinMask!!.length > 4){
           val subVin =  vinMask.substring(vinMask.length - 4)
            binding.vin.text = subVin

        }
        binding.tareMass.text = "${getRegistrationDetails?.vehicle?.tare_weight}"
        if(getRegistrationDetails.vehicle.gross_mass == null){
            binding.gvm.text = "NA"
        }
        else{
            binding.gvm.text = "${getRegistrationDetails?.vehicle?.gross_mass}"

        }
        binding.insurer.text = "${getRegistrationDetails?.insurer?.name}"
        binding.code.text = "${getRegistrationDetails?.insurer?.code}"
        if(!getRegistrationDetails?.registration?.expired) {
            binding.regStatus.text = "Registered"
        }
        else{
            binding.regStatus.text = "Expired"

        }
        binding.vehicleDetails.text = "Vehicle Details of${getRegistrationDetails?.vehicle?.model} ${getRegistrationDetails?.vehicle?.make}"



    }

    fun getDaysBetweenDates(firstDateValue: String, secondDateValue: String, format: String): String
    {
        val sdf = SimpleDateFormat(format, Locale.getDefault())

        val firstDate = sdf.parse(firstDateValue)
        val secondDate = sdf.parse(secondDateValue)

        if (firstDate == null || secondDate == null)
            return 0.toString()

        return (((secondDate.time - firstDate.time) / (1000 * 60 * 60 * 24)) + 1).toString()
    }

}