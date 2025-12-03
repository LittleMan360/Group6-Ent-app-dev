# Quick test runner for the project
# Usage: .\test.ps1 [all|<TestClassName>|<TestClassName>#<methodName>]

param(
    [string]$TestFilter = "all"
)

# Set Java home and path if needed
if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
    $env:JAVA_HOME = 'C:\Program Files\Java\jdk-25'
    $env:Path = "$env:JAVA_HOME\bin;$env:Path"
}

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path

Write-Host "Testing in: $projectRoot" -ForegroundColor Cyan

if ($TestFilter -eq "all") {
    Write-Host "Running all tests..." -ForegroundColor Green
    & "$projectRoot\mvnw.cmd" test
} else {
    Write-Host "Running test(s): $TestFilter" -ForegroundColor Green
    & "$projectRoot\mvnw.cmd" "-Dtest=$TestFilter" test
}

exit $LASTEXITCODE
