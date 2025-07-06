@echo off
echo 🔧 Compiling Java source files...

REM Make sure the 'out' folder exists
if not exist out mkdir out

javac -d out ^
--module-path lib\javafx-sdk-21\lib ^
--add-modules javafx.controls,javafx.fxml ^
-cp "lib\gs-core-2.0.jar;lib\gs-ui-javafx-2.0.jar" ^
src\Main.java src\PrimSimulatorApp.java src\PrimAlgorithmExecutor.java

if %errorlevel% neq 0 (
    echo ❌ Compilation failed.
    pause
    exit /b
)

echo ✅ Compilation successful.

echo 🧪 Creating JAR file...
jar cfm PrimRouting.jar manifest.mf -C out .

echo ✅ JAR file created: PrimRouting.jar
pause
