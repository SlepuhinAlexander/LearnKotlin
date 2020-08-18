package ch11.par3

import java.util.Date
import java.time.Period
import java.time.LocalDate

val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this

fun main(args: Array<String>) {
    println(1.days.ago) // 2020-08-17
    println(1.days.fromNow) // 2020-08-19
}
