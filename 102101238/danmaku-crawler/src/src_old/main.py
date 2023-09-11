# 导入所需模块.
import time
import asyncio
import excel_generator
import wordcloud_generator


async def main():
    start_time = time.time()
    excel_name = await excel_generator.generate_excel()
    wordcloud_generator.generate_wordcloud(excel_name, {"核污染水": None, }, ['6', '有', '有人吗', '阿姨', '1000+'])
    end_time = time.time()
    print("程序运行时间：{:.2f}秒".format(end_time - start_time))


if __name__ == '__main__':
    asyncio.get_event_loop().run_until_complete(main())
