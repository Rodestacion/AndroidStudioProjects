package com.example.recyclerexample2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerexample2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.myRecyclerView
        recyclerView.layoutManager =LinearLayoutManager(this)

        val items = listOf(
            Item("Motherboard","The motherboard is the main circuit board inside your computer. It is the “common ground” that allows all of the other components to communicate with one another"),
            Item("CPU","The CPU is the central processing unit of the computer. It is considered the “brain” of the computer and is the part that processes all of the instructions."),
            Item("RAM","RAM stands for random access memory. It is hardware that is found in many different electronic devices, including a computer."),
            Item("Storage (Mechanical Hard Disk or SSD)","The storage device, or hard disk, is the hardware that stores data which is not erased when you power-off the computer."),
            Item("Power Supply","The power supply is the component that supplies power to the whole Desktop computer system. It plugs in and receives power from an electrical outlet. It then converts the current from an alternating current (AC) to a direct current (DC)."),
            Item("Computer Monitor","The computer monitor is also called the screen or display. It shows the user the output and allows the user to see the information being processed on the computer."),
            Item("Keyboard","The keyboard is the main “input” device (together with the mouse) of any computer."),
            Item("Mouse","The mouse is another important input device. It is named because the shape of the mouse resembles the shape of the animal, and the cord represents the tail."),
            Item("Optical Drive (DVD/CD ROM)","The optical drive uses a laser that writes and reads data on optical discs, such as a DVD or CD."),
            Item("Network Interface Card or WiFi Card","The network interface card is also called the NIC (pronounced Nick). This part is what gives a computer its networking capabilities."),
            Item("Desktop Case","The desktop case is the mount that houses all of the components of the computer, including the motherboard, the hard drive, the optical drive, and more."),
            Item("GPU (Video Card)","GPU stands for graphics processing unit. It is a processor that handles graphics operations, including 2D and 3D calculations, and it excels at 3D graphics.")
        )

        recyclerView.adapter = ItemAdapter(items)


    }
}