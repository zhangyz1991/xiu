@echo off
REM 声明采用UTF-8编码
rem chcp 65001
echo start to update main program......
if exist D:\Documents\IdeaProjects\xiu\xiu-service\target\xiu-service-0.0.1-SNAPSHOT.jar (
    move /y D:\Documents\IdeaProjects\xiu\xiu-service\target\xiu-service-0.0.1-SNAPSHOT.jar D:\opt\services\xiu\xiu-service\xiu-service-0.0.1-SNAPSHOT.jar
)
echo main program update complete
echo start to update lab:jars......

set source= D:\Documents\IdeaProjects\xiu\xiu-service\lib
set destination= d:\opt\services\xiu\xiu-service\lib
if not exist %destination% md %destination%

:: in (*) in和(*)之间要有空格,否则有错 表示d:\opt\services\xiu\xiu-service\libs目录及其子目录中的所有文件
rem delete redundant files
for /r %destination% %%b in (*) do (
	if not exist %source%\%%~nxb (
		del %%b
		echo removed redundant jar: %%~nxb
	) else (
		call :subfor %%b
	)
)
rem /S 复制目录和子目录,除了空的 /C 即使有错误,也继续复制 /Y 禁止提示以确认改写一个现存目标文件 /V 验证每个新的文件
rem /D:m-d-y 复制在指定日期或指定日期以后改变的文件. 如果没有提供日期,只复制那些源时间比目标时间新的file,但是不存在的也不会复制，所以有问题
rem /i If the destination does not exist and multiple files are to be copied, it is assumed that the destination must be a directory
rem xcopy %source%\*.* %destination% /s /c /y /v /i /d:01-01-2049
for /r %source% %%a in (*) do (
	if not exist %destination%\%%~nxa copy /b /v %%a %destination%
)

echo lib:jars update complete
echo preparation completed,ready to start......
goto :startup
:: pause 输入任意键退出 pause>nul 表示直接退出
:: goto :eof表示退出程序,子程序要放最后
goto :eof
:subfor
for /r %source% %%a in (%~nx1) do (
	if %%~za neq %~z1 (
		del %1
		echo removed the changed jar: %~nx1
	)
)
goto :eof
:startup
java -Dloader.path=lib/ -jar D:\opt\services\xiu\xiu-service\xiu-service-0.0.1-SNAPSHOT.jar