package com.imshakthi.javatestserver.helper

import spock.lang.Specification

class StringFormatterTest extends Specification {

    private StringFormatter instance

    def setup() {
        instance = StringFormatter.getInstances()
    }

    def "string formatter"() {
        when: "say hello method is called"
            String actual = instance.sayHello();

        then: "it should return Hello"
            actual == "Hello"
    }
}
