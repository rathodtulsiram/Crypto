Crypto
===============

Crypto is used to encrypt and decrypt text using specified [cipher](https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html). A symetric key is generated using KeyGenerator & Stored in Android KeyStore for encryption and decryption purpose.

Gradle
------
```
dependencies {
    implementation 'com.tulsiram:crypto:1.0.2'
}
```

Usage
-----
```kotlin
val algorithm = Algorithm(
    KeyProperties.KEY_ALGORITHM_AES,
    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
    KeyProperties.BLOCK_MODE_GCM,
    KeyProperties.ENCRYPTION_PADDING_NONE
)
val algorithmSpec = AlgorithmSpec(algorithm, "Alias")
val crypto = Crypto.getInstance(algorithmSpec)

val text = "test"
val encrypted = crypto.encrypt(text)
val decrypted = crypto.decrypt(encrypted!!)
```

Support
-----------
* Support Android 6.0 and above.

Changelog
---------
* **1.0.2**
    * Sample App Included
    * Code Refactoring
* **1.0.1**
    * Error Handling
    * Test Cases Added
* **1.0.0**
    * Initial release

License
-------

    Copyright 2019 Tulsiram Rathod

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
