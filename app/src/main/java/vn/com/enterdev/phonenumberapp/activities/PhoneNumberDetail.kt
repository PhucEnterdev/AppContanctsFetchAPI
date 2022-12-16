package vn.com.enterdev.phonenumberapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import vn.com.enterdev.phonenumberapp.R

class PhoneNumberDetail : AppCompatActivity() {
    private lateinit var imgAvatar: CircleImageView
    private lateinit var tvEmail: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_detail)

        val name = intent.getStringExtra("name");
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")
        val img = intent.getStringExtra("img")

        initUI()

        tvEmail.text = email
        tvAddress.text = address
        tvPhone.text = phone
        tvName.text = name

        Glide.with(this).load(img).into(imgAvatar)

    }

    private fun initUI() {
         imgAvatar = findViewById(R.id.circle_image_view)
         tvEmail = findViewById(R.id.tv_email)
         tvAddress  = findViewById(R.id.tv_address)
         tvPhone = findViewById(R.id.tv_phone)
         tvName = findViewById(R.id.tv_full_name)
    }
}