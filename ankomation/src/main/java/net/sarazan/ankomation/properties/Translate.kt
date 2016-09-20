package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.util.Property
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet
import net.sarazan.ankomation.ViewWrapper

class Translate : Ankomation {

    companion object {
        val PROP_X = object :Property<ViewWrapper, Float>(Float::class.java, "translationX") {
            override fun get(`object`: ViewWrapper): Float { return `object`.translationX }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.translationX = value }
        }
        val PROP_Y = object : Property<ViewWrapper, Float>(Float::class.java, "translationY") {
            override fun get(`object`: ViewWrapper): Float { return `object`.translationY }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.translationY = value }
        }
        val PROP_Z = object : Property<ViewWrapper, Float>(Float::class.java, "translationZ") {
            override fun get(`object`: ViewWrapper): Float { return `object`.translationZ }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.translationZ = value }
        }
    }

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var xFrom: Float? = null
    var xTo: Float? = null
    var yFrom: Float? = null
    var yTo: Float? = null
    var zFrom: Float? = null
    var zTo: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        view?.let {
            view ->

            val xArgs = listOf(xFrom, xTo ?: xFrom).filterNotNull().toFloatArray()
            val propX = if (xArgs.isNotEmpty()) PropertyValuesHolder.ofFloat(PROP_X, *xArgs) else null
            val yArgs = listOf(yFrom, yTo ?: yFrom).filterNotNull().toFloatArray()
            val propY = if (yArgs.isNotEmpty()) PropertyValuesHolder.ofFloat(PROP_Y, *yArgs) else null
            val zArgs = listOf(zFrom, zTo ?: zFrom).filterNotNull().toFloatArray()
            val propZ = if (zArgs.isNotEmpty()) PropertyValuesHolder.ofFloat(PROP_Z, *yArgs) else null

            val props = arrayOf(propX, propY, propZ).filterNotNull().toTypedArray()

            animate(pass).apply {
                setValues(*props)
                runAnimator(this)
            }
        }
        return true
    }
}