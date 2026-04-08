# LogWatching 🪵

> Inspired by the UNIX `tail -f` command

A Spring Boot service that streams live logs from any automated system to multiple connected clients over WebSocket — in real time, without polling.

---

## The Problem

When a long automation run kicks off, developers are left waiting blindly until the run finishes to know if something went wrong. By then, compute time is already wasted. There was no easy way to watch what's happening *as it happens* — especially for multiple people at once.

## The Solution

LogWatching tails log files from any automation framework and pushes updates to connected clients over a persistent WebSocket connection. Think `tail -f` — but over the web, for your whole team.

- Spot failures early and kill the run before it completes
- Multiple developers can watch the same live run simultaneously
- No page refresh, no polling — just a live stream

---

## How It Works

```
Automation Run → Writes to log file → LogWatching tails the file → Pushes updates over WebSocket → Connected clients see live output
```

Each client gets its own persistent WebSocket connection. The service is stateless by design, which keeps it horizontally scalable — add more instances as your team grows.

---

## Features

- 🔴 **Live log streaming** — tails log files and pushes new lines as they are written
- 👥 **Multi-client support** — multiple users can connect and watch simultaneously
- 🔌 **WebSocket-based** — persistent connections, no polling overhead
- 🔧 **Framework-agnostic** — plugs into any automation system that writes to a log file
- 📦 **Standalone service** — deploy independently, no changes needed to your existing automation setup

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot |
| Real-time transport | WebSocket |
| Build tool | Maven |
| Language | Java (93.7%), HTML (6.3%) |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

### Run locally

```bash
# Clone the repo
git clone https://github.com/JDevRohanY/LogWaching.git
cd LogWaching

# Build the project
./mvnw clean install

# Run the service
./mvnw spring-boot:run
```

### Connect a client

Once the service is running, open the WebSocket endpoint from any browser or WebSocket client:

```
ws://localhost:8080/logs
```

The service will start streaming new lines from `logs.txt` as they are appended.

---

## Configuration

Point the service to your own log file by updating the log file path in `application.properties`:

```properties
logwatching.file.path=logs.txt
```

Replace `logs.txt` with the path to your automation framework's log output file.

---

## Use Case — Automation Test Monitoring

This service was originally built to solve a real problem in CI/CD workflows:

1. A test automation run starts (Playwright, TestNG, Selenium, etc.)
2. The framework writes output to a log file
3. LogWatching tails that file and streams updates to connected developers
4. If an early failure is detected, the team can stop and rerun immediately — without waiting for the full run to complete

This directly reduces wasted run time and speeds up feedback loops for engineering teams.

---

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/logwatching/
│   │       ├── controller/      # WebSocket handlers
│   │       ├── service/         # Log tailing logic
│   │       └── config/          # WebSocket configuration
│   └── resources/
│       └── application.properties
logs.txt                          # Default log file (replace with your own)
pom.xml
```

---

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you'd like to change.

---

## Author

**Rohan Yadav**
[LinkedIn](https://linkedin.com/in/rohan-yadav) · [GitHub](https://github.com/JDevRohanY)

---

## License

MIT
