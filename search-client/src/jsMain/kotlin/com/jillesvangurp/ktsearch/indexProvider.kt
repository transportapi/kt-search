package com.jillesvangurp.ktsearch

class SimpleIndexProvider : IndexProvider {
    private var index = 0

    override fun get(): Int = index

    override fun set(value: Int) {
        index = value
    }
}

actual fun indexProvider(): IndexProvider = SimpleIndexProvider()
