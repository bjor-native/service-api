package dao

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import com.mongodb.client.result.InsertOneResult
import model.CheckPayment
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import utils.getMongoHost
import utils.getMongoPort

private val host = getMongoHost()
private val port = getMongoPort()

val client = KMongo.createClient()
val db: MongoDatabase = client.getDatabase("test_api")
val paymentCollection = db.getCollection<CheckPayment>("payment")

/**
 * Methods
 */

fun getAllPayment(): FindIterable<CheckPayment> {
    return paymentCollection.find()
}

fun getPaymentById(id: String): CheckPayment? {
    return paymentCollection.findOne(CheckPayment::id eq id)
}

fun setPaymentById(checkPayment: CheckPayment): InsertOneResult {
    return paymentCollection.insertOne(checkPayment)
}