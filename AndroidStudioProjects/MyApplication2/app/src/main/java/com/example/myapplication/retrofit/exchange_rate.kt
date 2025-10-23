package com.example.myapplication.retrofit

import android.graphics.Point
import android.graphics.PointF
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import kotlin.math.roundToInt


@Composable
fun LineChartScreen() {




   Column(modifier = Modifier.fillMaxWidth()
       ,
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {
       Column(modifier = Modifier.fillMaxWidth()
           .padding(horizontal = 0.dp)) {

           val steps = 4
           val days = listOf("jan", "Feb", "Mar","Apr","May")

// 1️⃣ Float veri noktaları
           val pointsData = listOf(
               co.yml.charts.common.model.Point(0f, 100f),
               co.yml.charts.common.model.Point(1f, 450f),
               co.yml.charts.common.model.Point(2f, 300f),
               co.yml.charts.common.model.Point(3f, 400f),

               

           )

// 2️⃣ X ekseni
           val xAxisData = AxisData.Builder()
               .axisStepSize(75.dp)
               .backgroundColor(Color.Transparent)
               .steps(pointsData.size )
               .labelData { i -> days.get(i) }
               .axisLineColor(Color.Transparent)
               .startDrawPadding(10.dp)
               .labelAndAxisLinePadding(20.dp)

               .build()

// 3️⃣ Y ekseni
           val yAxisData = AxisData.Builder()
               .steps(steps-1)
               .backgroundColor(Color(0xffFFFFFF))
               .labelAndAxisLinePadding(25.dp)
               .axisLineColor(Color.Transparent)
               .labelData { i ->
                   val yScale = 100f / steps
                   val value = i * yScale
                   ((value * 20).roundToInt() ).toString()
               }
               .build()

// 4️⃣ LineChartData oluştur
           val lineChartData = LineChartData(
               linePlotData = LinePlotData(
                   lines = listOf(
                       Line(
                           dataPoints = pointsData,
                           lineStyle = LineStyle(
                               color = Color(0xffA4ADBD),


                           ),
                           intersectionPoint = IntersectionPoint(
                               color = Color(0xff9BA2EA),

                           ),
                           selectionHighlightPoint = SelectionHighlightPoint(
                               radius = 5.dp,
                               color = Color.White,

                           ),
                           shadowUnderLine = ShadowUnderLine(
                               color = Color.Transparent
                           ),
                           selectionHighlightPopUp = SelectionHighlightPopUp(


                           )
                       )
                   )
               ),
               xAxisData = xAxisData,
               yAxisData = yAxisData,
               gridLines = GridLines(enableVerticalLines = false,
                   lineWidth = 1.dp,
                   pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
                   ),
               backgroundColor = Color(0xffFFFFFF)
           )
           LineChart(
               modifier = Modifier
                   .fillMaxWidth()
                   .height(200.dp)


               ,
               lineChartData = lineChartData,

           )

       }

   }


}

@Composable
@Preview(showSystemUi = true)
fun show2(){
    LineChartScreen()

}
