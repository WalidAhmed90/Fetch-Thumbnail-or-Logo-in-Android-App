# Fetch Thumbnail or Logo in Android App

This repository contains an Android application that demonstrates how to fetch and display thumbnails or logos from any given URL. The app uses Retrofit for network requests, Jsoup for HTML parsing, and Glide for image loading.

## Features

- Fetch HTML content from a given URL.
- Parse the HTML to extract thumbnail or logo URLs.
- Display the fetched image using Glide.
- Fallback to a default logo if no thumbnail or logo is found.

## Getting Started

### Prerequisites

- Android Studio
- Kotlin
- Internet connection

### Dependencies

Add the following dependencies to your `build.gradle.kts` file:

```kotlin
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
implementation("org.jsoup:jsoup:1.13.1")
implementation("com.github.bumptech.glide:glide:4.11.0")
kapt("com.github.bumptech.glide:compiler:4.11.0")

