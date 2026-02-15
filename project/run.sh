#!/bin/bash

echo "🔨 Compiling Java classes..."

# Create the build directory if it doesn't exist
mkdir -p build

# Find all .java files in the src directory (including subdirectories) and compile them
find src -name "*.java" -exec javac -d build {} +

echo "✅ Build complete!"

# Copy all assets to build
cp -r assets build/

# Run the game
echo "🚀 Running the App..."
java -cp build core.Launcher

# For Error Detecting
if java -cp bin core.App; then
    echo "Program ran successfully."
else
    echo "Program crashed with an error."
    read -p "Press enter to exit..."
fi