@echo on
@echo Iniciando Teste de Integracao
@echo off
start "" /b cmd /c "timeout /nobreak 20 >nul & cd build\reports\tests\test & start index.html
gradlew clean test
pause;