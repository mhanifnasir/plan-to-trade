package com.example.plantotrade.analysis

import kotlin.math.*

class TechnicalAnalysisCalculator {

    // Calculate RSI (Relative Strength Index)
    fun calculateRSI(candlestickPrices: DoubleArray, period: Int): Double {
        val gains = DoubleArray(period)
        val losses = DoubleArray(period)

        for (i in 1 until period) {
            val change = candlestickPrices[i] - candlestickPrices[i - 1]
            if (change > 0) {
                gains[i] = change
            } else {
                losses[i] = -change
            }
        }

        val averageGain = gains.average()
        val averageLoss = losses.average()

        return if (averageLoss == 0.0) 100.0 else 100 - (100 / (1 + (averageGain / averageLoss)))
    }

    // Calculate MACD (Moving Average Convergence Divergence)
    fun calculateMACD(candlestickPrices: DoubleArray, shortPeriod: Int, longPeriod: Int): Pair<DoubleArray, DoubleArray> {
        val shortEMA = calculateEMA(candlestickPrices, shortPeriod)
        val longEMA = calculateEMA(candlestickPrices, longPeriod)

        val macd = DoubleArray(candlestickPrices.size)
        for (i in 0 until candlestickPrices.size) {
            macd[i] = shortEMA[i] - longEMA[i]
        }

        return Pair(macd, calculateSignalLine(macd))
    }

    private fun calculateEMA(candlestickPrices: DoubleArray, period: Int): DoubleArray {
        val ema = DoubleArray(candlestickPrices.size)
        val alpha = 2.0 / (period + 1)
        ema[0] = candlestickPrices[0]

        for (i in 1 until candlestickPrices.size) {
            ema[i] = (candlestickPrices[i] * alpha) + (ema[i - 1] * (1 - alpha))
        }

        return ema
    }

    private fun calculateSignalLine(macd: DoubleArray): DoubleArray {
        return calculateEMA(macd, 9)
    }

    // Calculate Moving Average
    fun calculateMovingAverage(candlestickPrices: DoubleArray, period: Int): DoubleArray {
        val movingAverage = DoubleArray(candlestickPrices.size)
        for (i in 0 until candlestickPrices.size) {
            movingAverage[i] = if (i < period) {
                candlestickPrices.copyOfRange(0, i + 1).average()
            } else {
                candlestickPrices.copyOfRange(i - period + 1, i + 1).average()
            }
        }
        return movingAverage
    }
}