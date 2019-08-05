/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.crypto.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyProperties
import com.tulsiram.crypto.Algorithm
import com.tulsiram.crypto.AlgorithmSpec
import com.tulsiram.crypto.Crypto
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var crypto: Crypto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCrypto()

        btnEncrypt.setOnClickListener {
            editText2.setText(crypto.encrypt(editText.text.toString()))
        }

        btnDecrypt.setOnClickListener {
            tvDecrypted.text = crypto.decrypt(editText2.text.toString())
        }
    }

    /**
     * initialize crypto
     */
    private fun initCrypto() {
        val algorithm = Algorithm(
            KeyProperties.KEY_ALGORITHM_AES,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
            KeyProperties.BLOCK_MODE_GCM,
            KeyProperties.ENCRYPTION_PADDING_NONE
        )
        val algorithmSpec = AlgorithmSpec(algorithm, "Alias")

        crypto = Crypto.getInstance(algorithmSpec)
    }
}
