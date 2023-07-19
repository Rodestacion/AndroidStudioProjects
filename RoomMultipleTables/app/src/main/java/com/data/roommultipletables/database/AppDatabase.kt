package com.data.roommultipletables.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.data.roommultipletables.dao.CustomersDao
import com.data.roommultipletables.dao.ProductsDao
import com.data.roommultipletables.model.Customers
import com.data.roommultipletables.model.Products

@Database(
    entities = [Products::class, Customers::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getProducts():ProductsDao
    abstract fun getCustomers():CustomersDao

    companion object{
        private var instance:AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "management_db"
            ).build()
    }



}