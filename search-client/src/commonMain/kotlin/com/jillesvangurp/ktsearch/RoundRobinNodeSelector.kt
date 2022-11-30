package com.jillesvangurp.ktsearch

interface IndexProvider {
    fun get(): Int
    fun set(value: Int)
}
expect fun indexProvider(): IndexProvider

class RoundRobinNodeSelector(
    private val nodes: Array<out Node>
) : NodeSelector {
    private var index: Int = 0
    override fun selectNode(): Node {
        return nodes[index++].also {
            if(index > nodes.size-1) {
                index=0
            }
        }
    }
}
