package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.util.Property
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet
import net.sarazan.ankomation.ViewWrapper

class TranslateFraction : Ankomation {

    companion object {
        val PROP_X = object : Property<ViewWrapper, Float>(Float::class.java, "translationXFraction") {
            override fun get(`object`: ViewWrapper): Float { return `object`.translationXFraction }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.translationXFraction = value }
        }
        val PROP_Y = object : Property<ViewWrapper, Float>(Float::class.java, "translationYFraction") {
            override fun get(`object`: ViewWrapper): Float { return `object`.translationYFraction }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.translationYFraction = value }
        }
    }

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var xFrom: Float? = null
    var xTo: Float? = null
    var yFrom: Float? = null
    var yTo: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        view?.let {
            view ->

            val xArgs = listOf(xFrom, xTo ?: xFrom).filterNotNull().toFloatArray()
            val propX = if (xArgs.isNotEmpty()) PropertyValuesHolder.ofFloat(PROP_X, *xArgs) else null
            val yArgs = listOf(yFrom, yTo ?: yFrom).filterNotNull().toFloatArray()
            val propY = if (yArgs.isNotEmpty()) PropertyValuesHolder.ofFloat(PROP_Y, *yArgs) else null

            val props = arrayOf(propX, propY).filterNotNull().toTypedArray()

            animate(pass).apply {
                setValues(*props)
                runAnimator(this)
            }
        }
        return true
    }
}