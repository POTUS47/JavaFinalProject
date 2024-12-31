import json
import os
import torch


# 用于用户向量更新后，重新写回json文件
def rewrite_json(file_name,users_data):
    with open(file_name, 'w', encoding='utf-8') as f:
        json.dump(users_data, f, indent=4)

# 有新商品添加时，将信息追加到json文件
def append_feature_to_json(file_name, vector_id, feature_vector):
    """将特征向量追加到 JSON 文件中"""
    # 确保目标文件夹存在
    directory = os.path.dirname(file_name)
    if not os.path.exists(directory):
        os.makedirs(directory)

    # 检查文件是否存在
    if not os.path.exists(file_name):
        # 如果文件不存在，初始化为一个空列表
        with open(file_name, 'w') as f:
            f.write('[')  # 初始化为 JSON 数组

    # 追加新数据
    with open(file_name, 'r+') as f:
        f.seek(0, os.SEEK_END)  # 移动到文件末尾
        pos = f.tell()  # 获取文件当前位置
        if pos > 1:  # 如果文件中已有内容（非空数组）
            f.seek(pos - 1)  # 移动光标，覆盖末尾的 ']'
            f.write(',\n')  # 添加逗号分隔符

        # 写入新条目
        json.dump({"id": vector_id, "feature_vector": feature_vector}, f, indent=4)
        f.write(']')  # 补上 JSON 数组的结束符

    print(f"特征向量已追加到文件：{file_name}")


# 加载所有商品特征
def load_product_features(filename):
    """从 JSON 文件加载商品特征，并将特征列表转换为 Tensor"""
    with open(filename, 'r', encoding='utf-8') as f:
        product_features = json.load(f)

    # 将每个特征从列表转换为 Tensor
    for product_id, feature_list in product_features.items():
        product_features[product_id] = torch.tensor(feature_list, dtype=torch.float32)  # 转换为 Float Tensor

    return product_features


# 加载所有用户特征
def load_user_features(filename):
    """从 JSON 文件加载商品特征，并将特征列表转换为 Tensor"""
    # 确保目标文件夹存在
    directory = os.path.dirname(filename)
    if not os.path.exists(directory):
        os.makedirs(directory)

    # 读取现有的用户特征数据
    if os.path.exists(filename):
        with open(filename, 'r', encoding='utf-8') as f:
            users_data = json.load(f)
    else:
        users_data = {}

    return users_data
