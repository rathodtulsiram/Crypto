/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.crypto

import android.util.Base64
import com.tulsiram.crypto.KeyGenerator.getSecretKey
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec

/**
 * @author Tulsiram
 * @since 23/07/2019
 */
class Crypto private constructor(algorithmSpec: AlgorithmSpec) {

    private val gcmParameterSpec = GCMParameterSpec(128, ByteArray(FIXED_IV))
    private lateinit var cipher: Cipher
    private lateinit var key: Key

    companion object {
        @Volatile
        private var INSTANCE: Crypto? = null

        fun getInstance(algorithmSpec: AlgorithmSpec): Crypto =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Crypto(algorithmSpec).also { INSTANCE = it }
            }

        const val FIXED_IV = 12 // fixed IV 12 byte supported
    }

    init {
        val algorithm = algorithmSpec.algorithm
        val transformation = "${algorithm.name}/${algorithm.mode}/${algorithm.padding}"
        try {
            cipher = Cipher.getInstance(transformation)
            key = getSecretKey(algorithmSpec)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * @return encrypted string
     *
     * @param strToEncrypt string to encrypt
     */
    @Throws(UninitializedPropertyAccessException::class)
    fun encrypt(strToEncrypt: String): String? {
        if (!::cipher.isInitialized) {
            throw UninitializedPropertyAccessException("Initialize Cipher with Specified Algorithm")
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec)
            return Base64.encodeToString(cipher.doFinal(strToEncrypt.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @return decrypted string
     *
     * @param strToDecrypt string to decrypt
     */
    @Throws(UninitializedPropertyAccessException::class)
    fun decrypt(strToDecrypt: String): String? {
        if (!::cipher.isInitialized) {
            throw UninitializedPropertyAccessException("Initialize Cipher with Specified Algorithm")
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec)
            return String(cipher.doFinal(Base64.decode(strToDecrypt, Base64.DEFAULT)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}