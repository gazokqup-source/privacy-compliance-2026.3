# Maven 安装指南

## 问题
编译报错：`mvn: command not found`

## 解决方案

### 方案 1：使用 Chocolatey 安装（最简单）

如果你安装了 Chocolatey（Windows 包管理器）：

```powershell
# 以管理员身份运行 PowerShell
choco install maven
```

安装完成后重启 PowerShell 即可使用。

---

### 方案 2：手动安装

#### 步骤 1：下载 Maven
访问：https://maven.apache.org/download.cgi

下载：`apache-maven-3.9.6-bin.zip`

或直接下载：
https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip

#### 步骤 2：解压
解压到：`C:\Maven\apache-maven-3.9.6`

#### 步骤 3：配置环境变量

**方法 A：图形界面**
1. 右键"此电脑" → 属性 → 高级系统设置
2. 点击"环境变量"
3. 在"系统变量"中新建：
   - 变量名：`MAVEN_HOME`
   - 变量值：`C:\Maven\apache-maven-3.9.6`
4. 找到 `Path` 变量 → 编辑 → 新建
   - 添加：`%MAVEN_HOME%\bin`
5. 确定保存

**方法 B：PowerShell（管理员）**
```powershell
[Environment]::SetEnvironmentVariable("MAVEN_HOME", "C:\Maven\apache-maven-3.9.6", "Machine")
$path = [Environment]::GetEnvironmentVariable("Path", "Machine")
[Environment]::SetEnvironmentVariable("Path", "$path;%MAVEN_HOME%\bin", "Machine")
```

#### 步骤 4：验证安装
打开新的 PowerShell 窗口：
```powershell
mvn --version
```

应该显示 Maven 版本信息。

---

### 方案 3：使用 IntelliJ IDEA（最简单，无需配置）

1. 下载 IDEA：https://www.jetbrains.com/idea/download/
2. 安装后打开 `backend` 文件夹
3. IDEA 会自动下载 Maven 和依赖
4. 右键 `PrivacyComplianceApplication.java` → Run

---

### 方案 4：使用 VS Code + Java 扩展

1. 安装 VS Code
2. 安装扩展：
   - Language Support for Java(TM) by Red Hat
   - Maven for Java
3. 打开 `backend` 文件夹
4. 在 Maven 面板中右键项目 → Run → Spring Boot

---

## 安装后测试

```powershell
cd C:\Users\Minyu\.openclaw\workspace-study\projects\privacy-compliance\backend
mvn spring-boot:run
```

等待看到：
```
Started PrivacyComplianceApplication in X.XXX seconds
```

即表示成功！
