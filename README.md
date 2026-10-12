# Noter — Modern Android Notes

**A modern Android notes app built with Kotlin, Jetpack Compose, Room, Clean Architecture, and a multi-module structure.**

Noter brings everyday notes, tasks, and ideas together in a clean, customizable experience. This project showcases a maintainable Android architecture alongside a polished Compose UI, local persistence, calendar-based browsing, and a home-screen widget.

> Portfolio project focused on modern Android development, modular design, and a user-centered note-taking experience.

## Highlights

- **Modern Android UI** built with Jetpack Compose and a cohesive visual theme.
- **Clean Architecture** with clear boundaries between presentation, domain, and data responsibilities.
- **Multi-module codebase** designed to keep features and shared foundations organized and maintainable.
- **Room persistence** for locally stored notes and folder membership.
- **Flexible organization** with custom folders, an Important filter, and support for placing a note in multiple folders.
- **Calendar browsing** to find notes by date, including a full-calendar view.
- **Home-screen widget** for quick access and note creation.
- **Personalization** for note appearance, sorting, and date-based browsing.
- **Sharing support** to send note content to other Android apps.

## Screenshots

| Onboarding | Notes overview |
|---|---|
| ![Noter onboarding screen](noter-readme-assets/onboarding.png) | ![Noter notes overview with calendar and folder filters](noter-readme-assets/notes-overview.png) |

| Note editor | Settings |
|---|---|
| ![Noter note editor](noter-readme-assets/note-editor.png) | ![Noter settings screen](noter-readme-assets/settings.png) |

| Account creation | Home-screen widget |
|---|---|
| ![Noter account creation screen](noter-readme-assets/login.png) | ![Noter home-screen widget](noter-readme-assets/home-widget.png) |

## Architecture

Noter applies **Clean Architecture** principles in a **multi-module Android project**. The goal is to keep UI, business rules, and persistence responsibilities separate, making features easier to evolve and maintain.

- **Presentation:** Compose screens and UI state for user-facing features.
- **Domain:** Feature rules and contracts kept independent of Android UI and database details.
- **Data:** Room-backed persistence and mappings between stored and domain data.
- **Modules:** Feature code is organized into modules, with shared concerns kept in reusable modules where appropriate.

The exact module names and dependency direction follow the project’s Gradle setup.

## Built with

- **Kotlin**
- **Jetpack Compose**
- **Room**
- **Clean Architecture**
- **Multi-module Gradle project**

## Feature tour

### Notes and organization

Create and edit notes, browse them in a card-based overview, and organize them with custom folders. Mark notes as Important and assign a note to more than one folder.

### Calendar and discovery

Browse notes by date using the date row and full-calendar view. Search and sorting options help surface the note you need.

### Personalization and quick access

Adjust note presentation in Settings and use the home-screen widget to see note information or start creating a note from the launcher.

### Sharing

Share note data with compatible Android apps through the system share flow.

## Getting started

1. Clone this repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync.
4. Select the app run configuration and launch it on an emulator or Android device.

> Add the repository URL, Android Studio version, SDK requirements, and any required local configuration here before publishing if your project needs them.

## Project status

Noter is an evolving portfolio project. The implementation may change as features and architecture continue to develop.

## Author

**Kirill** — Android developer

- GitHub: [add your profile link](https://github.com/)
- LinkedIn: [add your profile link](https://www.linkedin.com/)

---

*Designed and developed as a showcase of modern Android UI and architecture practices.*
