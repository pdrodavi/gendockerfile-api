pipeline {
    agent any
    
    tools {
        // NOME DEVE SER EXATAMENTE O QUE VOCÊ DEFINIU NO GLOBAL TOOL CONFIGURATION
        dotnetsdk 'DotNet-8.0'  
    }
    
    parameters {
        string(
            name: 'PROJETO_CAMINHO',
            defaultValue: 'C:\\MeuProjeto',
            description: 'Pasta do projeto .NET'
        )
    }
    
    stages {
        stage('Build e ZIP') {
            steps {
                // AGORA O .NET JÁ ESTÁ NO PATH - NÃO PRECISA DE CONFIGURAÇÃO MANUAL
                bat """
                    @echo off
                    set PROJETO=${params.PROJETO_CAMINHO}
                    set SAIDA=build_temp
                    
                    echo ========================================
                    echo Compilando projeto: %PROJETO%
                    echo ========================================
                    
                    cd /d "%PROJETO%"
                    
                    echo 1. Restaurando pacotes...
                    dotnet restore
                    
                    echo 2. Compilando...
                    dotnet build -c Release
                    
                    echo 3. Preparando arquivos...
                    if exist %SAIDA% rmdir /s /q %SAIDA%
                    mkdir %SAIDA%
                    
                    echo 4. Copiando executável...
                    for /r "bin\\Release" %%f in (*.exe) do (
                        copy "%%f" "%SAIDA%\\"
                        echo Copiado: %%f
                    )
                    
                    echo 5. Copiando DLLs...
                    for /r "bin\\Release" %%f in (*.dll) do (
                        xcopy "%%f" "%SAIDA%\\" /c /i /y 2>nul
                    )
                    
                    echo 6. Criando ZIP...
                    powershell Compress-Archive -Path "%SAIDA%\\*" -DestinationPath "aplicacao.zip" -Force
                    
                    echo 7. Limpando...
                    rmdir /s /q %SAIDA%
                    
                    echo ========================================
                    echo ZIP criado: %CD%\\aplicacao.zip
                    echo ========================================
                """
            }
        }
        
        stage('Arquivar') {
            steps {
                archiveArtifacts artifacts: 'aplicacao.zip'
            }
        }
    }
}