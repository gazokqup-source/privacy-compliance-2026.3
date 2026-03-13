@echo off
echo ========================================
echo   隐私协议智能合规风险辅助审查系统
echo ========================================
echo.

cd /d "%~dp0frontend"
echo [1/2] 启动前端...
start "Frontend" cmd /k "npm run dev"

timeout /t 3 /nobreak >nul

cd /d "%~dp0backend"
echo [2/2] 启动后端...
start "Backend" cmd /k "mvn spring-boot:run"

echo.
echo ========================================
echo   启动完成！
echo   前端：http://localhost:3000
echo   后端：http://localhost:8080
echo   Swagger: http://localhost:8080/swagger-ui.html
echo ========================================
pause
