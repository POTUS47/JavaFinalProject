import torch

from extract_feature import extract_description_feature, extract_image_feature
from initialize_model import ModelManager
from json_about import load_user_features, append_feature_to_json, rewrite_json


def generate_product_vector(product_id, descriptions, image_path):
    # 获取单例的模型实例
    model_manager = ModelManager.get_instance()

    # 调用模型生成特征向量
    text_vector = extract_description_feature(descriptions,
                                              model_manager.model,
                                              model_manager.device)
    text_vector = text_vector.mean(dim=0)  # 平均值

    img_vector = extract_image_feature(image_path, model_manager.model, model_manager.preprocess, model_manager.device)

    # 保存文字特征到JSON文件
    append_feature_to_json("features/product_text_feature.json", product_id, text_vector)
    # 保存图片特征到JSON文件
    append_feature_to_json("features/product_img_feature.json", product_id, img_vector)

    return True


def update_user_features(user_id, strings, file_name="features/user_feature.json"):
    """更新用户特征向量"""
    # 提取用户当前特征向量和已合并向量数
    users_data = load_user_features(file_name)
    user_data = users_data.get(str(user_id), {"user_vector": None, "update_count": 0})
    # 生成本次待添加的用户特征向量
    new_user_vector = generate_user_vector(strings)
    # 如果是第一次生成用户特征
    if user_data["user_vector"] is None:
        # 初始化用户特征向量和计数
        updated_vector = new_user_vector
        update_count = 1
    else:
        # 加载现有用户特征向量
        current_vector = torch.tensor(user_data["user_vector"])
        update_count = user_data["update_count"]
        # 合并特征向量
        updated_vector = update_count / (update_count + 1) * current_vector + new_user_vector / (update_count + 1)
        update_count += 1

    # 更新 JSON 数据
    users_data[str(user_id)] = {
        "user_vector": updated_vector.tolist(),  # 将 Tensor 转为列表以存储
        "update_count": update_count
    }
    # 保存回 JSON 文件
    rewrite_json(file_name, users_data)

    print(f"用户 {user_id} 的特征向量已更新！")


def generate_user_vector(strings):
    model_manager = ModelManager.get_instance()
    description_vectors = extract_description_feature(strings, model_manager.model, model_manager.device)
    new_user_feature = description_vectors.mean(dim=0)
    # 合并所有特征并计算最终的用户特征向量
    return new_user_feature
