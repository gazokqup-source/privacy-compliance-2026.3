# 隐私协议智能合规风险辅助审查系统 - 启动与测试指南

## 一、环境检查

### 1.1 检查 Node.js
```powershell
node --version
npm --version
```
**要求**: Node.js v18+ 和 npm v9+

如果没有，下载：https://nodejs.org/zh-cn/ (下载 LTS 版本)

### 1.2 检查 Java 和 Maven
```powershell
java --version
mvn --version
```
**要求**: Java 17+ 和 Maven 3.8+

Java 下载：https://adoptium.net/
Maven 下载：https://maven.apache.org/download.cgi

---

## 二、启动项目

### 方法 A：一键启动（最简单）

1. **双击** `start.bat`
2. 等待两个命令行窗口打开
3. 约 30-60 秒后启动完成

### 方法 B：手动启动（推荐，便于查看日志）

#### 步骤 1：启动后端
```powershell
# 打开一个新的 PowerShell 窗口
cd C:\Users\Minyu\.openclaw\workspace-study\projects\privacy-compliance\backend
mvn spring-boot:run
```

等待看到：
```
Started PrivacyComplianceApplication in X.XXX seconds
```

#### 步骤 2：启动前端
```powershell
# 打开另一个新的 PowerShell 窗口
cd C:\Users\Minyu\.openclaw\workspace-study\projects\privacy-compliance\frontend
npm install
npm run dev
```

等待看到：
```
VITE ready in XXX ms
Local: http://localhost:3000/
```

---

## 三、访问系统

### 3.1 打开浏览器
访问：http://localhost:3000

### 3.2 登录
- **用户名**: `admin`
- **密码**: `123456`
- 点击"登录"

---

## 四、功能测试

### 4.1 测试登录
✅ 预期：成功登录，跳转到首页

### 4.2 测试上传隐私协议

1. 点击左侧菜单 **"协议上传"**
2. 选择 **"文本输入"** 标签
3. 在文本框粘贴以下内容：

```
某 APP 隐私政策

1. 信息收集
我们可能收集您的个人信息用于相关服务。

2. 信息共享
我们可能会与第三方共享您的信息。

3. 用户权利
您有权查询、更正您的个人信息。

4. 安全措施
我们将采取合理的安全措施保护您的信息。

5. 生效日期
本政策自发布之日起生效。
```

4. 输入文档标题：`测试隐私政策`
5. 点击 **"提交分析"**
6. 等待进度条完成（约 3 秒）

✅ 预期：自动跳转到结果页面

### 4.3 测试查看结果

在结果页面，你应该看到：

- **总体风险评估**: 高风险 - 存在多项严重合规问题
- **文档信息**: 标题、分析时间、条款数量、风险评分 78.5
- **条款级审查结果**: 5 个条款，每个都有：
  - 原文内容
  - 风险类别
  - 置信度
  - 命中规则
  - 法律依据
  - 修改建议

点击每个条款可以展开查看详情。

### 4.4 测试历史记录

1. 点击左侧菜单 **"历史记录"**
2. 应该看到 3 条模拟的历史记录
3. 点击 **"查看结果"** 可以查看之前的分析报告
4. 点击 **"删除"** 可以删除记录

✅ 预期：都能正常工作

### 4.5 测试规则管理

1. 点击左侧菜单 **"规则管理"**
2. 应该看到 3 条预设规则
3. 测试功能：
   - **新增规则**: 点击右上角"新增规则"，填写信息后保存
   - **编辑规则**: 点击某条规则的"编辑"按钮，修改后保存
   - **删除规则**: 点击某条规则的"删除"按钮，确认删除

✅ 预期：都能正常工作

---

## 五、API 测试（可选）

### 5.1 使用浏览器测试

打开浏览器访问：
- http://localhost:8080/api/auth/me → 应该返回当前用户信息
- http://localhost:8080/api/rules/list → 应该返回规则列表
- http://localhost:8080/api/analyze/history → 应该返回历史记录

### 5.2 使用 Swagger UI（推荐）

访问：http://localhost:8080/swagger-ui.html

可以看到所有 API 接口文档，并在线测试。

---

## 六、常见问题

### Q1: 前端启动失败，提示 "npm: command not found"
**解决**: 安装 Node.js，确保添加到系统 PATH

### Q2: 后端启动失败，提示 "java: command not found"
**解决**: 安装 JDK 17+，确保添加到系统 PATH

### Q3: 端口被占用（3000 或 8080）
**解决**: 
- 前端端口 3000 被占用：修改 `vite.config.js` 中的 `port: 3001`
- 后端端口 8080 被占用：修改 `application.yml` 中的 `server.port: 8081`

### Q4: 登录失败
**检查**: 
- 后端是否正常启动
- 浏览器控制台是否有错误
- 用户名密码是否正确（admin/123456）

### Q5: 提交分析后没有反应
**检查**:
- 后端日志是否有错误
- 浏览器 F12 控制台是否有报错
- 网络请求是否成功（F12 → Network 标签）

---

## 七、测试完成检查清单

- [ ] 能够成功登录
- [ ] 能够上传文本并提交分析
- [ ] 能够查看分析结果（5 个条款）
- [ ] 能够查看历史记录
- [ ] 能够管理规则（增删改）
- [ ] 后端 API 正常响应

全部打勾 = 第一阶段测试通过 ✅

---

## 八、下一步

测试通过后，可以开始：
1. 完善 UI 样式
2. 接入真实数据库（MySQL）
3. 开发规则引擎
4. 集成 FastBERT 模型

---

**技术支持**: 有问题随时找我，Shadow。
