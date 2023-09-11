import asyncio
import unittest
from unittest.mock import patch
from excel_generator import generate_excel


class TestGenerateExcel(unittest.TestCase):

    @patch('builtins.input', side_effect=['日本核污染水排海', '300', '20'])
    def test_generate_excel(self, mock_input):
        loop = asyncio.get_event_loop()
        result = loop.run_until_complete(generate_excel())
        print(f"生成的 Excel 文件名为: {result}")
        self.assertTrue(result.endswith('.xlsx'))


if __name__ == '__main__':
    unittest.main()
