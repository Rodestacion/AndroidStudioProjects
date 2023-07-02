package com.data.apexercise11

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "product.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE products(
                    product_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_name TEXT,
                    product_description TEXT                
                )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS product")
        onCreate(db)
    }

    //Create
    fun insertNewProduct(product: Product){
        val db = writableDatabase
        val sql = "INSERT INTO products (product_name, product_description) VALUES (?,?)"
        val args = arrayOf(product.name,product.description)
        db.execSQL(sql,args)

    }

    //READ
    fun getAllProducts():MutableList<Product>{
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM products",null)

        val productList = mutableListOf<Product>()

        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val name =  cursor.getString(1)
            val description = cursor.getString(2)

            var newProduct = Product(id,name,description)
            productList.add(newProduct)
        }

        cursor.close()

        return productList
    }


}