package vn.com.enterdev.phonenumberapp.activities

import ResultAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import vn.com.enterdev.phonenumberapp.R
import vn.com.enterdev.phonenumberapp.api.ApiService
import vn.com.enterdev.phonenumberapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var listContacts: ArrayList<vn.com.enterdev.phonenumberapp.model.Result>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listContacts = ArrayList<vn.com.enterdev.phonenumberapp.model.Result>()
        getContactsFromApi()

    }

    private fun getContactsFromApi() {
        val api: ApiService = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.IO){
            val response = api.getData().awaitResponse()
            if (response.isSuccessful){
                withContext(Dispatchers.Main){
                    listContacts.clear()
                    for (contact in response.body()!!.results){
                        listContacts.add(contact)
                        Log.e("contact", contact.name.title+" . "+
                        contact.name.last+" "+contact.name.first)
                        binding.listview.isClickable = true
                        binding.listview.adapter = ResultAdapter(this@MainActivity, listContacts)
                        binding.listview.setOnItemClickListener {
                                parent, view, position, id ->
                                val name = listContacts[position].name.last
                                val email = listContacts[position].email
                                val phone = listContacts[position].phone
                                val city = listContacts[position].location.city
                                val img = listContacts[position].picture.thumbnail
                                val intent : Intent = Intent(this@MainActivity, PhoneNumberDetail::class.java)
                                intent.putExtra("name", name)
                                intent.putExtra("email", email)
                                intent.putExtra("phone", phone)
                                intent.putExtra("address", city)
                                intent.putExtra("img", img)
                                startActivity(intent)
                        }
                    }
                }
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "Cannot connect to the API!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}