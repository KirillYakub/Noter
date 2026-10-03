pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Noter"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:presentation:ui")
include(":core:presentation:designsystem")
include(":auth:data")
include(":auth:domain")
include(":auth:presentation")
include(":settings:presentation")
include(":settings:domain")
include(":settings:data")
include(":feature:notes:presentation")
include(":feature:notes:domain")
include(":feature:notes:data")
include(":feature:notes:widgets")
