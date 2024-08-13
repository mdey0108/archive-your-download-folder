@echo off
REM Set the source directory
REM set "source=C:\path\to\source\folder"
set "source=C:\Users\mdey0\Downloads"

REM Set the base destination directory
REM et "baseDestination=C:\path\to\destination\folder"
set "baseDestination=M:\DownloadsArchive"

REM Get the current date and time in YYYYMMDDHHMMSS format

for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "YY=%dt:~2,2%" & set "YYYY=%dt:~0,4%" & set "MM=%dt:~4,2%" & set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%" & set "Min=%dt:~10,2%" & set "Sec=%dt:~12,2%"

set "datetime=%YYYY%%MM%%DD%%HH%%Min%%Sec%"
echo datetime: "%datetime%"


REM Create the timestamped folder in the destination
set "destination=%baseDestination%\%datetime%"
mkdir "%destination%"

REM Move files from source to timestamped destination folder
Rem move "%source%\*" "%destination%"

REM Copy files and folders from source to timestamped destination folder
xcopy "%source%\*" "%destination%\" /E /I /H /Y


REM Check if the move was successful
if %errorlevel% equ 0 (
    echo copy successful to %destination%, creating done.ok file...
	echo deleting source files and folders...
	rmdir /S /Q "%source%"
	mkdir "%source%"
	echo Deletion successful, creating done.ok file...
    echo  Move successful to %destination%, > "%source%\done.ok"
) else (
    echo Move failed.
)

echo Script complete.
