package com.example.myminiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myminiapp.databinding.FragmentNumbersBinding


class NumbersFragment : Fragment() {
    private lateinit var binding: FragmentNumbersBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumbersBinding.inflate(layoutInflater,container,false)
        recyclerView = binding.numberRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val numbers = listOf(Japanese("One (1)","いち","ichi"),
            Japanese("Two (2)","に","ni"),
            Japanese("Three (3)","さん","san"),
            Japanese("Four (4)","よん or し","yon or shi"),
            Japanese("Five (5)","ご","go"),
            Japanese("Six (6)","ろく","roku"),
            Japanese("Seven (7)","なな or しち","nana or shichi"),
            Japanese("Eight (8)","はち","hachi"),
            Japanese("Nine (9)","く or きゅう","ku or kyuu"),
            Japanese("Ten (10)","じゅう","juu"),
            Japanese("One Hundred (100)","ひゃ","hyaku"),
            Japanese("One Thousand (1,000)","せん","sen"),
            Japanese("Ten Thousand (10,000)","まん","man"),
            Japanese("One Hundred Thousand\n(100,000)","じゅうまん","juuman"),
            Japanese("One Million\n(1,000,000)","ひゃくまん","juu"),
            Japanese("Ten Million\n(10,000,000)","せんまん","senman"),
            Japanese("One Hundred Million\n(100,000,000)","いちおく ","ichioku"),
            Japanese("One Billion\n(1,000,000,000)","じゅうおく","juuoku"),
            Japanese("One Trillion\n(1,000,000,000,000)","いっちょう","icchou"))
        recyclerView.adapter = JapaneseAdapter(numbers)

        return binding.root
    }
}