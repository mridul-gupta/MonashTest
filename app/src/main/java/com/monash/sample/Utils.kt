package com.monash.sample

import com.monash.sample.pojo.*

fun getData(): UserData {
    return UserData(
        "PK", "Heng", 8,
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
                R.color.blue,
                R.color.purple,
                R.color.pink,
                R.color.red,
                R.color.yellow,
                R.color.green,
                R.color.orange,
                645
            ),
            CarParking(
                "Burwood live feed",
                R.color.purple,
                R.color.green,
                R.color.orange,
                R.color.pink,
                R.color.blue,
                R.color.yellow,
                R.color.red,
                265
            ),
            CarParking(
                "Hawthorn live feed",
                R.color.red,
                R.color.purple,
                R.color.green,
                R.color.yellow,
                R.color.pink,
                R.color.blue,
                R.color.orange,
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
