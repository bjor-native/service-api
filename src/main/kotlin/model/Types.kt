package model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val start_t: Long,
)

@Serializable
data class Client(val id: String, val userName: String)

@Serializable
data class Payment(val amount: Long, val currency: Int, val exponent: Int)

@Serializable
data class Account(
    val id: String,
    @Contextual val extra: Map<String, String>
)

@Serializable
data class CheckPayment(
    val id: String,
    val tx: Transaction,
    val client: Client,
    val payment: Payment,
    val account: Account
)

@Serializable
data class PayPayment(
    val id:String,
    val tx:Transaction
)