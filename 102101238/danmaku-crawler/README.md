# 爬取视频弹幕程序

本程序使用 Python 编写，旨在爬取视频网站上的弹幕数据并进行处理和分析。

## 功能

- 爬取指定视频的弹幕数据
- 对弹幕数据进行处理和分析
- 生成词云图展示弹幕关键词

## 使用方法

1. 确保已安装 Python 3.x 版本。

2. 安装程序依赖库，在命令行中执行以下命令：

```
   pip install -r requirements.txt
```

3. 运行主程序 `main.py`，在命令行中执行以下命令：

```
   python main.py
```
4. 程序将自动爬取指定视频的弹幕数据，并生成词云图。

## 依赖项

本程序依赖以下 Python 库：

- aiohttp==3.8.5
- matplotlib==3.7.2
- numpy==1.25.2
- openpyxl==3.1.2
- Pillow==10.0.0
- pkuseg==0.0.25
- selenium==4.12.0
- wordcloud==1.9.2

确保在运行程序之前已安装这些库，并且版本符合要求。

您可以使用以下命令安装依赖项：

```
   pip install -r requirements.txt
```

## 注意事项

- 本程序仅用于学习和研究目的，请遵守视频网站的使用规定和法律法规。
- 请勿滥用程序，以免给视频网站服务器造成不必要的负担。

## 许可证

本程序使用 GNU General Public License v3.0 许可证。详情请参阅 [LICENSE](../../LICENSE) 文件。