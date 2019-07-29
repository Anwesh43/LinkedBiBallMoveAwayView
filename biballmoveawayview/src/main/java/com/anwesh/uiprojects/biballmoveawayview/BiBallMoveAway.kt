package com.anwesh.uiprojects.biballmoveawayview

/**
 * Created by anweshmishra on 29/07/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val balls : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#283593")
val backColor : Int = Color.parseColor("#BDBDBD")
val rFactor : Float = 3.3f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawArcAndMovingBall(i : Int, sc : Float, startDeg : Float, deg : Float, size : Float, w : Float, paint : Paint) {
    val r : Float = size / rFactor
    save()
    rotate(deg)
    paint.style = Paint.Style.FILL
    drawCircle(size + w * sc.divideScale(i, balls), 0f, r, paint)
    restore()
    paint.style = Paint.Style.STROKE
    drawArc(RectF(-size, -size, size, size), startDeg, deg, false, paint)
}

fun Canvas.drawBBMANode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    var start : Float = 0f
    var deg : Float = 0f
    for (j in 0..(balls - 1)) {
        val sc1i : Float = sc1.divideScale(j, balls)
        deg = 180f * sc1i
        drawArcAndMovingBall(j, sc2, start, deg, size, (w / 2 - size), paint)
        start = 180F * Math.floor(sc1i.toDouble()).toFloat()
    }
}
