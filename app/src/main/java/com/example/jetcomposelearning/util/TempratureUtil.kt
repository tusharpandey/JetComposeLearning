package com.example.jetcomposelearning.util

enum class TemperatureType{
    DEGREE,
    FAHRENHEIT,
}

private fun convertTo(temperature : Float, type :  TemperatureType) : Float{
    when (type) {
        TemperatureType.DEGREE -> {
            return (temperature * 9 / 5) + 32
        }
        TemperatureType.FAHRENHEIT -> {
            return  (temperature - 32) * 5/9
        }
    }
}

fun convertToDegree(temperature : Float) : Float{
    return convertTo(temperature,TemperatureType.DEGREE)
}

fun convertToFahrenheit(temperature : Float) : Float{
    return convertTo(temperature,TemperatureType.FAHRENHEIT)
}