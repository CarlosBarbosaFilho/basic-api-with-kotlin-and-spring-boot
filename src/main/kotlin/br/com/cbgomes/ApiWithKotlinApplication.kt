package br.com.cbgomes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.hateoas.client.LinkDiscoverer
import org.springframework.hateoas.client.LinkDiscoverers
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer
import org.springframework.plugin.core.SimplePluginRegistry
import java.util.*


@SpringBootApplication
class ApiWithKotlinApplication(){
    @Bean
    fun discovers(): LinkDiscoverers? {
        val plugins: MutableList<LinkDiscoverer> = ArrayList()
        plugins.add(CollectionJsonLinkDiscoverer())
        return LinkDiscoverers(SimplePluginRegistry.create(plugins))
    }

}

fun main(args: Array<String>) {
    runApplication<ApiWithKotlinApplication>(*args)
}

