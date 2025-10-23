package com.example.myapplication.data

data class GoldPricesResponse(
    val Header: Header,
    val Data: Data
)

data class Header(
    val StatusCode: String,
    val StatusDescription: String,
    val ObjectID: String
)

data class Data(
    val GoldRate: List<GoldRate>
)

data class GoldRate(
    val RateDate: String,
    val CurrencyCode: String,
    val ProductName: String,
    val SaleRate: String,
    val PurchaseRate: String
)

data class PriceDateRequest(
    val PriceDate: String // Örn: "2025-10-14T00:00:00+03:00"
)

