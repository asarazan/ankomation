package net.sarazan.ankomation

import android.view.View

class ViewWrapper(val view: View) {
    
    var translationX: Float
        get() = view.translationX
        set(value) { view.translationX = value }

    var translationXFraction: Float
        get() = view.translationX / view.measuredWidth
        set(value) { view.translationX = value * view.measuredWidth }

    var translationY: Float
        get() = view.translationY
        set(value) { view.translationY = value }

    var translationYFraction: Float
        get() = view.translationY / view.measuredHeight
        set(value) { view.translationY = value * view.measuredHeight }
    
    var translationZ: Float
        get() = view.translationZ
        set(value) { view.translationZ = value }

    var scaleX: Float
        get() = view.scaleX
        set(value) { view.scaleX = value }

    var scaleY: Float
        get() = view.scaleY
        set(value) { view.scaleY = value }

    var rotation: Float
        get() = view.rotation
        set(value) { view.rotation = value }

    var alpha: Float
        get() = view.alpha
        set(value) { view.alpha = value }

}