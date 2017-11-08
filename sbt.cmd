@echo off
set SCRIPT_DIR=%~dp0
java -Xms256M -Xmx1024M -Xss1M -XX:MaxPermSize=384M -jar "%SCRIPT_DIR%sbt-launch.jar" %* -Dfile.encoding=UTF8
