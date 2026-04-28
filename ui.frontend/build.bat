@echo off
echo Building frontend...
call npm run dev

echo Deploying ui.apps (fast)...
cd ..
call mvn -pl ui.apps -PautoInstallPackage install -DskipTests -Daem.port=4504

echo Done!
pause