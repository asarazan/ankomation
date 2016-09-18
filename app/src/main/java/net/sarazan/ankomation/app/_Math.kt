package net.sarazan.ankomation.app

/**
 * Created by Aaron Sarazan on 9/18/16
 */

fun Float.isAbout(other: Float, threshold: Float = 0.05f): Boolean {
    val diff = Math.abs(this - other)
    val max = Math.max(Math.abs(this), Math.abs(other))
    return diff <= max * threshold
}