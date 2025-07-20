echo.
REM ----------- Set Class Paths and Calls setenv.bat -----------------
SET OLDCLASSPATH=%CLASSPATH%
call classpath.bat
REM ------------------------------------------------------------------
@java -Djava.util.logging.config.file=console.cfg net.sf.l2j.accountmanager.SQLAccountManager
@pause
SET CLASSPATH=%OLDCLASSPATH%
