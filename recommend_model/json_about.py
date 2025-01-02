import json
import os
import torch


# 用于用户向量更新后，重新写回json文件
def rewrite_json(file_name,users_data):
    with open(file_name, 'w', encoding='utf-8') as f:
        json.dump(users_data, f, indent=4)

# 有新商品添加时，将信息追加到json文件
def append_feature_to_json(file_name, vector_id, feature_vector):
    """将特征向量追加到 JSON 文件中，使用对象格式存储"""
    # 确保目标文件夹存在
    directory = os.path.dirname(file_name)
    if not os.path.exists(directory):
        os.makedirs(directory)

    # 检查文件是否存在
    if not os.path.exists(file_name):
        # 如果文件不存在，初始化为一个空对象
        with open(file_name, 'w') as f:
            f.write('{}')  # 初始化为 JSON 对象

    # 追加新数据
    with open(file_name, 'r+') as f:
        # 读取现有数据
        file_data = json.load(f)

        # 将新的特征向量添加到字典中
        file_data[str(vector_id)] = {"feature_vector": feature_vector}

        # 移动文件指针到文件的开头
        f.seek(0)
        # 写入更新后的数据
        json.dump(file_data, f, indent=4)

    print(f"特征向量已追加到文件：{file_name}")

def remove_feature_from_json(file_name, vector_id):
    """根据 ID 删除特征向量"""
    if not os.path.exists(file_name):
        print(f"文件 {file_name} 不存在。")
        return

    # 读取现有的 JSON 文件
    with open(file_name, 'r') as f:
        data = json.load(f)

    # 查找并删除指定 ID 的条目
    original_length = len(data)
    data = [item for item in data if item["id"] != vector_id]

    if len(data) == original_length:
        print(f"未找到 ID 为 {vector_id} 的条目。")
        return

    # 将更新后的数据写回文件
    with open(file_name, 'w') as f:
        json.dump(data, f, indent=4)

    print(f"ID 为 {vector_id} 的特征向量已删除。")


# 加载所有商品特征
def load_product_features(filename):
    try:
        """从 JSON 文件加载商品特征，并将特征列表转换为 Tensor"""
        with open(filename, 'r', encoding='utf-8') as f:
            product_features = json.load(f)

        # 将每个特征从列表转换为 Tensor
        for product_id, feature_dict in product_features.items():
            if "feature_vector" in feature_dict and isinstance(feature_dict["feature_vector"], list):
                feature_list = feature_dict["feature_vector"]
                if all(isinstance(x, (int, float)) for x in feature_list):
                    product_features[product_id] = torch.tensor(feature_list, dtype=torch.float32)

        return product_features
    except Exception as e:
        print("loadProduct:"+str(e))



# 加载所有用户特征
def load_user_features(filename):
    """从 JSON 文件加载商品特征，并将特征列表转换为 Tensor"""
    try:
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
    except Exception as e:
        print("load"+str(e))

