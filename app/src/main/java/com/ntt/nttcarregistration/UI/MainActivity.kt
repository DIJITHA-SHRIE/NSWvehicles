package com.ntt.nttcarregistration.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntt.nttcarregistration.Adapter.RegistrationListAdapter
import com.ntt.nttcarregistration.Model.Registration
import com.ntt.nttcarregistration.Model.RegistrationDetail
import com.ntt.nttcarregistration.Network.getNetworkService
import com.ntt.nttcarregistration.R
import com.ntt.nttcarregistration.Repository.RegistrationRepo
import com.ntt.nttcarregistration.ViewModel.RegistrationDetailVM
import com.ntt.nttcarregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var registrationDetailVM: RegistrationDetailVM
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.setLifecycleOwner(this)

        val repository = RegistrationRepo(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this, RegistrationDetailVM.FACTORY(repository
            )
            ).get(RegistrationDetailVM::class.java)

        viewModel.onGetRegistrationDetail()

        viewModel.mutableRegistrationLiveData.observe(this){value ->
            value?.let {
                Log.i("RegistrationResponse",value.registrations.get(0).plate_number)

                val registrationlist:List<RegistrationDetail> = value.registrations

                try {
                    var adapter: RegistrationListAdapter = RegistrationListAdapter(registrationlist,this!!)
                    binding.registrationListRecycle!!.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.registrationListRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("EmployeeAdapterExp",e.localizedMessage)
                }
            }
        }



    }
}