package gdsc.knu

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import gdsc.knu.databinding.ActivityRegisterBinding
import gdsc.knu.model.RestaurantCreateRequest

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 메뉴 추가
        val list = ArrayList<String>()

        binding.addMenu.setOnClickListener {
            list.add(binding.inputMenu.text.toString())

            Log.d("test log", list.toString())

            binding.recyclerView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter=Adapter(list)
            binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

            binding.inputMenu.setText("")
        }

        // 스피너
        var selected_category = "한식"
        val itemList = listOf("한식", "중식", "양식", "일식", "술집", "카페", "기타")
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, itemList)
        binding.inputCategory.adapter = adapter
        binding.inputCategory.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)  {
                selected_category = binding.inputCategory.selectedItem.toString()
            //    binding.temp.setText(selected_category)
            }

            override fun onNothingSelected(parent: AdapterView<*>?){
            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long)  {
                TODO("Not yet implemented")
            }
        }


        //등록 버튼 누르면
        binding.registerBtn.setOnClickListener {
            val request = RestaurantCreateRequest(
                binding.inputName.text.toString(),
                binding.inputExplan.text.toString(),
                binding.inputTel.text.toString(),
                binding.inputLocation.text.toString(),
                list,
                selected_category
            )

            Log.d("regist", request.toString())
        }
    }
}