package vn.mobile.expersystem.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import vn.mobile.expersystem.R


class InformationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_information, container, false)
        val image = view.findViewById<ShapeableImageView>(R.id.imgae_avt)
        Picasso.get().load("https://scontent.fhan17-1.fna.fbcdn.net/v/t39.30808-6/465013326_1729204774543418_1704716528547078173_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeFTIjFHGDpsfvXMJII34HSCSvJpar1FYQpK8mlqvUVhCoF1Rb2lnVHxGELaJOSd1WoSI7ENBrW0q0OisUJ-kbfj&_nc_ohc=E13-RfME1DkQ7kNvgFQ-MgT&_nc_zt=23&_nc_ht=scontent.fhan17-1.fna&_nc_gid=ANH6M70nE5QeYUbfDNwV9qw&oh=00_AYDgvAhMcFFGn1UL7GjtQyjYo_4r4Ynd2ynknuEXRav2BA&oe=6730B363").into(image)
        return view
    }
}