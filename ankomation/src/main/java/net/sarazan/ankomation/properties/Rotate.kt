package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.util.Property
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet
import net.sarazan.ankomation.ViewWrapper

class Rotate : Ankomation {

    companion object {
        val PROP = object : Property<ViewWrapper, Float>(Float::class.java, "rotation") {
            override fun get(`object`: ViewWrapper): Float { return `object`.rotation }
            override fun set(`object`: ViewWrapper, value: Float) { `object`.rotation = value }
        }
    }

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var from: Float? = null
    var to: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        from?.let { view!!.rotation = it }
        to?.let {
            animate(pass).apply {
                setValues(PropertyValuesHolder.ofFloat(PROP, it))
                runAnimator(this)
            }
        }
        return true
    }
}