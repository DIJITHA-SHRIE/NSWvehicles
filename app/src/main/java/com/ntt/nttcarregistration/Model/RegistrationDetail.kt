package com.ntt.nttcarregistration.Model

import java.io.Serializable

data class RegistrationDetail(
    val plate_number:String,
    val registration:RegistrationStatus,
    val vehicle:VehicleDetails,
    val insurer:InsuranceDetails
):Serializable
