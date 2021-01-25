package com.ntt.nttcarregistration.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntt.nttcarregistration.Model.Registration
import com.ntt.nttcarregistration.Repository.EmployeeRegistrationRefreshError
import com.ntt.nttcarregistration.Repository.RegistrationRepo
import com.ntt.nttcarregistration.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class RegistrationDetailVM (private val repository: RegistrationRepo) : ViewModel() {

    val mutableRegistrationLiveData = MutableLiveData<Registration>()


    companion object {
        val FACTORY = singleArgViewModelFactory(::RegistrationDetailVM)
    }

    fun onGetRegistrationDetail(){
        getEmployeeDetail()
    }



    fun getEmployeeDetail(){
        viewModelScope.launch{
            try{
                mutableRegistrationLiveData.value = repository.refreshRegistrationDetail()
            }
            catch(error: EmployeeRegistrationRefreshError){


            }
            finally {

            }

        }
    }


}