import os
import unittest
from wordcloud_generator import generate_wordcloud


class WordCloudGeneratorTestCase(unittest.TestCase):
    def test_generate_wordcloud(self):
        # 测试generate_wordcloud函数的工作是否正确
        excel_name = "日本核污染水排海_939f9642-19a1-48e4-8400-73d73bb13589.xlsx"
        keywords = {"核污染": None, }
        stopwords = ["6", ]
        img_path = "dove-35401_1920.png"

        # 调用generate_wordcloud函数
        generate_wordcloud(excel_name, keywords, stopwords, img_path)

        # 检查生成的图像文件是否存在来验证是否成功生成词云图
        expected_image_path = "日本核污染水排海_939f9642-19a1-48e4-8400-73d73bb13589.png"
        self.assertTrue(os.path.exists(expected_image_path))


if __name__ == '__main__':
    unittest.main()