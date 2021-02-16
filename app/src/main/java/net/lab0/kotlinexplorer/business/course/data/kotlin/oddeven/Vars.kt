package net.lab0.kotlinexplorer.business.course.data.kotlin.oddeven

import java.util.*

val kotlinCreation = GregorianCalendar(2011, Calendar.JULY, 22)
val kotlinV1 = GregorianCalendar(2016, Calendar.FEBRUARY, 15)
val today = GregorianCalendar().get(GregorianCalendar.YEAR)

val kotlinsAge = today - kotlinCreation.get(GregorianCalendar.YEAR)
val kotlinsV1Age = today - kotlinV1.get(GregorianCalendar.YEAR)
