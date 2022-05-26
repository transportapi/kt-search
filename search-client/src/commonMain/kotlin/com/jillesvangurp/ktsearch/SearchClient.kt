@file:Suppress("MemberVisibilityCanBePrivate")

package com.jillesvangurp.ktsearch

import kotlinx.serialization.*

/*
{"cluster_name":"docker-test-cluster","status":"yellow","timed_out":false,"number_of_nodes":1,"number_of_data_nodes":1,"active_primary_shards":13,"active_shards":13,"relocating_shards":0,"initializing_shards":0,"unassigned_shards":7,"delayed_unassigned_shards":0,"number_of_pending_tasks":0,"number_of_in_flight_fetch":0,"task_max_waiting_in_queue_millis":0,"active_shards_percent_as_number":65.0}
 */

@Serializable
enum class ClusterStatus {
    @SerialName("red")
    Red,
    @SerialName("yellow")
    Yellow,
    @SerialName("green")
    Green
}
@Serializable
data class ClusterHealthResponse(
    @SerialName("cluster_name")
    val clusterName: String,
    val status: ClusterStatus,
    @SerialName("timed_out")
    val timedOut: Boolean,
)

fun <T> Result<RestResponse>.parse(deserializationStrategy: DeserializationStrategy<T>): T = DEFAULT_JSON.decodeFromString(deserializationStrategy, this.getOrThrow().text)

class SearchClient(val restClient: RestClient) {
    suspend fun clusterHealth(): ClusterHealthResponse {
        return restClient.get {
            it.path("_cluster","health")
        }.parse(ClusterHealthResponse.serializer())
    }
}