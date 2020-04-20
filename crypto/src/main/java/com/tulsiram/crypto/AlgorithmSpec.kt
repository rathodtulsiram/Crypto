/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.crypto

import android.security.keystore.KeyProperties.*

/**
 * @return [ALIAS] as default alias
 */
private const val ALIAS = "Alias"

/**
 * data class used to store algorithm specification details
 *
 * @see [android.security.keystore.KeyProperties]
 * @param algorithm algorithm [Algorithm]
 * @param alias alias for keystore
 */
data class AlgorithmSpec(
    val algorithm: Algorithm = Algorithm(),
    val alias: String = ALIAS
)

/**
 * data class used to store algorithm details
 *
 * @see [android.security.keystore.KeyProperties]
 * @param name algorithm name
 * @param purpose purpose of algorithm
 * @param mode block mode of algorithm
 * @param padding padding
 */
data class Algorithm(
    val name: String = KEY_ALGORITHM_AES,
    val purpose: Int = PURPOSE_ENCRYPT or PURPOSE_DECRYPT,
    val mode: String = BLOCK_MODE_GCM,
    val padding: String = ENCRYPTION_PADDING_NONE
)