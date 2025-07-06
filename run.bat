@echo off
echo Running PrimRouting Simulator...
java --module-path lib\javafx-sdk-21\lib --add-modules javafx.controls,javafx.fxml ^
-cp "PrimRouting.jar;lib\gs-core-2.0.jar;lib\gs-ui-javafx-2.0.jar" Main
pause
