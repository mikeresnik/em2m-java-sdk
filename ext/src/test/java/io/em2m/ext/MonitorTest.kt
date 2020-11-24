/**
 * ELASTIC M2M Inc. CONFIDENTIAL
 * __________________
 *
 * Copyright (c) 2013-2020 Elastic M2M Incorporated, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Elastic M2M Incorporated
 *
 * The intellectual and technical concepts contained
 * herein are proprietary to Elastic M2M Incorporated
 * and may be covered by U.S. and Foreign Patents,  patents in
 * process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Elastic M2M Incorporated.
 */
package io.em2m.sdk.ext

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import io.em2m.policy.model.Claims
import io.em2m.simplex.Simplex
import io.em2m.simplex.model.BasicKeyResolver
import io.em2m.simplex.model.Key
import io.em2m.simplex.model.PathKeyHandler
import org.junit.Ignore
import org.junit.Test

@Ignore
class MonitorTest {

    private val config: Config = ConfigFactory.parseMap(mapOf("data.ext.dir" to "src/test/ext"))
    private val simplex = Simplex()

    private val ctx = mapOf("claims" to Claims(mapOf(
        "entitlements" to listOf("web", "product.ams"),
        "brand" to "em2m")))

    init {
        simplex.keys(BasicKeyResolver(mapOf(Key("claims", "*") to PathKeyHandler("claims"))))
    }

    private val service = ExtensionServiceImpl(config, simplex)

    @Test
    fun testMonitoring() {
        service.startMonitoring()
        Thread.sleep(1000)
        service.stopMonitoring()
        Thread.sleep(500)
    }

}
