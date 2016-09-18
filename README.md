# ankomation
anko for anims

Here's how I hope it looks eventually:
```kotlin
ankomate {

  duration = 200L
  interpolator = AccelerateDecelerateInterpolator()

  view1.scale {
    xFrom = 0.1f
    xTo = 1f
  }

  view1.alpha {
    from = 0f
    to = 1f
  }

  view2.rotate {
    from = 270
    to = 180
  }

  then {

    duration = 400L
    interpolator = AccelerateInterpolator()

    view1.rotate {
      from = 180
      to = 360
    }

    then {
      view1.disappear()
    }
  }
}
```
