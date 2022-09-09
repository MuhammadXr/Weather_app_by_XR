package uz.gita.weatherappbyxr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import im.delight.android.location.SimpleLocation
import uz.gita.weatherappbyxr.R
import uz.gita.weatherappbyxr.presenter.LocationsViewModel
import uz.gita.weatherappbyxr.repository.LocationRep
import uz.gita.weatherappbyxr.ui.adapters.LocAdapder


@AndroidEntryPoint
class Locations : Fragment(R.layout.fragment_locations) {

    lateinit var searchView: SearchView
    lateinit var btnMyLocation: ImageView
    lateinit var list_locations: RecyclerView
    val adapder: LocAdapder by lazy { LocAdapder() }

    private val viewModel: LocationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.locationLiveData.observe(this){
            val bundle = Bundle().apply {
                putDouble("lat", it.lat)
                putDouble("lon", it.lon)
            }
            findNavController().navigate(R.id.action_locations_to_main, bundle)
        }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.apply {
            searchView = findViewById(R.id.search_view)
            btnMyLocation = findViewById(R.id.my_location)
            list_locations = findViewById(R.id.list_locations)

            list_locations.adapter = adapder
        }

        viewModel.listLocations.observe(viewLifecycleOwner){
            adapder.submitList(it)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                viewModel.getLocations(query.toString())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        btnMyLocation.setOnClickListener{
            if(!LocationRep.getLocation()!!.hasLocationEnabled()){
                SimpleLocation.openSettings(requireContext())
            }
            else
                viewModel.getCurrentLocation()
        }

        adapder.setOnClickListener {
            viewModel.setLocationData(it)
        }
    }
}