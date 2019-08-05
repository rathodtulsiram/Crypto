/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.crypto

import android.security.keystore.KeyProperties
import org.junit.Before
import org.junit.Test

class CryptoTest {

    private lateinit var algorithmSpec: AlgorithmSpec
    private lateinit var crypto: Crypto

    @Before
    fun setUp() {
        val algorithm = Algorithm(
            KeyProperties.KEY_ALGORITHM_AES ,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
            KeyProperties.BLOCK_MODE_GCM,
            KeyProperties.ENCRYPTION_PADDING_NONE
        )
        algorithmSpec = AlgorithmSpec(algorithm,  "Alias")

        crypto = Crypto.getInstance(algorithmSpec)
    }

    @Test
    fun testCipher() {
        val text = "test"
        val encrypted = crypto.encrypt(text)
        val decrypted = crypto.decrypt(encrypted!!)
        assert(text == decrypted)
    }
}