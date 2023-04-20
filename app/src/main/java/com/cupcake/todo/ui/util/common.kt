package com.cupcake.todo.ui.util

import android.app.UiModeManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.fragment.home.HomeItem
import com.cupcake.todo.ui.fragment.home.HomeItemType
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.text.SimpleDateFormat
import java.util.*


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

    // Set the hole color based on the app theme
    val nightMode = isNightMode(context)
    val holeColor = if (nightMode) Color.parseColor("#353535") else Color.parseColor("#FFFFFFFF")
    pieChart.setHoleColor(holeColor)

    pieChart.invalidate()
}
fun isNightMode(context: Context): Boolean {
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
}

fun formatDate(date: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.US)
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.US)
    return inputFormat.parse(date)?.let { outputFormat.format(it) }
}

fun Fragment.navigateWithSendObject(fragment: Fragment, key: String, task: Any? = null) {
    val fragmentManager = requireActivity().supportFragmentManager
    val transaction = fragmentManager.beginTransaction()

    task.let {
        val bundle = Bundle()
        bundle.putParcelable(key, task as Parcelable?)
        fragment.arguments = bundle
    }
    transaction.apply {
        replace(android.R.id.content, fragment)
        addToBackStack(null)
        commit()
    }
}

fun Fragment.navigateTo(fragment: Fragment) {
    val fragmentManager = requireActivity().supportFragmentManager
    val transaction = fragmentManager.beginTransaction()

    transaction.apply {
        replace(android.R.id.content, fragment)
        addToBackStack(null)
        commit()
    }
}
