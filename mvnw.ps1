# Maven Wrapper PowerShell bootstrapping script
$ErrorActionPreference = "Stop"

# 1. Read properties file
$propertiesFile = Join-Path $PSScriptRoot ".mvn\wrapper\maven-wrapper.properties"
if (-not (Test-Path $propertiesFile)) {
    Write-Error "Could not find maven-wrapper.properties at $propertiesFile"
}

$properties = @{}
Get-Content $propertiesFile | ForEach-Object {
    $line = $_.Trim()
    if ($line -and -not $line.StartsWith("#")) {
        $parts = $line.Split("=", 2)
        if ($parts.Length -eq 2) {
            $properties[$parts[0].Trim()] = $parts[1].Trim()
        }
    }
}

$url = $properties["distributionUrl"]
if (-not $url) {
    Write-Error "distributionUrl property is missing in $propertiesFile"
}

# 2. Parse filename and version
$fileName = $url.Substring($url.LastIndexOf('/') + 1)
$folderName = $fileName -replace '-bin\.zip$', ''
$folderName = $folderName -replace '\.zip$', ''

$targetDir = Join-Path $env:USERPROFILE ".m2\wrapper\dists\$folderName"
$mavenHome = Join-Path $targetDir $folderName
$mvnCmd = Join-Path $mavenHome "bin\mvn.cmd"

# 3. Download and extract if not present
if (-not (Test-Path $mvnCmd)) {
    Write-Host "Maven Wrapper: Downloading Maven from $url ..."
    if (-not (Test-Path $targetDir)) {
        New-Item -ItemType Directory -Force -Path $targetDir | Out-Null
    }
    
    $zipPath = Join-Path $targetDir $fileName
    
    # Enable TLS 1.2
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    
    $webClient = New-Object System.Net.WebClient
    $webClient.DownloadFile($url, $zipPath)
    
    Write-Host "Maven Wrapper: Extracting to $targetDir ..."
    # Extract zip
    Add-Type -AssemblyName System.IO.Compression.FileSystem
    [System.IO.Compression.ZipFile]::ExtractToDirectory($zipPath, $targetDir)
    
    Remove-Item -Path $zipPath -Force
    Write-Host "Maven Wrapper: Installation completed successfully."
}

# 4. Run Maven
$mvnArgs = @()
foreach ($arg in $args) {
    $mvnArgs += $arg
}

if ($mvnArgs.Length -gt 0) {
    & $mvnCmd $mvnArgs
} else {
    & $mvnCmd
}
exit $LASTEXITCODE
