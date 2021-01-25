package com.ntt.nttcarregistration.Model

import java.io.Serializable

data class RegistrationStatus (
    val expired:Boolean,
    val expiry_date:String
):Serializable
