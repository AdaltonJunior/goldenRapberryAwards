@echo on
@echo Iniciando Servico
@echo off
start "" /b cmd /c "timeout /nobreak 10 >nul & start http://localhost:8080/awards/statistics
gradlew bootRun