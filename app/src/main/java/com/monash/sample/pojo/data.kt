package com.monash.sample.pojo

data class Lecture(
    var startTime: String,
    var endTime: String,
    var name: String,
    var professor: String,
    var location: String
)

data class Lectures(
    var lectures: List<Lecture>
) : Comparable<Lectures> {
    override fun compareTo(other: Lectures): Int {
        return 0
    }
}


data class CarParking(
    var name: String,
    var count: Int
)

data class CarParkings(
    var carParkings: List<CarParking>
) : Comparable<CarParkings> {
    override fun compareTo(other: CarParkings): Int {
        return 0
    }
}

data class Shuttle(
    var fromStation: String,
    var toStation: String,
    var minutes: Int
)

data class Shuttles(
    var shuttles: List<Shuttle>
) : Comparable<Shuttles> {
    override fun compareTo(other: Shuttles): Int {
        return 0
    }
}


data class UserData(
    var firstName: String,
    var lastName: String,
    var weekNum: Int,
    var lectures: Lectures,
    var carParkings: CarParkings,
    var shuttles: Shuttles
)

