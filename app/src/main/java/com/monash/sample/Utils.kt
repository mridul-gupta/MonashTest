package com.monash.sample

import com.monash.sample.pojo.*


const val BASE_URL = "https://us-central1-monashtest-cf1d4.cloudfunctions.net/"

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}


fun getRandomColor(): Int {
    val colors = intArrayOf(
        R.color.blue,
        R.color.purple,
        R.color.pink,
        R.color.red,
        R.color.yellow,
        R.color.green,
        R.color.orange
    )
    return colors[(colors.indices).random()]
}


fun getData(): UserData {
    return UserData(
        "Mridul", "Gupta", 8,
        Lectures(listOf(
            Lecture(
                "08:00",
                "10:00",
                "FIT1031 Lecture 01",
                "Arun Kongaurthu",
                "S4, 13 College Walk, Clayton"
            ),
            Lecture(
                "01:00",
                "03:00",
                "FIT1075 Tutorial 11",
                "Jorrod Knibbe",
                "S3, 13 College Walk, Clayton"
            ),
            Lecture(
                "03:00",
                "05:00",
                "FIT1078 Laboratory 08",
                "Akshay Sapra",
                "144, 13 College Walk, Clayton"
            )
        )),
        CarParkings(listOf(
            CarParking(
                "Clayton live feed",
                645
            ),
            CarParking(
                "Burwood live feed",
                265
            ),
            CarParking(
                "Hawthorn live feed",
                37
            )
        )),
        Shuttles(listOf(
            Shuttle("Clayton", "Caufield", 4),
            Shuttle("Clayton", "Peninsula", 8),
            Shuttle("Clayton", "Hawthorn", 10)
        ))
    )
}
