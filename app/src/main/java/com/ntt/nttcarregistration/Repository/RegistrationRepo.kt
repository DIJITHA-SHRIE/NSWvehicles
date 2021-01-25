package com.ntt.nttcarregistration.Repository

import com.ntt.nttcarregistration.Model.Registration
import com.ntt.nttcarregistration.Network.Network

class RegistrationRepo (val network: Network) {

    suspend fun refreshRegistrationDetail():Registration{
        val result:Registration
        try{
            result= network.fetchRegistration()
        }
        catch(cause:Throwable){
            throw  EmployeeRegistrationRefreshError("Unable to fetch list",cause)

        }

        return result

    }


}


class EmployeeRegistrationRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)