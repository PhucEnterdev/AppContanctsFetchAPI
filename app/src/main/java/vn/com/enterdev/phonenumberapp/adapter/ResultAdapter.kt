import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import vn.com.enterdev.phonenumberapp.R

class ResultAdapter(private val context : Activity, private var arrayList: ArrayList<vn.com.enterdev.phonenumberapp.model.Result>):
    ArrayAdapter<vn.com.enterdev.phonenumberapp.model.Result>(context, R.layout.item_contact, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_contact, null)
        val imgAvatar: ImageView = view.findViewById(R.id.circle_image_view_item)
        val tvFullName: TextView = view.findViewById(R.id.tv_full_name_item)
        val tvPhone: TextView = view.findViewById(R.id.tv_full_name_item)
        val tvEmail: TextView = view.findViewById(R.id.tv_email_item)
        val tvAddress: TextView = view.findViewById(R.id.tv_address_item)

        tvFullName.text = arrayList[position].name.title+" . "+arrayList[position].name.last+" "+arrayList[position].name.first
        tvPhone.text = arrayList[position].phone
        tvEmail.text = arrayList[position].email
        tvAddress.text = arrayList[position].location.city

        Glide.with(context).load(arrayList[position].picture.thumbnail).into(imgAvatar)
        return view
    }
}