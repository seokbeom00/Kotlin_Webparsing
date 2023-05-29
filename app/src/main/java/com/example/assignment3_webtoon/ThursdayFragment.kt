package com.example.assignment3_webtoon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3_webtoon.databinding.FragmentThursdayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class ThursdayFragment : Fragment(){
    private var _binding: FragmentThursdayBinding? = null
    private val binding get() = _binding!!

    val url = "https://www.toomics.com/webtoon/weekly/dow/4"
    val scope = CoroutineScope(Dispatchers.IO)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DayAdapter
    val data:ArrayList<WebtoonData> = ArrayList()
    private val origin:ArrayList<WebtoonData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThursdayBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun search(query: String){
        data.clear()
        if(query.isEmpty()){
            data.addAll(origin)
        }else{
            for(webtoon in origin){
                if(webtoon.Title.contains(query, ignoreCase = true)){
                    data.add(webtoon)
                }
            }
        }
        adapter.notifyDataSetChanged()
    }

    fun getWebtoonMain(){
        data.clear()
        scope.launch {
            val doc = Jsoup.connect(url).get()
            val week = doc.select(".grid__li")
            for(i in week){
                data.add(WebtoonData(i.select(".toon-dcard__title").text(),
                    i.select(".toon-dcard__thumbnail img").attr("data-original"),
                    i.select(".toon-dcard__subtitle").text(),
                false)
                )            }
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
            origin.clear()
            origin.addAll(data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recycle4
        getWebtoonMain()

        adapter = DayAdapter(data)
        recyclerView.adapter = adapter
        binding.recycle4.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }
}