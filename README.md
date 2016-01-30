# TwitterTinkerFicus

[![Build Status](https://travis-ci.org/skyYaga/TwitterTinkerFicus.svg?branch=master)](https://travis-ci.org/skyYaga/TwitterTinkerFicus)

An application that measures moisture using Tinkerforge and twitters results.

# How to run the app?

1. You need a set up Tinkerforge Master Brick with Moisture Bricklet
2. Your plant needs a Twitter account
3. Create a Twitter app for your plant via [https://apps.twitter.com/](https://apps.twitter.com/)
4. Rename `twitter4j.properties.example to `twitter4j.properties` and configure it with your Twitter app's values
5. Rename `config.properties.example` to `config.properties and` configure it
6. Start `TinkerTwitterFicusApplication`