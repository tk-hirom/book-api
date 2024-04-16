//package com.example.bookapi
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.boot.context.properties.EnableConfigurationProperties
//import org.springframework.boot.context.properties.bind.ConstructorBinding
//import org.springframework.context.annotation.Configuration
//
//@Configuration // Springの設定クラスであることを宣言
//@EnableConfigurationProperties(AppProperties::class) // ConfigurationPropertiesクラスを有効化 application.ymlを読めるように
//class AppPropertiesConfig
//
//@ConfigurationProperties(prefix = "app")
//data class AppProperties @ConstructorBinding constructor(
//    val host: String,
//    val port: Int,
//    val user: String,
//    val password: String,
//    val database: String
//)
