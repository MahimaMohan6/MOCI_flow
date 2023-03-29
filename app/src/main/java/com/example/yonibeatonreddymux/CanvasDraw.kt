package com.example.yonibeatonreddymux

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class CanvasDraw(context: Context,attributeSet: AttributeSet):View(context,attributeSet){
    val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    val path=Path()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }


    private fun drawCanvas(canvas: Canvas?) {
        paint.color = Color.parseColor("#E3E3E3")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4.0f
        val toPx = resources.displayMetrics.density
        val width=(resources.displayMetrics.widthPixels/toPx)
        val height=(resources.displayMetrics.heightPixels/toPx)
        println("width $width")
        println("height $height")
        val b = BitmapFactory.decodeResource(resources, R.drawable.img_success)
        val bitmap = Bitmap.createScaledBitmap(b, 280, 320, true)
        path.moveTo(width/6,height/4)
        path.lineTo(width/6,height-100)
        path.quadTo(width/3,height-70,width/6,height-40)
        path.lineTo(width/6,height+300)
        path.lineTo(width*2+100,height+300)
        path.lineTo(width*2+100,height-40)
        path.quadTo(width*2+30,height-70,width*2+100,height-100)
        path.lineTo(width*2+100,height/4)
        path.quadTo(width*2+100,height/4-50,width*2+40,height/4-50)
        path.lineTo(width/6+40,height/4-50)
        path.quadTo(width/6,height/4-50,width/6,height/4)
       /* path.moveTo(50f * toPx, 70f * toPx)
        path.lineTo(50f * toPx, 250f * toPx)
        path.quadTo(80f * toPx, 260f * toPx, 50f * toPx, 270f * toPx)
        path.lineTo(50f * toPx, 460f * toPx)
        path.quadTo(50f * toPx, 490f * toPx, 80f * toPx, 490f * toPx)
        path.lineTo(330f * toPx, 490f * toPx)
        path.quadTo(360f * toPx, 490f * toPx, 360f * toPx, 460f * toPx)
        path.lineTo(360f * toPx, 270f * toPx)
       path.quadTo(330f * toPx, 260f * toPx, 360f * toPx, 250f * toPx)
        path.lineTo(360f * toPx, 70f * toPx)
        path.quadTo(360f * toPx, 40f * toPx, 330f * toPx, 40f * toPx)
        path.lineTo(80f * toPx, 40f * toPx)
        path.quadTo(50f * toPx, 40f * toPx, 50f * toPx, 70f * toPx)*/
        canvas!!.drawPath(path, paint)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.textSize = 28f * toPx
        paint.isFakeBoldText = true
        paint.textAlign=Paint.Align.CENTER
        if(lang=="English"){
            canvas.drawText("Success",170f*toPx,230f*toPx,paint)
        }else if(lang=="Arabic"){
            canvas.drawText("نجاح",170f*toPx,230f*toPx,paint)
        }

        paint.textSize=14f*toPx
        paint.color=Color.parseColor("#343434")
        if(lang=="English") {
            canvas.drawText("Your request has been", 170f * toPx, 260f * toPx, paint)
            canvas.drawText("submitted successfully. We", 168f * toPx, 278f * toPx, paint)
            canvas.drawText("will update you with further", 168f * toPx, 296f * toPx, paint)
            canvas.drawText("information.", 174f * toPx, 314f * toPx, paint)
        }else if(lang=="Arabic"){
            canvas.drawText("لقد كان طلبك", 170f * toPx, 260f * toPx, paint)
            canvas.drawText("تم الإرسال بنجاح. نحن", 168f * toPx, 278f * toPx, paint)
            canvas.drawText("سوف تقوم بتحديثك مع المزيد", 168f * toPx, 296f * toPx, paint)
            canvas.drawText("معلومة.", 174f * toPx, 314f * toPx, paint)
        }
        canvas.drawBitmap(bitmap, 120f * toPx, 100f * toPx, paint)
        paint.color=Color.parseColor("#6DCE42")
        paint.style=Paint.Style.FILL
        paint.strokeWidth=10f
        canvas.drawLine(110f*toPx,90f*toPx,114f*toPx,94f*toPx,paint)
        canvas.drawLine(90f*toPx,140f*toPx,108f*toPx,136f*toPx,paint)
        canvas.drawLine(216f*toPx,168f*toPx,222f*toPx,174f*toPx,paint)
        paint.color=Color.parseColor("#FFA2A2")
        canvas.drawLine(270f*toPx,148f*toPx,274f*toPx,156f*toPx,paint)
        canvas.drawLine(120f*toPx,180f*toPx,126f*toPx,188f*toPx,paint)
        canvas.drawLine(150f*toPx,100f*toPx,156f*toPx,104f*toPx,paint)
        canvas.drawLine(240f*toPx,134f*toPx,246f*toPx,126f*toPx,paint)

        //path.close()
    }
}