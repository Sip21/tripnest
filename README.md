# TripNest

A modern travel website built on **Adobe Experience Manager as a Cloud Service (AEMaaCS)**, designed to deliver rich travel content experiences with a scalable, cloud-native architecture.

---

## About

TripNest is an AEM Sites project for exploring and booking travel destinations. Built following Adobe best practices, it serves as both a production-ready foundation and a hands-on learning project for AEMaaCS development patterns.

---

## Tech Stack

| Layer | Technology |
|---|---|
| CMS | Adobe Experience Manager as a Cloud Service |
| Backend | Java 21, OSGi, Apache Sling |
| Frontend | HTL (Sightly), ClientLibs |
| Build | Maven, AEM Maven Archetype |
| Deployment | Adobe Cloud Manager |
| Local Dev | AEM SDK, Docker (Dispatcher) |

---

## Project Structure

```
tripnest/
├── core/                   # Java bundle — Sling Models, OSGi Services, Schedulers, Listeners
├── ui.apps/                # AEM components, templates, dialog XML, ClientLibs
├── ui.content/             # Sample content, page structures
├── ui.config/              # OSGi configurations (.cfg.json)
├── ui.frontend/            # Frontend build (webpack/npm)
├── dispatcher/             # Apache Dispatcher configuration
└── it.tests/               # Integration tests
```

---

## Key Features

- **AEM Sites** — Page authoring with Editable Templates and Style System
- **Sling Models** — Clean separation of business logic from presentation
- **OSGi Services** — Modular, configurable backend services
- **Schedulers** — Configurable background jobs with enable/disable flags
- **Event Handling** — Custom OSGi events and ResourceChangeListeners
- **Dispatcher** — Caching and security layer with Docker-based local setup
- **System Users** — Secure JCR access via repo init scripts

---

## Local Development Setup

### Prerequisites

- Java 21
- Maven 3.8+
- AEM SDK (author on `localhost:4504`)
- Node.js 18+ (for frontend build)
- Docker (for Dispatcher)

### Build and Deploy

```bash
# Full build and deploy to local AEM
mvn clean install -PautoInstallSinglePackage
mvn clean install -PautoInstallSinglePackage -Daem.port=4504 -Denforcer.skip=true

# Deploy core bundle only
mvn clean install -PautoInstallBundle -pl core
mvn clean install -PautoInstallBundle -Daem.port=4504 -Denforcer.skip=true

# Deploy ui.config only
mvn clean install -PautoInstallPackage -pl ui.config
```

### Dispatcher (Docker)

```bash
cd dispatcher/
docker-compose up
```

Dispatcher runs on `localhost:8080`, proxying to AEM Publish on `localhost:4505`.

---

## OSGi Configuration

All configurations live in `ui.config/src/main/content/jcr_root/apps/tripnest/osgiconfig/`.

| Folder | Applies To |
|---|---|
| `config/` | All environments |
| `config.author/` | Author only |
| `config.publish/` | Publish only |
| `config.dev/` | Dev environment only |

---

## Package Structure

```
com.tripnest.core/
├── models/         # Sling Models
├── services/       # OSGi Service interfaces
│   └── impl/       # Service implementations
├── schedulers/     # OSGi Schedulers
├── listeners/      # Event Handlers, ResourceChangeListeners
└── config/         # OSGi @ObjectClassDefinition interfaces
```

---

## Author

**Supriya**
AEM Developer — AEMaaCS, Sling, OSGi, Java

---

## License

This project is for personal learning and development purposes.
