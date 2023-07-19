package com.data.roommultipletables.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.data.roommultipletables.model.Products


@Dao
interface ProductsDao {
    //Create
    @Insert
    fun addProduct(products: Products)

    //Read
    @Query("SELECT * FROM products")
    fun getAllProducts():List<Products>

    //Update
    @Update
    fun updateProduct(products: Products)

    //Delete
    @Delete
    fun deleteProduct(products: Products)
}
