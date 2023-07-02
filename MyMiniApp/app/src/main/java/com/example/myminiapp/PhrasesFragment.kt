package com.example.myminiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myminiapp.databinding.FragmentPhrasesBinding


class PhrasesFragment : Fragment() {
    private lateinit var binding: FragmentPhrasesBinding
    private lateinit var recyclerView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhrasesBinding.inflate(layoutInflater,container,false)
        recyclerView = binding.phrasesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val phrases = listOf(Japanese("Hi"," やあ！"," Ya- \n\n A super simple greeting to start off with! やあ is used casually between friends. It’s more of an exclamation to grab attention."),
            Japanese("Hello"," こんにちは"," Konnichiwa \n\n こんにちは is “hello” in Japanese, but it’s not used as often as you would think. こんにちは is somewhat formal so it would sound a bit awkward to say it to a friend."),
            Japanese("My Name is"," 名前は＿＿＿です。","Namae wa ____ desu. \n\n How to say “my name is” in Japanese is quite simple. You could say it two ways: 名前は＿＿＿です。 (Namae wa **** desu.) or ＿＿＿＿です。(____ desu._)"),
            Japanese("Good Morning"," おはようございます"," Ohayou gozaimasu \n\n In the mornings, you can greet people with おはようございます. It’s a formal way to greet someone so you would use it when saying hello to your boss in the morning."),
            Japanese("Good Evening"," こんばんは ","Konbanwa \n\n In the evening, greet others by saying こんばんは. It’s a polite and formal way to say “good evening.”"),
            Japanese("Goodnight"," おやすみなさい"," Oyasumi nasai \n\n This is more a parting phrase than a greeting. But at night and when going to bed, you say おやすみなさい. It literally means: “Go take a rest.”"),
            Japanese("Long Time No See"," お久しぶりですね"," O-hisashiburi desu ne \n\n If you haven’t seen someone for a while (three weeks or longer) you can say お久しぶりですね。This means, “It’s been a while, huh?” or “Long time no see!” You can say this to anyone, from a friend to someone in the workplace."),
            Japanese("Yoohoo"," ヤッホー"," Ya-ho- \n\n This is a very casual greeting, usually between young kids or close friends. It’s a more feminine greeting, but don’t worry boys – your greeting is next."),
            Japanese("Hey"," おす！"," Osu! \n\n おす is a masculine, slang way to greet other guys. Girls don’t typically say this (although I’d be lying if I said it wasn’t my favorite informal greeting. I use it sometimes with friends – often receiving strange looks). And guys wouldn’t say it to girls."),
            Japanese("Yo!"," よー！","Yo-! \n\n Exactly the same as English. You can greet a close friend informally with a simple yo! You wouldn’t say this to anyone older than you, though. This one is also more masculine, but sometimes young girls and women say it, too, to get someone’s attention."),
            Japanese("How are you?"," お元気ですか"," O-genki desu ka \n\n You can greet someone with “How are you” by saying お元気ですか. The “o” here is to show respect, and “ka” is the question particle. You can drop the “o” to make it informal and to reply, you could say genki desu."),
            Japanese("How’s it going?"," 調子はどうですか"," Choushi wa dou desu ka \n\n The actual translation for this one is “What’s your condition?” but it’s used informally to ask “how’s it going” in Japanese."),
            Japanese("I’m Back!"," ただいまー"," Tadaima- \n\n When you come home, you can announce it by proclaiming ただいまー！This means “I’m back!” or “I’ve returned home!” You can use this at work, too, when you’ve left the office for a while and returned."),
            Japanese("Nice to Meet You"," はじめまして"," Hajimemashite \n\n When you meet someone for the first time, you can start off by saying はじめまして. This means “nice to meet you.”"),
            Japanese("Welcome!"," いらっしゃいませ"," Irasshaimase \n\n When you walk into a store in Japan, you’ll often be greeted with いらっしゃいませ！It means “welcome” to store guests."),
            Japanese("Hello [on the Phone]"," もしもし"," Moshi moshi \n\n Answering your phone in Japanese also has its own greeting! When you say “hello” on the phone, you say もしもし with a raised inflection to say “Hello?”"),
            Japanese("Nice day, huh?"," いい天気ですね"," Ii tenki desu ne \n\n Like English, you can also start off by greeting someone with a generic phrase about the weather. Usually, it’ll be something along the lines of いい天気ですね (ii tenki desu ne, “Nice weather, isn’t it?”) or 暑い / 寒い ですね (atsui / samui desu ne, “It’s hot / It’s cold, isn’t it?)")
        )
        recyclerView.adapter = JapaneseAdapter(phrases)

        return binding.root

    }
}