一、应用架构设计思路

	采用 MVVM 分层架构 + 模块化设计，具体分层如下：
	┌───────────────────────┐
	│        UI Layer       │  # Jetpack Compose + ViewModel
	├───────────────────────┤
	│      Domain Layer     │  # 业务逻辑（本需求较简单可合并到ViewModel）
	├───────────────────────┤
	│       Data Layer      │  # Room + Repository
	└───────────────────────┘

	模块划分：
	data：数据库实体、DAO、Repository

	ui：Compose组件、ViewModel、导航

	model：数据类

	utils：摄像头工具类、权限处理
	
二、关键实现说明
	数据库操作：

		使用Room的Flow实现数据自动刷新

		在主线程外执行数据库操作

	图片处理：

		使用FileProvider处理摄像头拍照

		使用Coil库高效加载图片

	模块化设计：

		各屏幕独立为单独Composable函数

		扫码功能通过接口隔离（onScanBarcode）

	日志记录：

		使用Timber库添加关键日志点

		在ViewModel和Repository中添加操作日志



