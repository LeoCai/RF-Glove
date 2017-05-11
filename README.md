# RF-Glove
基于RFID的弹钢琴项目

## 简介
一个手套，每个手指贴一个RFID标签，然后人没按下手指，服务端发出对应的钢琴声。

## 原理
手指按下后的RSSI变化

## 系统流程
1. 初始化阅读器，开始读取数据
2. 每读到一个标签信号，加入到对应标签ID对应的缓冲数组位置
3. 当读满BUFFER_SIZE个信号后，输出缓冲区数组
4. 图表模块(CharDrawer)画出对应采样信号
5. 动作检测模块(ActionManager)监测信号并采取相关动作

## 主要类介绍
1. 系统主入口： [ImpinjStater.java](https://github.com/LeoCai/RF-Glove/blob/master/src/dislab/rfidaction/core/ImpinjStater.java)
2. 动作监听管理： [ActionManger.java](https://github.com/LeoCai/RF-Glove/blob/master/src/dislab/rfidaction/core/ActionManager.java)
3. 弹琴动作事件监测： [PianoActionChecker.java](https://github.com/LeoCai/RF-Glove/blob/master/src/dislab/rfidaction/core/actionchecker/PianoActionChecker.java)

## 注意事项
1. 阅读器IP地址
2. 标签EPC
3. 标签MASK

均在[ImpinjStater.java](https://github.com/LeoCai/RF-Glove/blob/master/src/dislab/rfidaction/core/ImpinjStater.java)中修改
