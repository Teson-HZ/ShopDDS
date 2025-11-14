# 连锁超市物资分配调拨决策支持系统（ShopDDS）
🏬 A chain supermarket goods allocation and transfer decision support system. This system is based on a B/S architecture and uses Java + MySQL technology to achieve automated and informational management of goods allocation and transfer.

**一款基于 B/S 架构的决策支持系统，用于提升连锁超市物资分配、调拨与库存管理的效率与精度。**
本仓库包含系统说明、设计文档摘录、数据库建表脚本示例与使用说明，便于复现、部署与二次开发。

---

## 🔎 项目概述

随着连锁超市规模扩大，传统人工调拨方式已无法满足效率与准确性需求。ShopDDS 通过采集超市申请、仓库库存与距离信息，自动生成物资分配与最优调拨方案，并支持人工干预与实时库存更新，从而实现物资供应的信息化与智能化，降低人力成本、提升运营效率与库存利用率。

---

## ✨ 主要功能（Highlights）

* **超市物资申请管理**：超市提交商品需求（申请表记入数据库）。
* **仓库库存上报**：仓库提交当前库存与最低储备，系统计算可供量。
* **智能物资分配**：基于“总申请表”与“总库存表”自动分配；当库存不足时按申请比例缩减；支持人工干预。
* **最优物资调拨（运输问题求解）**：结合仓库-超市距离表，求解调拨方案并写入调拨表，支持人工干预。
* **发货 / 收货与库存同步**：根据调拨表自动更新仓库发货记录与超市收货记录并调整库存。
* **多角色权限与管理**：总部管理员 / 仓库管理员 / 超市管理员三类角色，支持用户管理、商品管理、距离管理等。
* **报表与查询**：分配表、调拨表、发物表、收物表等查询与导出。

---

## 📕 数据流图

* **（1）顶层图**：
* <img width="691" height="316" alt="image" src="https://github.com/user-attachments/assets/9028aa06-1d7d-4584-9e82-47c0167c64b7" />
---
* **（2）DFD（0层）**：
* <img width="766" height="405" alt="image" src="https://github.com/user-attachments/assets/bfd44851-828f-4b5f-b368-651260f7c46d" />
---
* **（3）DFD（1层）**：
* **Ⅰ物资分配子系统：**
* <img width="604" height="480" alt="image" src="https://github.com/user-attachments/assets/17d6929c-9947-4d28-8b3a-5801851af75c" />
---
* **Ⅱ物资调拨子系统：**
* <img width="691" height="423" alt="image" src="https://github.com/user-attachments/assets/be8bfccd-1b6d-47c4-9684-64b344c34b95" />

---

## 🏗️ 系统架构 & 技术栈

* 架构：**B/S（Browser / Server）**。
* 前端：JSP + Layui（响应式表单与企业级界面）。
* 后端：**Java（Servlet + JDBC）**。
* 数据库：**MySQL**（示例建表 SQL 已包含在文档中）。
* Web 容器：Apache Tomcat（作者环境示例）。

---

## 📦 数据库（重要表一览）
* **ER图**
* <img width="691" height="414" alt="image" src="https://github.com/user-attachments/assets/dfe9db78-3d45-468e-a91d-a84001bfc258" />

> 下面列出关键表与字段摘要：

* `commodity`（商品表）：`commodity_Id`, `commodity_Barcode`, `commodity_Name`, `commodity_Price` 等。
* `supermarket`（超市表）：`supermarket_Id`, `supermarket_Name`, `supermarket_Adr`。
* `warehouse`（仓库表）：`warehouse_Id`, `warehouse_Name`, `warehouse_Adr`。
* `inv`（各仓库库存）：主键 `(commodity_Id, warehouse_Id)`，包含 `inv_Amount`、`inv_Time`。
* `apl`（超市申请表）：主键 `(supermarket_Id, commodity_Id)`，包含 `apl_Amount`、`apl_Time`。
* `allinv` / `allapl`（总库存 / 总申请）：用于分配计算汇总。
* `dis`（距离表）：`warehouse_Id`, `supermarket_Id`, `distance`（用于调拨优化）。
* `assign`（分配表）与 `allocate`（调拨表）：分别记录分配与调拨结果，供查询与后续发/收货处理。

---

## 🧾 数据字典（Data Dictionary）

> **约束标识说明：**
> `PK`：主键 `FK`：外键 `NN`：非空 `UQ`：唯一 `AI`：自增

---

### 一、实体表（Entity Tables）

#### 🛒 商品表（commodity）

|  No | 属性名               | 数据类型        | 约束条件  | 说明   |
| :-: | :---------------- | :---------- | :---- | :--- |
|  1  | commodity_Id      | Varchar(5)  | PK、NN | 商品序号 |
|  2  | commodity_Barcode | Varchar(20) | NN    | 商品条码 |
|  3  | commodity_Name    | Varchar(20) | NN    | 商品名  |
|  4  | commodity_Class   | Varchar(10) | NN    | 商品类别 |
|  5  | commodity_Unit    | Varchar(5)  | NN    | 计量单位 |
|  6  | commodity_Price   | Double(5,2) | NN    | 商品单价 |

---

#### 🏢 总部管理员表（headadmin）

|  No | 属性名            | 数据类型        | 约束条件  | 说明      |
| :-: | :------------- | :---------- | :---- | :------ |
|  1  | headadmin_Id   | Varchar(10) | PK、NN | 总部管理员编号 |
|  2  | headadmin_Name | Varchar(10) | NN    | 负责人姓名   |
|  3  | headadmin_Pwd  | Varchar(20) | NN    | 登录密码    |
|  4  | headadmin_Tel  | Varchar(11) | NN    | 联系电话    |

---

#### 👨‍💼 员工表（employee）

|  No | 属性名           | 数据类型        | 约束条件  | 说明             |
| :-: | :------------ | :---------- | :---- | :------------- |
|  1  | employee_Id   | Varchar(10) | PK、NN | 员工编号           |
|  2  | employee_Name | Varchar(10) | NN    | 员工姓名           |
|  3  | employee_Pwd  | Varchar(20) | NN    | 登录密码           |
|  4  | employee_Ide  | Varchar(10) | NN    | 员工身份（超市/仓库管理员） |
|  5  | employee_Tel  | Varchar(11) | NN    | 联系电话           |

---

#### 🏬 超市表（supermarket）

|  No | 属性名              | 数据类型        | 约束条件                         | 说明    |
| :-: | :--------------- | :---------- | :--------------------------- | :---- |
|  1  | supermarket_Id   | Varchar(5)  | PK、NN                        | 超市编号  |
|  2  | supermarket_Name | Varchar(10) | NN                           | 超市名称  |
|  3  | supermarket_Adr  | Varchar(20) | NN                           | 超市地址  |
|  4  | supermarket_Tel  | Varchar(11) | NN                           | 超市电话  |
|  5  | employee_Id      | Varchar(10) | FK(employee(employee_Id))、NN | 超市管理员 |

---

#### 🏗️ 仓库表（warehouse）

|  No | 属性名            | 数据类型        | 约束条件                         | 说明    |
| :-: | :------------- | :---------- | :--------------------------- | :---- |
|  1  | warehouse_Id   | Varchar(5)  | PK、NN                        | 仓库编号  |
|  2  | warehouse_Name | Varchar(10) | NN                           | 仓库名称  |
|  3  | warehouse_Adr  | Varchar(20) | NN                           | 仓库地址  |
|  4  | warehouse_Tel  | Varchar(11) | NN                           | 仓库电话  |
|  5  | employee_Id    | Varchar(10) | FK(employee(employee_Id))、NN | 仓库管理员 |

---

### 二、关系表（Relationship Tables）

#### 📦 各仓库库存表（inv）

|  No | 属性名          | 数据类型        | 约束条件                           | 说明   |
| :-: | :----------- | :---------- | :----------------------------- | :--- |
|  1  | commodity_Id | Varchar(10) | PK、FK(commodity(commodity_Id)) | 商品编号 |
|  2  | warehouse_Id | Varchar(10) | PK、FK(warehouse(warehouse_Id)) | 仓库编号 |
|  3  | inv_Amount   | Int         |                                | 库存量  |
|  4  | inv_Time     | Time        |                                | 时间   |

---

#### 🧾 各超市申请表（apl）

|  No | 属性名            | 数据类型        | 约束条件                               | 说明   |
| :-: | :------------- | :---------- | :--------------------------------- | :--- |
|  1  | supermarket_Id | Varchar(10) | PK、FK(supermarket(supermarket_Id)) | 超市编号 |
|  2  | commodity_Id   | Varchar(10) | PK、FK(commodity(commodity_Id))     | 商品编号 |
|  3  | apl_Amount     | Int         |                                    | 申请数量 |
|  4  | apl_Time       | Time        |                                    | 时间   |

---

#### 📊 总库存表（allinv）

|  No | 属性名           | 数据类型        | 约束条件                           | 说明   |
| :-: | :------------ | :---------- | :----------------------------- | :--- |
|  1  | commodity_Id  | Varchar(10) | PK、FK(commodity(commodity_Id)) | 商品编号 |
|  2  | allinv_Amount | Int         |                                | 可供量  |

---

#### 📈 总申请表（allapl）

|  No | 属性名           | 数据类型        | 约束条件                           | 说明   |
| :-: | :------------ | :---------- | :----------------------------- | :--- |
|  1  | commodity_Id  | Varchar(10) | PK、FK(commodity(commodity_Id)) | 商品编号 |
|  2  | allapl_Amount | Int         |                                | 需求量  |

---

#### 🗺️ 距离表（dis）

|  No | 属性名            | 数据类型          | 约束条件                               | 说明   |
| :-: | :------------- | :------------ | :--------------------------------- | :--- |
|  1  | warehouse_Id   | Varchar(10)   | PK、FK(warehouse(warehouse_Id))     | 仓库编号 |
|  2  | supermarket_Id | Varchar(10)   | PK、FK(supermarket(supermarket_Id)) | 超市编号 |
|  3  | distance       | Double(100,2) |                                    | 实际距离 |

---

#### 🚚 物资分配表（assign）

|  No | 属性名            | 数据类型        | 约束条件                               | 说明   |
| :-: | :------------- | :---------- | :--------------------------------- | :--- |
|  1  | supermarket_Id | Varchar(10) | PK、FK(supermarket(supermarket_Id)) | 超市编号 |
|  2  | commodity_Id   | Varchar(10) | PK、FK(commodity(commodity_Id))     | 商品编号 |
|  3  | assign_Amount  | Int         |                                    | 分配数量 |

---

#### 🔄 物资调拨表（allocate）

|  No | 属性名             | 数据类型        | 约束条件                               | 说明   |
| :-: | :-------------- | :---------- | :--------------------------------- | :--- |
|  1  | supermarket_Id  | Varchar(10) | PK、FK(supermarket(supermarket_Id)) | 超市编号 |
|  2  | warehouse_Id    | Varchar(10) | PK、FK(warehouse(warehouse_Id))     | 仓库编号 |
|  3  | commodity_Id    | Varchar(10) | PK、FK(commodity(commodity_Id))     | 商品编号 |
|  4  | allocate_Amount | Int         |                                    | 调拨数量 |

---


## 🛠️ 系统部署（快速开始）

> 以下为示例环境与启动步骤

### 环境需求

* Java JDK 1.8
* Apache Tomcat 8.2
* MySQL 5.7 
* IDE:Netbeans

### 快速部署步骤

1. 克隆仓库：`git clone <repo-url>`
2. 在 MySQL 中创建数据库创建数据表。（请检查编码与用户权限）
3. 修改后端配置：填写 DB 连接、用户名、密码、JDBC URL。
4. 在 IDE 中导入 Web 项目（或打包为 `.war`）：使用 Maven / Ant（视项目结构而定）。
5. 将 `.war` 部署到 Tomcat，访问：`http://<host>:<port>/shopdds/`。

---

## 🧭 使用说明（简要）

* 三类登录入口：总部管理员 / 仓库管理员 / 超市管理员（分别拥有不同权限）。
* 标准流程示例：超市提交申请 → 总部汇总 → 自动分配（或人工调整）→ 生成分配表 → 调拨求解（结合距离）→ 生成调拨表 → 仓库发货 & 超市收货 → 库存更新。
* 报表查询：总部可查询全部分配/调拨记录；超市/仓库可按自身编号筛选查看相关记录。

---


## 🙋 贡献 & 联系

欢迎提交 Issue / PR：

* Issue：功能建议、Bug 报告、文档改进。
* PR：请基于 `develop` 分支提交，并附上变更说明与测试步骤。

---

## 📜 参考与致谢

本项目题目来源于内蒙古大学计算机学院张献国老师

---
