package com.cupcake.todo.ui.util

import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.fragment.home.HomeItem
import com.cupcake.todo.ui.fragment.home.HomeItemType
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


fun TeamTask.toTeamTask(): HomeItem<Any> {
    return HomeItem(this, HomeItemType.ITEM_TYPE_TEAM_TASK)
}

fun PersonalTask.toPersonalTask(): HomeItem<Any> {
    return HomeItem(this, HomeItemType.ITEM_TYPE_PERSONAL_TASK)
}

fun PieChart.setupPieChart(pieChartView: PieChart, dataValue: MutableList<PieEntry>){
    val chartColors = listOf(0xFF81B2F3.toInt(), 0xFF269AB1.toInt(), 0xFFB2CFDE.toInt())

    val pieChart: PieChart = pieChartView
    val pieDataSet = PieDataSet(dataValue, "")
    pieDataSet.colors = chartColors
    pieDataSet.setDrawValues(false)

    val pieData = PieData(pieDataSet)

    pieChart.data = pieData
    pieChart.animateX(1400)
    pieChart.setDrawEntryLabels(false)
    pieChart.description.isEnabled = false
    pieChart.legend.isEnabled = false
    pieChart.holeRadius = 60f

    pieChart.invalidate()
}