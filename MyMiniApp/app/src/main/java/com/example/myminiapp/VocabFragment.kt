package com.example.myminiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myminiapp.databinding.FragmentVocabBinding

class VocabFragment : Fragment() {
    private lateinit var binding:FragmentVocabBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVocabBinding.inflate(layoutInflater,container,false)
        recyclerView = binding.vocabRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val vocab = listOf(Japanese("About (this thing)","こと"," Koto "),
            Japanese("After","後","Ato "),
            Japanese("Bathroom","トイレ or お手洗い","Toire or Otearai"),
            Japanese("Before","前","Mae"),
            Japanese("Car","車"," Kuruma"),
            Japanese("College","大学"," Daigaku"),
            Japanese("Country","国","Kuni or Koku "),
            Japanese("Day and Sun","日"," Nichi or Hi "),
            Japanese("Family","家族","Kazoku "),
            Japanese("For or In regards to","ため"," Tame "),
            Japanese("Home or House","家","Uchi or Ie"),
            Japanese("Hometown","出身","Shusshin "),
            Japanese("Hour or Time","時","Ji or Toki"),
            Japanese("Japan","日本","Nihon "),
            Japanese("Line","線","Sen"),
            Japanese("Middle, Inside or During","中"," Naka or Chuu "),
            Japanese("Minute","分","Fun or Bun"),
            Japanese("Month and Moon","月","Getsu or Tsuki "),
            Japanese("Movie","映画"," Eiga"),
            Japanese("Name","名前"," Namae"),
            Japanese("Now","今","Ima "),
            Japanese("Other","他の","Hoka no"),
            Japanese("Outside","外"," Soto or Gai "),
            Japanese("Person","人","Hito or Nin "),
            Japanese("Place","所 or 場所"," Tokoro or Basho "),
            Japanese("Room","部屋","Heya"),
            Japanese("School","学校"," Gakkou "),
            Japanese("Shop","店","Mise or Ya "),
            Japanese("Station (Train)","駅","Eki "),
            Japanese("Subway","地下鉄"," Chikatetsu "),
            Japanese("Thing","物","Mono "),
            Japanese("Time (As in a time frame.)","時間","Jikan "),
            Japanese("Today","今日"," Kyou "),
            Japanese("Tomorrow","明日"," Ashita"),
            Japanese("Town","町","Machi "),
            Japanese("Train","電車","Densha"),
            Japanese("TV","テレビ","Terebi"),
            Japanese("Water","水"," Mizu "),
            Japanese("Week","週","Shuu "),
            Japanese("Year","年"," Toshi or Nen "),
            Japanese("Yesterday","昨日"," Kinou"))
        recyclerView.adapter = JapaneseAdapter(vocab)

        return binding.root
    }


}