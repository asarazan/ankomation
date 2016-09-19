package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.os.Build
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet

class Translate : Ankomation {

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var xFrom: Float? = null
    var xFromPercent: Float? = null
    var xTo: Float? = null
    var xToPercent: Float? = null
    var yFrom: Float? = null
    var yFromPercent: Float? = null
    var yTo: Float? = null
    var yToPercent: Float? = null
    var zFrom: Float? = null
    var zTo: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        view?.let {
            view ->

            xFrom?.let { view.translationX = it }
            xFromPercent?.let { view.translationX = it * view.measuredWidth }
            yFrom?.let { view.translationY = it }
            yFromPercent?.let { view.translationY = it * view.measuredHeight }
            if(Build.VERSION.SDK_INT >= 21) { zFrom?.let { view.translationZ = it } }
            
            val x1 = xTo ?: (xToPercent?.times(view.measuredWidth))
            val propX = x1?.let { PropertyValuesHolder.ofFloat("translationX", it) }
            val y1 = yTo ?: (yToPercent?.times(view.measuredHeight))
            val propY = y1?.let { PropertyValuesHolder.ofFloat("translationY", it) }
            val z1 = zTo
            val propZ = if(Build.VERSION.SDK_INT >= 21) z1?.let { PropertyValuesHolder.ofFloat("translationZ", it) } else null
            
            val props = arrayOf(propX, propY, propZ).filterNotNull().toTypedArray()

            animate(pass).apply {
                setValues(*props)
                start()
            }
        }
        return true
    }
}