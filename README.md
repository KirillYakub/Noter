# Noter

**A multi-module Clean Architecture Android notes app built with modern Jetpack tools.**

Noter is a personal notes application that demonstrates scalable and maintainable Android development through a fully modular codebase. It focuses on clean separation of concerns, reactive data flows, and solid engineering decisions.

---

## About

Noter supports accounts, folders, rich note editing, search, sorting preferences, and home-screen widgets.

It was built from scratch to showcase:

- Strict Clean Architecture with pure Kotlin domain layers
- Multi-module Gradle structure
- Reactive UI driven by Kotlin Flow and Coroutines
- Robust local persistence with Room (foreign keys, transactions, cascades)
- Modern declarative UI with Jetpack Compose + Material 3
- Glance widgets

---

## Module Structure

| Layer | Modules | Responsibility |
|-------|---------|----------------|
| **App Shell** | `:app` | Application entry point, global navigation graph, Koin initialization, theme |
| **Feature: Auth** | `:auth:data`<br>`:auth:domain`<br>`:auth:presentation` | Authentication, account management, session persistence |
| **Feature: Notes** | `:feature:notes:data`<br>`:feature:notes:domain`<br>`:feature:notes:presentation`<br>`:feature:notes:widgets` | Note CRUD, multi-folder mapping, search, rich text, Glance widgets |
| **Feature: Settings** | `:settings:data`<br>`:settings:domain`<br>`:settings:presentation` | Preferences, sort order, display density, session management |
| **Core** | `:core:data`<br>`:core:database`<br>`:core:domain`<br>`:core:presentation:designsystem`<br>`:core:presentation:ui` | Shared database abstractions, Room DAOs & type converters, domain models, design system components |

Domain modules contain pure Kotlin only — zero Android framework dependencies.

---

## Technical Highlights

- **Clean Architecture**  
  Domain models and use cases are pure Kotlin. Business logic is independent of the Android framework.

- **Reactive data layer**  
  UI is driven by `StateFlow` / `SharedFlow`. Search uses debounced `snapshotFlow`. Database queries are reactive Room Flows.

- **Room persistence**  
  - Foreign keys with `ON DELETE CASCADE` between accounts, folders, notes, and the `note_folders` cross-reference table  
  - Transaction-safe upserts (`withTransaction`) that keep note entities and folder memberships in sync  
  - Proper type converters and clear entity / relation modeling

- **Dependency Injection**  
  Koin with modular graphs (`singleOf`, `viewModelOf`). Constructor injection throughout.

- **UI**  
  Jetpack Compose + Material 3. Custom animated transitions, full-screen dialogs (`FullCalendarDialog`, `FolderSelectionDialog`), adaptive layouts.

- **Widgets**  
  Glance-based home screen widgets for quick access.

---

## Feature Tour

| Onboarding & Account Management | Authentication & Login |
|--------------------------------|------------------------|
| ![Onboarding](docs/screenshots/onboard_prev.png) | ![Login](docs/screenshots/login_prev.png) |
| Onboarding flow & accounts | Local authentication & login |

| Notes Overview & Folder Filters | Note Editor |
|--------------------------------|-------------|
| ![Notes Overview](docs/screenshots/notes_overview_prev.png) | ![Note Editor](docs/screenshots/note_edit_prev.png) |
| Card / list views, calendar row, folder chips | Rich text alignment, color themes, multi-folder assignment |

| Settings & Preferences | Home Screen Widgets |
|------------------------|---------------------|
| ![Settings](docs/screenshots/sett_prev.png) | ![Widgets](docs/screenshots/widgets_prev.png) |
| Sorting, date filters, display density | Glance widgets |

---

## Tech Stack

| Category | Technology |
|----------|------------|
| Language | Kotlin 2.0+ |
| UI | Jetpack Compose, Material 3 |
| Architecture | Clean Architecture + Multi-module Gradle |
| Persistence | Room (SQLite + transactions + foreign keys) |
| Async | Kotlin Coroutines & Flow (`StateFlow`, `SharedFlow`) |
| DI | Koin |
| Navigation | Jetpack Navigation (type-safe Compose routes) |
| Widgets | Glance |

---

## Engineering Decisions

- Domain layer is framework-agnostic (pure Kotlin)
- Reactive database queries and UI state management
- Transactional consistency for multi-table operations
- Modular dependency graphs for clear feature ownership
- Clean separation between data, domain, and presentation layers

---

## Download

Pre-built release APKs are available on the [GitHub Releases](https://github.com/KirillYakub/Noter/releases) page.

---

## Getting Started (from source)

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the `:app` configuration
