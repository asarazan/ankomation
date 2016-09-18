package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet

class Scale : Ankomation {

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

            val x = xTo?.let { PropertyValuesHolder.ofFloat("scaleX", it) }
            val y = yTo?.let { PropertyValuesHolder.ofFloat("scaleY", it) }
            val props = arrayOf(x, y).filterNotNull().toTypedArray()

            animate(pass).apply {
                setValues(*props)
                start()
            }
        }
        return true
    }
}