#!/bin/bash

# Pickleball Ladder League - Setup and Run Script

echo "==================================="
echo "Pickleball Ladder League"
echo "Setup & Run"
echo "==================================="

# Check Java
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

echo "✓ Java is installed"
java -version

# Check Maven
if ! command -v mvn &> /dev/null; then
    echo ""
    echo "⚠️  Maven is not installed."
    echo ""
    echo "To install Maven on macOS:"
    echo "  brew install maven"
    echo ""
    echo "To install Maven on Linux:"
    echo "  sudo apt-get install maven"
    echo ""
    exit 1
fi

echo "✓ Maven is installed"
mvn --version

# Build project
echo ""
echo "Building project..."
mvn clean install -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed. Please check errors above."
    exit 1
fi

echo ""
echo "✓ Build successful!"
echo ""
echo "==================================="
echo "Starting Application..."
echo "==================================="
echo "Server will be available at: http://localhost:8080"
echo "Press Ctrl+C to stop"
echo ""

# Run application
mvn spring-boot:run
