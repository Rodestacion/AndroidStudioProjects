package com.data.canvassample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.res.ResourcesCompat

class MyCanvas(context: Context): View(context) {
    private lateinit var extraCanvas:Canvas
    private lateinit var extraBitmap: Bitmap
    private val backgroundColor = ResourcesCompat.getColor(resources, com.google.android.material.R.color.material_blue_grey_800,null)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if(::extraBitmap.isInitialized)extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap,0f,0f,null)
        canvas.drawRect(100f,100f,300f,400f,paint)
        canvas.drawCircle(canvas.width.toFloat()/2,500F,300.0F,paint)
        canvas.drawLine(100F,100F,799F,1000F,paint)
        canvas.drawText("Hello",400F,1000F,paint)

    }

    private val paint = Paint().apply {
        color = ResourcesCompat.getColor(resources,androidx.appcompat.R.color.abc_decor_view_status_guard_light,null)
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeWidth = 20F
        textSize = 130F
    }
}