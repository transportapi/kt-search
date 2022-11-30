package com.jillesvangurp.ktsearch

class ThreadLocalIndex : IndexProvider {
    private val index = ThreadLocal<Int>()

    override fun get(): Int = index.get()

    override fun set(value: Int) = index.set(value)
}

actual fun indexProvider(): IndexProvider = ThreadLocalIndex()
