package net.sarazan.ankomation

import android.view.View

/**
 * Created by Aaron Sarazan on 9/27/16
 */

fun AnkomationSet.visibleFade(v: View) {
    visible(v)
    alpha(v) {
        if (v.visibility != View.VISIBLE) {
            from = 0f
        }
        to = 1f
    }
}

fun AnkomationSet.invisibleFade(v: View) {
    and {
        alpha(v) {
            to = 0f
        }
        then { invisible(v) }
    }
}

fun AnkomationSet.goneFade(v: View) {
    and {
        alpha(v) {
            to = 0f
        }
        then { gone(v) }
    }
}