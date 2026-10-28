# Usage (from the Java folder):  .\new-project.ps1 todolist
param([Parameter(Mandatory = $true)][string]$Name)

if ($Name -notmatch '^[a-z][a-z0-9-]*$') {
    Write-Error "Use lowercase letters, digits, hyphens only. Example: todolist"
    exit 1
}
if (Test-Path $Name) {
    Write-Error "Folder '$Name' already exists."
    exit 1
}

Copy-Item "_template" $Name -Recurse

$ws = Get-Content "Java.code-workspace" -Raw | ConvertFrom-Json
$ws.folders += [pscustomobject]@{ path = $Name }
$ws | ConvertTo-Json -Depth 5 | Set-Content "Java.code-workspace"

Write-Host "Created '$Name' and added it to Java.code-workspace."
Write-Host "Reload VS Code window (Ctrl+Shift+P > Developer: Reload Window)."
