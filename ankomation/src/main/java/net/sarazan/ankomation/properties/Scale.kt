package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.util.Property
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet
import net.sarazan.ankomation.ViewWrapper

class Scale : Ankomation {
    
    companion object {
        val PROP_X = object : Property<ViewWrapper, Float>(Float::class.java, "scaleX") {
            override fun get(`object`: ViewWrapper): Float { return `object`.scaleX }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.scaleX = value }
        }
        val PROP_Y = object : Property<ViewWrapper, Float>(Float::class.java, "scaleY") {
            override fun get(`object`: ViewWrapper): Float { return `object`.scaleY }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.scaleY = value }
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

            xFrom?.let { view.scaleX = it }
            yFrom?.let { view.scaleY = it }

            val x = xTo?.let { PropertyValuesHolder.ofFloat(PROP_X, it) }
            val y = yTo?.let { PropertyValuesHolder.ofFloat(PROP_Y, it) }
            val props = arrayOf(x, y).filterNotNull().toTypedArray()

            animate(pass).apply {
                setValues(*props)
                start()
            }
        }
        return true
    }
}