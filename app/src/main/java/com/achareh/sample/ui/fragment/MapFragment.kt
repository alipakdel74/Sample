package com.achareh.sample.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.achareh.component.ui.fragment.BaseFragment
import com.achareh.data.model.body.BAddress
import com.achareh.sample.R
import com.achareh.sample.databinding.FragmentMapBinding
import com.achareh.sample.viewModel.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.Marker

class MapFragment : BaseFragment<FragmentMapBinding>() {

    private val vm by viewModel<AddressViewModel>()

    private var address: BAddress? = null
    private var lat = 0.0
    private var lng = 0.0

    override fun setLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ) = FragmentMapBinding.inflate(inflater, container, false)

    override fun onInitObject() {
        address = requireArguments().getParcelable("createAddress")
        Configuration.getInstance()
            .load(requireContext(), requireContext()
                .getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE))

        observeCreateAddress()
    }

    override fun onInitView() {
        binding.apply {
            mapView.zoomController.setZoomInEnabled(true)
            mapView.zoomController.setZoomOutEnabled(true)
            mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

            val mapController = mapView.controller
            mapController.setZoom(9.5)
            val startPoint = GeoPoint(35.7717503, 51.3365315)
            mapController.setCenter(startPoint)

            btnConfirm.setOnClickListener {
                val startMarker = Marker(mapView)
                startMarker.position = startPoint
                startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                lat =startMarker.position.latitude
                lng = startMarker.position.longitude
                vm.createAddress(address())
            }
        }
    }

    private fun observeCreateAddress() = vm.createAddress.handleResponse(
        onSuccess = {
            progress.dismiss()
            findNavController().navigate(R.id.addressFragment, null, popUpTo(R.id.nav_main))
        },
        retryErrorConnection = {
            vm.createAddress(address())
        }
    )

    private fun address() = BAddress(
        address = address?.address.orEmpty(),
        last_name = address?.last_name.orEmpty(),
        first_name = address?.first_name.orEmpty(),
        gender = address?.gender.orEmpty(),
        lat = lat,
        lng = lng,
        coordinate_mobile = address?.coordinate_mobile.orEmpty(),
        coordinate_phone_number = address?.coordinate_phone_number.orEmpty()
    )

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
}