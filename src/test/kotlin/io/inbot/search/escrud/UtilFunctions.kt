package io.inbot.search.escrud

import java.util.UUID

// use random ids so we don't have conflicting tests
internal fun randomId() = UUID.randomUUID().toString()
