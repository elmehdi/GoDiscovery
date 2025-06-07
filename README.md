# Go Project Setup Guide

Unlike other programming languages, Go requires a specific project setup process. I can't simply create a `file.go` and run it with `go file.go` - that approach won't work properly.

## 1. Initialize a Go Module

First, I need to initialize a Go module using:

```bash
go mod init <module-name>
```

The module name can be either a simple name or a GitHub repository URL (e.g., `github.com/username/project-name`).

This command creates a `go.mod` file that serves several important purposes:
- Tells Go the name of my module
- Enables dependency tracking
- Required for imports, external libraries, and proper project structure
- Specifies the minimum Go version

### Example go.mod file:

```go
module github.com/myusername/my-project

go 1.21

require (
    github.com/gorilla/mux v1.8.0
    github.com/lib/pq v1.10.9
)
```

### The go.sum file

Alongside the `go.mod` file, Go automatically creates a `go.sum` file. This file contains cryptographic checksums (hashes) for every version of every direct and indirect dependency used in my project.

**Important:** I must commit both `go.mod` and `go.sum` files to my repo
## 2. Create Project Structure (Recommended)

I should organize my project with a clear structure:

```
my-module-name/
├── go.mod
├── go.sum
├── main.go          ← entry point
├── pkg/             ← public packages
├── internal/        ← private packages
└── cmd/             ← application entry points (optional)
```

### Basic main.go structure:

```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, world!")
}
```

## 3. Run or Build the Code

Once I have my module initialized and code written, I can:

**Run the code directly:**
```bash
go run main.go
```

**Build an executable:**
```bash
go build
```

**Install dependencies (if any):**
```bash
go mod tidy
```
go mod tidy to clean up dependencies, and others for managing the go.mod and go.sum files.

This proper setup ensures that my Go project follows best practices and works correctly with Go's module system.


To test the gRPC I had to :
go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
+ sudo apt install -y protobuf-compiler
then run from project root : 
protoc --go_out=go-client --go-grpc_out=go-client \                                                                                ✔ 
  --go_opt=paths=source_relative --go-grpc_opt=paths=source_relative \
  proto/greeter.proto


1st run the java server : mvn compile exec:java -Dexec.mainClass="com.example.grpc.GrpcServer"
2nd go run main.go
