package net.sarazan.ankomation

import android.view.View
import android.view.animation.Interpolator

/**
 * Created by Aaron Sarazan on 9/17/16
 */

fun ankomate(fn: AnkomationSet.() -> Unit) {
    AnkomationSetImpl().apply(fn).apply {
        prepare()
        execute()
    }
}

interface Ankomation {
    fun prepare() {}
    fun execute() {}
}

interface ViewAnkomation : Ankomation {
    val view: View
}

interface TimeAnkomation : Ankomation {
    var duration: Long?
    var interpolator: Interpolator?
}

interface AnkomationSet: TimeAnkomation {

    fun View.scale(fn: Scale.() -> Unit)
    fun View.alpha(fn: Alpha.() -> Unit)
    fun View.rotate(fn: Rotate.() -> Unit)

    infix fun then(fn: AnkomationSet.() -> Unit)

    fun View.show()
    fun View.hide()
    fun View.disappear()

}

//fun test() {
//    val view1: View? = null
//    view1!!
//    val view2: View? = null
//    view2!!
//
//
//    ankomate {
//
//        duration = 200L
//        interpolator = AccelerateDecelerateInterpolator()
//
//        view1.scale {
//            xFrom = 0.1f
//            xTo = 1f
//        }
//
//        view1.alpha {
//            from = 0f
//            to = 1f
//        }
//
//        view2.rotate {
//            from = 270
//            to = 180
//        }
//
//        then {
//
//            duration = 400L
//            interpolator = AccelerateInterpolator()
//
//            view1.rotate {
//                from = 180
//                to = 360
//            }
//
//            then {
//
//                view1.disappear()
//
//            }
//
//        }
//    }
//}