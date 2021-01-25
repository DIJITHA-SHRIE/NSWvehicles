package com.ntt.nttcarregistration.Model

import java.io.Serializable

data class VehicleDetails(
    val type:String,
    val make:String,
    val model:String,
    val colour:String,
    val vin:String,
    val tare_weight:Int,
    val gross_mass:String

):Serializable
